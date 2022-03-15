package net.danielgill.ros.tgenerator.rtt;

import java.util.ArrayList;

import net.danielgill.ros.timetable.reference.Reference;

public class RTTService {
    public Reference ref;
    
    private ArrayList<RTTStop> stops;
    
    public RTTService(String identity) {
        ref = new Reference(identity);
        this.stops = new ArrayList<>();
    }
    
    public RTTService(String identity, ArrayList<RTTStop> stops) {
        ref = new Reference(identity);
        this.stops = stops;
    }
    
    public Reference getReference() {
        return ref;
    }
    
    public RTTStop getOrigin() {
        return stops.get(0);
    }
    
    public RTTStop getDestination() {
        return stops.get(stops.size() - 1);
    }
    
    public RTTStop getStopFromCRS(String crs) {
        for(int i = 0; i < stops.size(); i++) {
            if(stops.get(i).CRS.equalsIgnoreCase(crs)) {
                return stops.get(i);
            }
        }
        return null;
    }
    
    public String getDescription() {
        return getOrigin().description + " to " + getDestination().description;
    }
}
