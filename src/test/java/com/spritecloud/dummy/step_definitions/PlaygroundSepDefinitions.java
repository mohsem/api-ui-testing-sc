package com.spritecloud.dummy.step_definitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaygroundSepDefinitions {

    public Playwright playwright = Playwright.create();
    public BrowserType browserType = playwright.chromium();
    public Browser browser = browserType.launch();
    public Page page = browser.newPage();

    @When("I go to test page")//OK
    public void i_go_to_test_page() {

        playwright = Playwright.create();
        page.navigate("http://www.uitestingplayground.com/");
        System.out.println(page.title());
    }

    @When("I navigate to {string} page")//OK
    public void i_navigate_to_page(String featureUrl) {
        page.locator("a:has-text(\"" + featureUrl + "\")").click();
        System.out.println(page.title());
    }

    @Then("I should be able to click the button")//OK
    public void i_should_be_able_to_click_th_button() {
        page.locator("button[class=\"btn btn-primary\"]").click();
    }

    @Then("I should be able to click to the green button")
    public void i_should_be_able_to_click_to_the_green_button() {
        page.locator("button[class=\"btn btn-success\"]").click();
    }

}
