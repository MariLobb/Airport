/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.passengerUtils;

import core.models.Passenger;

/**
 *
 * @author FabiFree
 */
public class GenerateFullName {

    public static String getFullname(Passenger passenger) {
        return passenger.getFirstname() + " " + passenger.getLastname();
    }
}
