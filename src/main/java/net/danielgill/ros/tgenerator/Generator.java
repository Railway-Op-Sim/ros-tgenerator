package net.danielgill.ros.tgenerator;

import net.danielgill.ros.tgenerator.rtt.RTTParse;
import net.danielgill.ros.tgenerator.rtt.RTT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import net.danielgill.ros.service.Service;
import net.danielgill.ros.service.time.Time;
import net.danielgill.ros.tgenerator.rtt.RTTService;
import net.danielgill.ros.tgenerator.rtt.RTTStop;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Generator<T extends Parser> {
    private String APIUser;
    private String APIPass;
    private File servicesList;
    private T t;
    private String year;
    private String month;
    private String day;
    
    public Generator(String APIUser, String APIPass, File servicesList, String year, String month, String day) {
        this.APIUser = APIUser;
        this.APIPass = APIPass;
        this.servicesList = servicesList;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public <T extends Parser> String createTimetable(T parse, Time startTime) throws FileNotFoundException, IOException, ParseException, InterruptedException {
        JSONParser parser = new JSONParser();
        JSONArray serviceIDs = (JSONArray) parser.parse(new FileReader(servicesList));
        RTT rtt = new RTT();
        ArrayList<RTTService> services = new ArrayList<>();
        Timetable ttb = new Timetable(startTime);
        
        for(int i = 0; i < serviceIDs.size(); i++) {
            String id = (String) serviceIDs.get(i);
            JSONObject jo = rtt.getData(id, year, month, day, APIUser, APIPass);
            RTTParse rttp = new RTTParse();
            services.add(rttp.parseRTT(jo));
        }
        for(int i = 0; i < serviceIDs.size(); i++) {
            Service s = parse.genService(services.get(i), ttb);
            if(s != null) {
                ttb.addService(s);
            }
        }
        return ttb.getTextTimetable();
    }
}
