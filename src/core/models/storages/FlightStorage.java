/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author MARIA LUCIA LOBO
 */
public class FlightStorage extends Storage {

    private static FlightStorage instance;
    private ArrayList<Flight> flights;

    private FlightStorage() {
        this.flights = new ArrayList<>();
    }

    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    @Override
    public void addItem(Object flight) {
        if (!this.flights.contains((Flight) flight)) {
            this.flights.add((Flight) flight);
        }
    }

    public ArrayList<Flight> getFlights() {
        return this.flights;
    }

}
