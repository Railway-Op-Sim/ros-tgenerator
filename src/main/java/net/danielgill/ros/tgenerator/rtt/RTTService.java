package net.danielgill.ros.tgenerator.rtt;

import java.util.ArrayList;
import net.danielgill.ros.service.reference.Reference;

public class RTTService {
    private Reference ref;
    
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
}
