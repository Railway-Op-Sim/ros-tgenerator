package net.danielgill.ros.tgenerator;

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
        this.origin = (JSONObject) ((JSONArray) rtt.get("origin")).get(0);
        this.destination = (JSONObject) ((JSONArray) rtt.get("destination")).get(0);
        this.locations = (JSONArray) rtt.get("locations");
    }
    
    public String getTrainIdentity() {
        return (String) rtt.get("trainIdentity");
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
                return new Time(insertColon(((String) ((JSONObject) locations.get(i)).get("workingTime")).substring(0, 3)));
            }
        }
        return null;
    }
    
    private String insertColon(String toInsert) {
        return toInsert.substring(0, 2) + ":" + toInsert.substring(1, 3);
    }
}
