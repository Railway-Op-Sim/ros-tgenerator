package net.danielgill.ros.tgenerator.rtt;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RTTParse {
    public RTTParse() {
        
    }
    
    public RTTService parseRTT(JSONObject rtt) {
        JSONArray locations = (JSONArray) rtt.get("locations");
        ArrayList<RTTStop> stops = new ArrayList<>();
        for(int i = 0; i < locations.size(); i++) {
            JSONObject location = (JSONObject) locations.get(i);
            String desc = (String) location.get("description");
            String CRS = (String) location.get("crs");
            String TIPLOC = (String) location.get("tiploc");
            String platform = (String) location.get("platform");
            RTTStop stop = new RTTStop();
        }
    }
    
    /*
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
    */
}
