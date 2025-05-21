/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storages.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Allison Ruiz
 */
public class LocationListController {

    public static Response updateLocationList(DefaultTableModel model) {
        try {
            model.setRowCount(0); // Limpiar el modelo
            LocationStorage locationStorage = LocationStorage.getInstance();
            ArrayList<Location> locations = locationStorage.getLocations();

            if (locations == null || locations.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT);
            }

            // Ordenar por ID ascendente
            locations.sort(Comparator.comparing(Location::getAirportId));

            for (Location location : locations) {
                model.addRow(new Object[]{
                    location.getAirportId(),
                    location.getAirportName(),
                    location.getAirportCity(),
                    location.getAirportCountry(),
                });
            }

            return new Response("List updated successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
