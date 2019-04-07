import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=fc44d15e1378900312c1b9991b109ccd");

        Scanner in = new Scanner((InputStream) url.getContent());

        String res = "";

        while (in.hasNext()) {
            res += in.nextLine();

        }

        JSONObject object = new JSONObject(res);

        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");

        model.setTemp(main.getDouble("temp"));

        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");

        for (int i = 0; i < getArray.length(); i++) {

            JSONObject object1 = getArray.getJSONObject(i);

            model.setIcon((String)object1.get("icon"));

            model.setMain((String)object1.get("main"));

        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "C"+ "\n" +
                "Humidity" + model.getHumidity() + "%" + "\n"+
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png";

    }
}
