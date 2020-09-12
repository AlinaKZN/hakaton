package ru.povolzie.hakaton.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Slf4j
public class PointsParser {

  public static List<PointOfInterest> parse() {

    List pointsOfInterest = new ArrayList<>();

    JSONParser parser = new JSONParser();
    try {
      JSONArray array = (JSONArray) parser.parse(new FileReader("points.json"));

      for (Object o : array) {
        JSONObject pointObject = (JSONObject) o;

        /*String name = (String) person.get("name");
        System.out.println(name);

        String city = (String) person.get("city");
        System.out.println(city);

        String job = (String) person.get("job");
        System.out.println(job);*/
        Long id = (Long) pointObject.get("id");
        //  Long id = Long.parseLong(s) ;
        Double latitude = (Double) pointObject.get("latitude");
        Double longitude = (Double) pointObject.get("longitude");
        String type = (String) pointObject.get("type");
        pointsOfInterest.add(new PointOfInterest(id, latitude.floatValue(), longitude.floatValue(), type));
      }
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return pointsOfInterest;
  }
}
