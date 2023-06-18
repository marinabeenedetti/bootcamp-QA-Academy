package br.com.sysmap.driver;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class Api {

    static String apiKey = "875e041c94d845b6a813aaaafddcbef3"; // valor da API do site: https://home.openweathermap.org/api_keys
    public static String getLatLon(String city) {
        String retorno = null;
        city = city.replaceAll(" ", "-");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet("http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&appid=" + apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            //            System.out.println(resp);
            JSONObject obj = new JSONObject(resp.replace("[", "").replace("]", ""));
           // System.out.println("valor da lat: " + obj.getBigDecimal("lat"));
           // System.out.println("valor da lon: " + obj.getBigDecimal("lon"));
            retorno = "lat="
                    .concat(String.valueOf(obj.get("lat")))
                    .concat("&lon=")
                    .concat(String.valueOf(obj.get("lon")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;

    }
    public static String currentWeather(String city) {
        String retorno = null;
        city.replaceAll(" ", "-");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uri = "https://api.openweathermap.org/data/2.5/weather?" + getLatLon(city) + "&appid=" + apiKey;HttpGet get = new HttpGet(uri);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp);
            System.out.println(obj.getJSONObject("main"));
            JSONObject main = obj.getJSONObject("main");
            System.out.println("Retorno da API com o valor da temperatura: " + main.get("temp"));
            retorno = String.valueOf(obj.get("name"));
            System.out.println(retorno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;

    }

    public static double currentWeatherTemp(String city, String unitsTemp) {
        double retorno = 0;
        city.replaceAll(" ", "-");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uri = "https://api.openweathermap.org/data/2.5/weather?" + getLatLon(city) + "&units="+unitsTemp+"&appid=" + apiKey;HttpGet get = new HttpGet(uri);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp);
            JSONObject main = obj.getJSONObject("main");
            System.out.println(main.get("temp"));
            retorno = Math.round(Double.parseDouble(String.valueOf(main.get("temp"))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retorno;

    }

}