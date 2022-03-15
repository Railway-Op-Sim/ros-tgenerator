package net.danielgill.ros.tgenerator.rtt;

import net.danielgill.ros.timetable.event.StopEvent;
import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.time.Time;

public class RTTStop {
    public String description;
    public String CRS;
    public String TIPLOC;
    
    public String platform;
    
    //Can be null.
    public Time arrTime;
    public Time depTime;
    
    public RTTStop(String desc, String CRS, String TIPLOC, String platform, Time arrTime, Time depTime) {
        this.description = desc;
        this.CRS = CRS;
        this.TIPLOC = TIPLOC;
        this.platform = platform;
        this.arrTime = arrTime;
        this.depTime = depTime;
    }
    
    public int getPlatformInt() {
        return Integer.parseInt(platform);
    }
    
    public StopEvent toStopEvent() {
        if(arrTime != null && depTime != null) {
            return new StopEvent(arrTime, depTime, new NamedLocation(description));
        } else if(arrTime != null && depTime == null) {
            return new StopEvent(arrTime, new NamedLocation(description));
        } else {
            return new StopEvent(new NamedLocation(description), depTime);
        }
    }
}
