package net.danielgill.ros.tgenerator.rtt;

import java.util.ArrayList;
import net.danielgill.ros.service.time.Time;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RTTParse {
    public RTTParse() {
        
    }
    
    public RTTService parseRTT(JSONObject rtt) {
        JSONArray locations = (JSONArray) rtt.get("locations");
        String ref = (String) rtt.get("trainIdentity");
        ArrayList<RTTStop> stops = new ArrayList<>();
        for(int i = 0; i < locations.size(); i++) {
            JSONObject location = (JSONObject) locations.get(i);
            String desc = (String) location.get("description");
            String CRS = (String) location.get("crs");
            String TIPLOC = (String) location.get("tiploc");
            String platform = (String) location.get("platform");
            Time arr;
            Time dep;
            if(location.containsKey("gbttBookedArrival")) {
                arr = new Time(insertColon(((String) location.get("gbttBookedArrival")).substring(0, 3)));
            } else {
                arr = null;
            }
            if(location.containsKey("gbttBookedDeparture")) {
                dep = new Time(insertColon(((String) location.get("gbttBookedDeparture")).substring(0, 3)));
            } else {
                dep = null;
            }
            RTTStop stop = new RTTStop(desc, CRS, TIPLOC, platform, arr, dep);
            stops.add(stop);
        }
        RTTService rtts = new RTTService(ref, stops);
        return rtts;
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
    */
    
    private String insertColon(String toInsert) {
        return toInsert.substring(0, 2) + ":" + toInsert.substring(2, 3);
    }
}
