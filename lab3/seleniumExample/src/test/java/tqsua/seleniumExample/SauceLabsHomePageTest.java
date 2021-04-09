package tqsua.seleniumExample;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceLabsHomePageTest {
        private WebDriver browser;

        @BeforeEach
        void setup() {
            System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/bin/geckodriver");
            browser = new FirefoxDriver();
        }

        @Test
        public void site_header_is_on_home_page() {
            browser.get("https://www.saucelabs.com");
            WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
            assertTrue((href.isDisplayed()));
        }

        @AfterEach
        void cleanUp() {
            browser.close();
        }
}
