package tqsua;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlazeDemoSteps {
    private WebDriver webDriver;

    @After
    public void cleanUp() {
        webDriver.close();
    }

    @When("I navigate to {string}")
    public void navigateTo(String url) {
        System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/bin/geckodriver");
        webDriver = new FirefoxDriver();
        webDriver.get(url);
    }

    @When("I choose {string} as the departure city")
    public void chooseDeparture(String departure) {
        webDriver.findElement(By.name("fromPort")).findElement(By.xpath("//option[. = '" + departure + "']")).click();
    }

    @When("I choose {string} as the destination city")
    public void chooseDestination(String destination) {
        webDriver.findElement(By.name("toPort")).findElement(By.xpath("//option[. = '" + destination + "']")).click();
    }

    @When("I press the {string} button")
    public void pressFindFlights(String buttonText) {
        webDriver.findElement(By.xpath("//input[@value='" + buttonText +"']")).click();
    }

    @When("I choose flight {int}")
    public void chooseFlight(int position) {
        webDriver.findElement(By.cssSelector("tr:nth-child(" + position + ") .btn")).click();
    }

    @When("I type {string} on the {string} field")
    public void typeOnInputField(String text, String field) {
        webDriver.findElement(By.id(field)).sendKeys(text);
    }

    @When("I choose {string} as the Card Type")
    public void chooseCardType(String type) {
        webDriver.findElement(By.id("cardType")).findElement(By.xpath("//option[. = '" + type + "']")).click();
    }

    @Then("I should be in the page {string}")
    public void assertPageTitle(String result) {
        assertEquals(webDriver.getTitle(), result);
    }

}
