package net.danielgill.ros.tgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.danielgill.ros.service.Service;
import net.danielgill.ros.service.ServiceInvalidException;
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
    
    public <T extends Parser> String createTimetable(T parse) throws FileNotFoundException, IOException, ParseException, InterruptedException {
        String ttb = "";
        JSONParser parser = new JSONParser();
        JSONArray serviceIDs = (JSONArray) parser.parse(new FileReader(servicesList));
        RTT rtt = new RTT();
        for(int i = 0; i < serviceIDs.size(); i++) {
            String id = (String) serviceIDs.get(i);
            JSONObject jo = rtt.getData(id, year, month, day, APIUser, APIPass);
            RTTParse rttp = new RTTParse(jo);
            Service s = parse.genService(rttp);
            try {
                ttb += "\u0000" + s.toTimetableString();
            } catch (ServiceInvalidException e) {
                System.err.println("[" + e.getRef() + "]: " + e.getMessage());
            }
        }
        return ttb;
    }
}
