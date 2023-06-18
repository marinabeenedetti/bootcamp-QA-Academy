package br.com.sysmap;

import br.com.sysmap.driver.Api;
import br.com.sysmap.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static br.com.sysmap.driver.Api.currentWeather;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProvaProjectTest {

    String city = "Barueri"; //Case sensitive
    @BeforeAll
    static void inicio() {
        Browser.openChrome("https://openweathermap.org/");
    }

   @Test
    @Order(1)
    void validateWebsite() {
        boolean validate = Browser.elementExist(By.xpath("//span[text()='OpenWeather']"));
        assertTrue(validate);
        System.out.println("Validado que estamos no site do Weather Map");
    }

    @Test
    @Order(2)
    void searchCity()  {

        Browser.write(By.xpath("//input[@placeholder='Search city']"), city);
        Browser.waiting(4);
        Browser.click(By.xpath("//button[@class='button-round dark']"));
        Browser.waiting(5);
        Browser.click(By.xpath("//ul[@class='search-dropdown-menu']"));
        Browser.waiting(1);
        assertTrue(Browser.elementExist(By.xpath("//h2[text()='"+city+", BR']")));
        System.out.println("CT1 - Validado que pesquisou a cidade de "+city+", BR");
    }
    @Test
    @Order(3)
    void validTempCelsius()  {
        double tempAPI, tempSite;
        Browser.click(By.xpath("//div[text()='Metric: °C, m/s']"));
        tempAPI = Api.currentWeatherTemp(city,"metric");
        Browser.waiting(1);
        tempSite = Double.parseDouble(Browser.readText(By.xpath("//span[@class='heading']"))
              .replaceAll("°C"," "));
        System.out.println("Retorno da API:"+tempAPI+" Valor no site: "+tempSite);
        boolean validate = (tempAPI == tempSite);
        assertTrue(validate);
        System.out.println("CT2 - Validado que a temp em grau Celsius no site é igual a temp da chamada API");
    }

    @Test
    @Order(4)
    void validTempFahrenheit()  {
        double tempAPI, tempSite;
        Browser.click(By.xpath("//div[text()='Imperial: °F, mph']"));
        tempAPI = Api.currentWeatherTemp(city,"Imperial");
        Browser.waiting(1);
        tempSite = Double.parseDouble(Browser.readText(By.xpath("//span[@class='heading']"))
                .replaceAll("°F"," "));
        System.out.println("Retorno da API:"+tempAPI+" Valor no site: "+tempSite);
        boolean validate = (tempAPI == tempSite);
        assertTrue(validate);
        System.out.println("CT3 - Validado que a temp em grau Fahrenheit no site é igual a temp da chamada API");
    }



    @AfterAll
    static void end() {
        Browser.closeChrome();
    }


}