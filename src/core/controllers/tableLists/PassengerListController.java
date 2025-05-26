/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.passenger.GenerateFullPhone;
import core.models.Passenger;
import core.models.storages.PassengerStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fvarelo and mlobol
 */
import java.util.Comparator;

public class PassengerListController {

    public static Response updatePassengerList(DefaultTableModel model) {
        try {
            model.setRowCount(0); // Limpiar el modelo
            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = passengerStorage.getPassengers();

            if (passengers == null || passengers.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, passengers.clone());
            }

            // Ordenar por ID ascendente
            passengers.sort(Comparator.comparingLong(Passenger::getId));

            for (Passenger passenger : passengers) {
                model.addRow(new Object[]{
                    passenger.getId(),
                    passenger.getFirstname(),
                    passenger.getBirthDate(),
                    passenger.calculateAge(),
                    GenerateFullPhone.generateFullPhone(passenger),
                    passenger.getCountry(),
                    passenger.getFlights().size(),});
            }

            return new Response("List updated successfully.", Status.OK, passengers.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
