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
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author MARIA LUCIA LOBO
 */
public class FlightController {

    public static Response createFlight(String id, String planeId, String departureLocationId, String scaleLocationId, String arrivalLocationId,
            String departureYear, String departureMonth, String departureDay, String departureHour, String departureMinute, String hoursDurationArrival,
            String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        Location departureLocation = null;
        Location scaleLocation = null;
        Location arrivalLocation = null;
        Plane plane = null;
        int hoursDurationArrival1;
        int minutesDurationArrival1;
        int hoursDurationScale1;
        int minutesDurationScale1;
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

            if (hoursDurationScale.equals("")) {
                return new Response("Please select an hour for scale duration", Status.BAD_REQUEST);
            }

            if (minutesDurationScale.equals("")) {
                return new Response("Please select minute for scale duration", Status.BAD_REQUEST);
            }

            for (Flight flight : flights) {
                if (flight.getId().equals(id)) {
                    return new Response("A flight with this ID is already registered.", Status.BAD_REQUEST);
                }
            }

            for (Plane plane1 : planes) {
                if (plane1.getId().equals(planeId)) {
                    plane = plane1;
                }

            }

            if (plane == null) {
                return new Response("A plane with this ID is not registered.", Status.BAD_REQUEST);
            }

            for (Location departureLocation1 : departureLocationIdStorages) {
                if (departureLocation1.getId().equals(planeId)) {
                    plane = plane1;
                }

            }

            for (Plane plane1 : planes) {
                if (plane1.getId().equals(planeId)) {
                    plane = plane1;
                }

            }

            for (Location loccation : locations) {
                if (flight.getDepartureLocationId) {
                    return 
                }

            }
            // validar que se siga el formato xxxyyy
            flightStorage.addItem(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureYear1, departureDate, hoursDurationArrival1,
                    minutesDurationArrival1, hoursDurationScale1, minutesDurationScale1));
            return new Response("Plane added successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
