package br.com.sysmap.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static final String DRIVER_CHROME = "C:\\dev\\tools\\chromedriver.exe";

    static WebDriver driver;

    public static void openChrome(String url) {
        System.setProperty("webdriver.chrome.driver", DRIVER_CHROME);
        driver = new ChromeDriver();
        driver.get(url);
        System.out.println("Navegador aberto");
    }

    public static void closeChrome() {
        driver.quit();
        System.out.println("Navegador fechado");
    }

}