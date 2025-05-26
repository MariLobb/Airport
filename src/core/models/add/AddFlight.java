/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.add;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author FabiFree
 */
public class AddFlight{

    public static boolean addFlight(ArrayList<Flight> flights, Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
            return true;
        }
        return false;
    }

}
