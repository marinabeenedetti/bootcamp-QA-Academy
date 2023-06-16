package br.com.sysmap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class iniciandoProjetoTest {

    @Test
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver","C:\\dev\\tools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
    }
}
