package net.danielgill.ros.tgenerator;

import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RTTParse {
    private final JSONObject rtt;
    private final JSONObject origin;
    private final JSONObject destination;
    private final JSONArray locations;
    
    public RTTParse(JSONObject rtt) {
        this.rtt = rtt;
        System.out.println(rtt);
        this.origin = (JSONObject) ((JSONArray) rtt.get("origin")).get(0);
        this.destination = (JSONObject) ((JSONArray) rtt.get("destination")).get(0);
        this.locations = (JSONArray) rtt.get("locations");
    }
    
    public Reference getTrainIdentity() {
        return new Reference((String) rtt.get("trainIdentity"));
    }
    public String getAtocName() {
        return (String) rtt.get("atocName");
    }
    
    public String getOriginDescription() {
        return (String) origin.get("description");
    }
    
    public String getDestinationDescription() {
        return (String) destination.get("description");
    }
    public Time getDestinationTime() {
        return new Time(insertColon(((String) destination.get("workingTime")).substring(0, 3)));
    }
    
    public Time getArrivalTimeAtStation(String station) {
        for(int i = 0; i < locations.size(); i++) {
            if(((JSONObject) locations.get(i)).get("description").equals(station)) {
                return new Time(insertColon(((String) ((JSONObject) locations.get(i)).get("gbttBookedArrival")).substring(0, 3)));
            }
        }
        return null;
    }
    public Time getDepartureTimeAtStation(String station) {
        for(int i = 0; i < locations.size(); i++) {
            if(((JSONObject) locations.get(i)).get("description").equals(station)) {
                return new Time(insertColon(((String) ((JSONObject) locations.get(i)).get("gbttBookedDeparture"))));
            }
        }
        return null;
    }
    public int getPlatformAtStation(String station) {
        for(int i = 0; i < locations.size(); i++) {
            if(((JSONObject) locations.get(i)).get("description").equals(station)) {
                return Integer.parseInt((String)((JSONObject) locations.get(i)).get("platform"));
            }
        }
        return 0;
    }
    public boolean stopsAt(String station) {
        for(int i = 0; i < locations.size(); i++) {
            if(((JSONObject) locations.get(i)).get("description").equals(station)) {
                return (boolean) ((JSONObject)locations.get(i)).get("isCall");
            }
        }
        return false;
    }
    
    public String getDescription() {
        return getOriginDescription() + " to " + getDestinationDescription();
    }
    
    private String insertColon(String toInsert) {
        return toInsert.substring(0, 2) + ":" + toInsert.substring(2, 3);
    }
}
