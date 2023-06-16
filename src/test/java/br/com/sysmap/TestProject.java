package br.com.sysmap;

import br.com.sysmap.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProject {

    @BeforeAll
    static void inicio() {
        Browser.openChrome("http://google.com");
    }

    @Test
    @Order(2)
    void validateWebsite() {
        boolean validate = Browser.elementExist(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@value='Pesquisa Google']"));
        assertTrue(validate);
        System.out.println("Validado que estamos no site do google");
    }

    @Test
    @Order(3)
    void search()  {
        Browser.write(By.xpath("//*[@id='APjFqb']"), "facebook");
        Browser.click(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@value='Pesquisa Google']"));
        Browser.waiting(2);
        assertTrue(Browser.elementExist(By.xpath("//h3[text()='Facebook – entre ou cadastre-se']")));
        System.out.println("Validado que pesquisou o facebook na pagina de pesquisa do google");
    }


    @AfterAll
    static void end() {
        Browser.closeChrome();
    }


}