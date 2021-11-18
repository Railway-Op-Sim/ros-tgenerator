package net.danielgill.ros.tgenerator.rtt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RTT {
    public RTT() {
        
    }
    
    public JSONObject getData(String service, String year, String month, String day, String APIUser, String APIPass) throws FileNotFoundException, IOException, ParseException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rtt.io/api/v1/json/service/" + service + "/" + year + "/" + month + "/" + day))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((APIUser + ":" + APIPass).getBytes()))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response.body());
    }
}
