package br.com.sysmap.driver;

import org.openqa.selenium.By;

public class Browser extends DriverFactory {

    public static boolean elementExist(By element) {
        return driver.findElements(element).size() != 0;
    }

    public static void write(By element, String value) {
        driver.findElement(element).sendKeys(value);
    }

    public static void click(By element) {
        driver.findElement(element).click();
    }

    public static void waiting(Integer Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readText(By element) {
        return driver.findElement(element).getText();
    }
}