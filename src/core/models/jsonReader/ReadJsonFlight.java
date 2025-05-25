package core.models.jsonReader;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storages.FlightStorage;
import core.models.storages.LocationStorage;
import core.models.storages.PlaneStorage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONException;

/**
 *
 * @author fvarelo and mlobol
 */
public class ReadJsonFlight implements JsonReader<Flight> {

    @Override
    public ArrayList<Flight> read(String path) throws IOException, JSONException {
        ArrayList<Flight> flights = new ArrayList<>();

        InputStream is = new FileInputStream(path);
        JSONArray array = new JSONArray(new JSONTokener(is));

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            String id = object.getString("id");
            String planeId = object.getString("plane");
            String departureLocationId = object.getString("departureLocation");
            String arrivalLocationId = object.getString("arrivalLocation");
            String scaleLocationId = object.optString("scaleLocation", null);
            String departureStr = object.getString("departureDate");
            LocalDateTime departureDate = LocalDateTime.parse(departureStr); // como el constructor está en LocalDate, toca volver a parsear
            int hoursArrival = object.getInt("hoursDurationArrival");
            int minutesArrival = object.getInt("minutesDurationArrival");
            int hoursScale = object.getInt("hoursDurationScale");
            int minutesScale = object.getInt("minutesDurationScale");
            Plane plane = null;
            Location departureLocation = null;
            Location arrivalLocation = null;
            Location scaleLocation = null;
            Flight flight;

            PlaneStorage storageP = PlaneStorage.getInstance();
            LocationStorage storageL = LocationStorage.getInstance();
            for (Plane planeTemp : storageP.getPlanes()) {
                if (planeTemp.getId().equals(planeId)) {
                    plane = planeTemp;
                }
            }
            for (Location locationTemp : storageL.getLocations()) {
                if (locationTemp.getAirportId().equals(departureLocationId)) {
                    departureLocation = locationTemp;
                }
            }
            for (Location locationTemp : storageL.getLocations()) {
                if (locationTemp.getAirportId().equals(arrivalLocationId)) {
                    arrivalLocation = locationTemp;
                }
            }
            for (Location locationTemp : storageL.getLocations()) {
                if (locationTemp.getAirportId().equals(scaleLocationId)) {
                    scaleLocation = locationTemp;
                }
            }

            if (scaleLocation == null) {
                flight = new Flight(id, plane,
                        departureLocation,
                        arrivalLocation,
                        departureDate,
                        hoursArrival,
                        minutesArrival);
            } else {
                flight = new Flight(id, plane,
                        departureLocation,
                        arrivalLocation,
                        scaleLocation,
                        departureDate,
                        hoursArrival,
                        minutesArrival,
                        hoursScale,
                        minutesScale);
            }

            flights.add(flight);
        }
        FlightStorage flightRegister = FlightStorage.getInstance();
        flightRegister.setFlights(flights);
        return flights;
    }
}
