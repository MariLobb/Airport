/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storages.LocationStorage;
import java.util.ArrayList;

/**
 *
 * @author MARIA LUCIA LOBO
 */
public class LocationController {

    public static Response createPlane(String airportId, String airportName, String airportCity, String airportCountry, String airportLatitude, String airportLongitude) {
        double airportLatitude1;
        double airportLongitude1;
        LocationStorage locationStorage = LocationStorage.getInstance();
        ArrayList<Location> locations = locationStorage.getLocations();

        try {
            if (airportId.equals("")) {
                return new Response("The airport id must be not empty.", Status.BAD_REQUEST);
            }

            if (airportName.equals("")) {
                return new Response("The airport name must be not empty.", Status.BAD_REQUEST);
            }

            if (airportCity.equals("")) {
                return new Response("The airport city must be not empty.", Status.BAD_REQUEST);
            }

            if (airportCountry.equals("")) {
                return new Response("The airport country  must be not empty.", Status.BAD_REQUEST);
            }
            if (airportLatitude.equals("")) {
                return new Response("The airport Latitude must be not empty.", Status.BAD_REQUEST);
            }
            if (airportLongitude.equals("")) {
                return new Response("The airport Longitude must be not empty.", Status.BAD_REQUEST);
            }

            for (Location location : locations) {
                if (location.getAirportId().equals(airportId)) {
                    return new Response("An airport with this ID is already registered.", Status.BAD_REQUEST);
                }
            }

            if (airportId.length() != 3) {
                return new Response("The ID must have 3 uppercase letters", Status.BAD_REQUEST);
            }

            if (!airportId.equals(airportId.toUpperCase())) {
                return new Response("The id letters must be uppercase", Status.BAD_REQUEST);
            }

            try {
                airportLatitude1 = Double.parseDouble(airportLatitude);
                if (airportLatitude1 < -90 | airportLatitude1 > 90) {
                    return new Response("The airport latitude must be in the range [-90,90].", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("The airport latitude must be numeric.", Status.BAD_REQUEST);
            }

            try {
                airportLongitude1 = Double.parseDouble(airportLongitude);
                if (airportLongitude1 < -180 | airportLongitude1 > 180) {
                    return new Response("The airport longitude must be in the range [-180,180].", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("The airport longitude must be numeric.", Status.BAD_REQUEST);
            }
            airportLongitude1 = Double.parseDouble(airportLongitude);

            locationStorage.addItem(new Location(airportId, airportName, airportCity, airportCountry, airportLatitude1, airportLongitude1));
            return new Response("Location added successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
