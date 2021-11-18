package net.danielgill.ros.tgenerator.rtt;

import net.danielgill.ros.service.time.Time;

public class RTTStop {
    private String description;
    private String CRS;
    private String TIPLOC;
    
    private String platform;
    
    //Can be null.
    private Time arrivalTime;
    private Time departureTime;
    
    public RTTStop(String desc, String CRS, String TIPLOC, String platform, Time arrTime, Time depTime) {
        this.description = desc;
        this.CRS = CRS;
        this.TIPLOC = TIPLOC;
        this.platform = platform;
        this.arrivalTime = arrTime;
        this.departureTime = depTime;
    }
}
