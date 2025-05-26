/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Location;
import java.util.ArrayList;

/**
 *
 * @author fvarelo and mlobol
 */
public class LocationStorage implements Storage{
    private static LocationStorage instance;
    private ArrayList<Location> locations;
    
    private LocationStorage() {
        this.locations = new ArrayList<>();
    }
    
    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    @Override
    public void addItem(Object location){
        if(!this.locations.contains((Location)location)){
            this.locations.add((Location)location);
        }
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Location> getLocations() {
        return this.locations;
    }
    
}
