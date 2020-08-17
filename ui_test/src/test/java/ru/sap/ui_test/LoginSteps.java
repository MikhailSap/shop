package ru.sap.ui_test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void openBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void navigateToLoginPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }


    @When("^click on login button$")
    public void clickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void provideUsernameAndPassword(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        webElement = webElement.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
    }

    @Then("^name should be  \"([^\"]*)\"$")
    public void nameShouldBe(String name) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("dd-user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @Given("^any user logged in$")
    public void userLoggedIn() {
        webDriver.findElement(By.id("logged-in-username"));
    }

    @When("^Open dropdown menu$")
    public void openDropdownMenu() {
        WebElement webElement = webDriver.findElement(By.id("dd-user"));
        webElement.click();
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("logged-in-username"));
        webElement.click();
    }
}
