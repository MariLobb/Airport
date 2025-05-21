/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storages.PlaneStorage;
import java.util.ArrayList;

/**
 *
 * @author MARIA LUCIA LOBO
 */
public class PlaneController {

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        int maxCapacity1;
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = planeStorage.getPlanes();
        try {
            if (id.equals("")) {
                return new Response("The Id must be not empty.", Status.BAD_REQUEST);
            }

            if (brand.equals("")) {
                return new Response("The Brand must be not empty.", Status.BAD_REQUEST);
            }

            if (model.equals("")) {
                return new Response("The Model must be not empty.", Status.BAD_REQUEST);
            }

            if (maxCapacity.equals("")) {
                return new Response("The Maximum capacity  must be not empty.", Status.BAD_REQUEST);
            }
            if (airline.equals("")) {
                return new Response("The Airline must be not empty.", Status.BAD_REQUEST);
            }

            for (Plane plane : planes) {
                if (plane.getId().equals(id)) {
                    return new Response("A plane with this ID is already registered.", Status.BAD_REQUEST);
                }
            }
            // validar forma xxxyyyy
            try {
                maxCapacity1 = Integer.parseInt(maxCapacity);
            } catch (NumberFormatException e) {
                return new Response("The maximum Capacity must be numeric.", Status.BAD_REQUEST);
            }
           
            planeStorage.addItem(new Plane(id, brand, model, maxCapacity1, airline));
            return new Response("Plane added successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
