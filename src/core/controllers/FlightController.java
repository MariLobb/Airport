/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storages.FlightStorage;
import core.models.storages.LocationStorage;
import core.models.storages.PlaneStorage;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author fvarelo and mlobol
 */
public class FlightController {

    public static Response createFlight(String id, String planeId, String departureLocationId, String scaleLocationId, String arrivalLocationId,
            String departureYear, String departureMonth, String departureDay, String departureHour, String departureMinute, String hoursDurationArrival,
            String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        Pattern ID_PATTERN = Pattern.compile("^[A-Z]{3}\\d{3}$");

        Location departureLocation = null;
        Location scaleLocation = null;
        Location arrivalLocation = null;
        Plane plane = null;
        LocalDateTime departureDate;
        int hoursDurationArrival1;
        int minutesDurationArrival1;
        int hoursDurationScale1 = 0;
        int minutesDurationScale1 = 0;
        int departureYear1;
        int departureMonth1;
        int departureDay1;
        int departureHour1;
        int departureMinute1;
        FlightStorage flightStorage = FlightStorage.getInstance();
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        LocationStorage departureLocationIdStorage = LocationStorage.getInstance();
        LocationStorage scaleLocationIdStorage = LocationStorage.getInstance();
        LocationStorage arrivalLocationIdStorage = LocationStorage.getInstance();
        ArrayList<Flight> flights = flightStorage.getFlights();
        ArrayList<Plane> planes = planeStorage.getPlanes();
        ArrayList<Location> departureLocationIdStorages = departureLocationIdStorage.getLocations();
        ArrayList<Location> scaleLocationIdStorages = scaleLocationIdStorage.getLocations();
        ArrayList<Location> arrivalLocationIdStorages = arrivalLocationIdStorage.getLocations();

        try {
            if (id.equals("")) {
                return new Response("The Id must be not empty.", Status.BAD_REQUEST);
            }

            if (planeId.equals("")) {
                return new Response("Please select a plane ID.", Status.BAD_REQUEST);
            }

            if (departureLocationId.equals("")) {
                return new Response("Please select a departure location ID.", Status.BAD_REQUEST);
            }

            if (scaleLocationId.equals("")) {
                return new Response("Please select a scale location ID.", Status.BAD_REQUEST);
            }

            if (arrivalLocationId.equals("")) {
                return new Response("Please select an arrival location ID.", Status.BAD_REQUEST);
            }

            if (departureYear.equals("")) {
                return new Response("Please select a departure year.", Status.BAD_REQUEST);
            }

            if (departureMonth.equals("")) {
                return new Response("Please select a departure month.", Status.BAD_REQUEST);
            }

            if (departureDay.equals("")) {
                return new Response("Please select a departure day.", Status.BAD_REQUEST);
            }

            if (departureHour.equals("")) {
                return new Response("Please select a departure hour.", Status.BAD_REQUEST);
            }

            if (departureMinute.equals("")) {
                return new Response("Please select a departure minute.", Status.BAD_REQUEST);
            }

            if (hoursDurationArrival.equals("")) {
                return new Response("Please select an hour for arrival duration", Status.BAD_REQUEST);
            }

            if (minutesDurationArrival.equals("")) {
                return new Response("Please select minute for arrival duration.", Status.BAD_REQUEST);
            }

            // Validar formato de IDs
            if (!ID_PATTERN.matcher(id).matches()) {
                return new Response("Invalid flight ID format found", Status.BAD_REQUEST);
            }

            for (Flight flight : flights) {
                if (flight.getId().equals(id)) {
                    return new Response("A flight with this ID is already registered.", Status.BAD_REQUEST);
                }
            }
            try {
                departureYear1 = Integer.parseInt(departureYear);
            } catch (NumberFormatException e) {
                return new Response("The departure year must be numeric.", Status.BAD_REQUEST);
            }
            try {
                departureMonth1 = Integer.parseInt(departureMonth);
            } catch (NumberFormatException e) {
                return new Response("Please select a departure month.", Status.BAD_REQUEST);
            }
            try {
                departureDay1 = Integer.parseInt(departureDay);
            } catch (NumberFormatException e) {
                return new Response("Please select a day.", Status.BAD_REQUEST);
            }
            try {
                departureHour1 = Integer.parseInt(departureHour);
            } catch (NumberFormatException e) {
                return new Response("Please select a hour.", Status.BAD_REQUEST);
            }
            try {
                departureMinute1 = Integer.parseInt(departureMinute);
            } catch (NumberFormatException e) {
                return new Response("Please select a minute.", Status.BAD_REQUEST);
            }
            try {
                departureDate = LocalDateTime.of(departureYear1, departureMonth1, departureDay1, departureHour1, departureMinute1);
            } catch (DateTimeException e) {
                return new Response("Please select a valid date.", Status.BAD_REQUEST);
            }
            try {
                hoursDurationArrival1 = Integer.parseInt(hoursDurationArrival);
            } catch (NumberFormatException e) {
                return new Response("The Arrival Hour must be numeric.", Status.BAD_REQUEST);
            }
            try {
                minutesDurationArrival1 = Integer.parseInt(minutesDurationArrival);
            } catch (NumberFormatException e) {
                return new Response("The Arrival Minute must be numeric.", Status.BAD_REQUEST);
            }
            for (Plane plane1 : planes) {
                if (plane1.getId().equals(planeId)) {
                    plane = plane1;
                }

            }

            if (plane == null) {
                return new Response("A plane with this ID is not registered.", Status.BAD_REQUEST);
            }

            for (Location location : departureLocationIdStorages) {
                if (location.getAirportId().equals(departureLocationId)) {
                    departureLocation = location;
                }
                if (location.getAirportId().equals(scaleLocationId)) {
                    scaleLocation = location;
                }
                if (location.getAirportId().equals(arrivalLocationId)) {
                    arrivalLocation = location;
                }
            }
            if (departureLocation == null) {
                return new Response("A Location with the departureLocation ID is not registered.", Status.BAD_REQUEST);
            }

            if (arrivalLocation == null) {
                return new Response("A Location with the arrivalLocation ID is not registered.", Status.BAD_REQUEST);
            }
            if (scaleLocation == null & scaleLocationId.equals("Location")) {
                // validar que se siga el formato xxxyyy
                flightStorage.addItem(new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrival1, minutesDurationArrival1));
                return new Response("Plane added successfully", Status.OK);
            } else if (scaleLocation == null) {
                return new Response("A Location with the scaleLocation ID is not registered.", Status.BAD_REQUEST);
            } else {
                if (hoursDurationScale.equals("Hour")) {
                    return new Response("Please select an hour for scale duration", Status.BAD_REQUEST);
                }

                if (minutesDurationScale.equals("Minute")) {
                    return new Response("Please select minute for scale duration", Status.BAD_REQUEST);
                }
                try {
                    hoursDurationScale1 = Integer.parseInt(hoursDurationScale);
                } catch (NumberFormatException e) {
                    return new Response("The Scale Hour must be numeric.", Status.BAD_REQUEST);
                }
                try {
                    minutesDurationScale1 = Integer.parseInt(minutesDurationScale);
                } catch (NumberFormatException e) {
                    return new Response("The Scale Minute must be numeric.", Status.BAD_REQUEST);
                }
                flightStorage.addItem(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival1, minutesDurationArrival1, hoursDurationScale1, minutesDurationArrival1));
                return new Response("Plane added successfully", Status.OK);
            }

        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
