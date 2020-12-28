package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
        Thread.sleep(3000);
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        WebElement webElement = webDriver.findElement(By.id("cur_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @Given("^any user logged in$")
    public void userLoggedIn() {
        webDriver.findElement(By.id("current-user"));
    }

    @Given("^click brand link$")
    public void clickToBrands() throws Throwable {
        WebElement webElement = webDriver.findElement(By.xpath("/html/body/div/div[1]/ul/li[4]/a"));
        webElement.click();
        Thread.sleep(3000);
    }

    @Given("^click to create new button$")
    public void clickToCreateNew() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("create-button"));
        webElement.click();
        Thread.sleep(2000);
    }

    @Given("^I provide new brand as \"([^\"]*)\"$")
    public void addNewBrand(String brand) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(brand);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("submit-button"));
        Thread.sleep(2000);
        webElement.click();
    }

    @Given("^check added brand \"([^\"]*)\"$")
    public void checkNewBrand(String brand) {
        List<WebElement> rows = webDriver.findElements(By.xpath("/html/body/div/div[2]/div/div/table"));
        int count = 0;

        for (int i = 0; i < rows.size(); i++) {
            String rowElement = rows.get(i).findElement(
                By.xpath("/html/body/div/div[2]/div/div/table/tbody/tr[" + (i + 1) +"]/td[1]")).getText();
            if (rowElement.equals(brand)) {
                count ++;
            }
        }
        assertThat(count > 0);
    }

    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("current-user"));
        Thread.sleep(1000);
        webElement.click();
        Thread.sleep(3000);
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("link-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("inp-username"));
        webDriver.findElement(By.id("inp-password"));
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
