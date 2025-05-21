/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Flight;
import core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author MARIA LUCIA LOBO
 */
public class PassengerStorage extends Storage {

    private static PassengerStorage instance;
    private ArrayList<Passenger> passengers;

    private PassengerStorage() {
        this.passengers = new ArrayList<>();
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }

    @Override
    public void addItem(Object passenger) {
        if (!this.passengers.contains((Passenger) passenger)) {
            this.passengers.add((Passenger) passenger);
        }
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

}
