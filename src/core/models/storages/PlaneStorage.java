/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author fvarelo and mlobol
 */
public class PlaneStorage extends Storage{
    private static PlaneStorage instance;
    private ArrayList<Plane> planes;
    
    private PlaneStorage() {
        this.planes = new ArrayList<>();
    }
    
    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }
    
    @Override
    public void addItem(Object plane){
        if(!this.planes.contains((Plane)plane)){
            this.planes.add((Plane)plane);
        }
    }

    public void setPlanes(ArrayList<Plane> planes) {
        this.planes = planes;
    }

    public ArrayList<Plane> getPlanes() {
        return this.planes;
    }
    
}
