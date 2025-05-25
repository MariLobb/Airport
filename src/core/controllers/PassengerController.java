/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storages.PassengerStorage;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author fvarelo and mlobol
 */
public class PassengerController {

    public static Response createPassenger(String id, String firstname, String lastname, String year,
            String month, String day, String phoneCode, String phone, String country) {
        long id1, phone1;
        int month1, year1, day1, phoneCode1;
        LocalDate birthDate;
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerStorage.getPassengers();
        try {
            if (id.equals("")) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty.", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty.", Status.BAD_REQUEST);
            }

            if (year.equals("")) {
                return new Response("year must be not empty.", Status.BAD_REQUEST);
            }
            if (month.equals("")) {
                return new Response("month must be not empty.", Status.BAD_REQUEST);
            }
            if (day.equals("")) {
                return new Response("day must be not empty.", Status.BAD_REQUEST);
            }
            if (phoneCode.equals("")) {
                return new Response("Phone Code must be not empty.", Status.BAD_REQUEST);
            }
            if (phone.equals("")) {
                return new Response("Phone must be not empty.", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty.", Status.BAD_REQUEST);
            }
            try {
                if (id.length() > 15) {
                    return new Response("The ID must have a maximum of 15 digits.", Status.BAD_REQUEST);
                }
                id1 = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return new Response("The id must be numeric", Status.BAD_REQUEST);
            }

            if (id1 < 0) {
                return new Response("The ID must be a number greater than 0", Status.BAD_REQUEST);
            }

            for (Passenger passenger : passengers) {
                if (passenger.getId() == id1) {
                    return new Response("A user with this ID is already registered.", Status.BAD_REQUEST);
                }
            }
            try {
                year1 = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                return new Response("The year must be numeric.", Status.BAD_REQUEST);
            }
            try {
                month1 = Integer.parseInt(month);
            } catch (NumberFormatException e) {
                return new Response("Please select a month.", Status.BAD_REQUEST);
            }
            try {
                day1 = Integer.parseInt(day);
            } catch (NumberFormatException e) {
                return new Response("Please select a day.", Status.BAD_REQUEST);
            }
            try {
                birthDate = LocalDate.of(year1, month1, day1);
            } catch (DateTimeException e) {
                return new Response("Please select a valid date.", Status.BAD_REQUEST);
            }
            try {
                if (phoneCode.length() > 3) {
                    return new Response("The Phone Code must have a maximum of 3 digits.", Status.BAD_REQUEST);
                }
                phoneCode1 = Integer.parseInt(phoneCode);
            } catch (NumberFormatException e) {
                return new Response("The Phone Code must be numeric.", Status.BAD_REQUEST);
            }
            if (phoneCode1 < 0) {
                return new Response("The Phone Code must be a number greater than 0.", Status.BAD_REQUEST);
            }
            try {
                if (phone.length() > 11) {
                    return new Response("The Phone must have a maximum of 11 digits.", Status.BAD_REQUEST);
                }
                phone1 = Long.parseLong(phone);
            } catch (NumberFormatException e) {
                return new Response("The Phone must be numeric", Status.BAD_REQUEST);
            }
            if (phone1 < 0) {
                return new Response("The Phone must be a number greater than 0", Status.BAD_REQUEST);
            }

            passengerStorage.addItem(new Passenger(id1, firstname, lastname, birthDate, phoneCode1, phone1, country));
            return new Response("Passenger added successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("Must be numeric", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
