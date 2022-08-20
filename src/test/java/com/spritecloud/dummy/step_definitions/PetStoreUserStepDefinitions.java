package com.spritecloud.dummy.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import io.restassured.specification.RequestSpecification;
import com.spritecloud.dummy.utils.RequestBodies;

public class PetStoreUserStepDefinitions {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private String userName = "";
    private RequestSpecification request = RestAssured
            .given()
            .baseUri(BASE_URL)
            .contentType("application/json");

    private static RestAssured rest = new RestAssured();
    private static RequestBodies userRequestBody = new RequestBodies();

    @When("I create a user")
    public void i_create_a_user() {
        String message = request.contentType("application/json")
                .body(userRequestBody.userRequestBody().toString())
                .post("/user")
                .then()
                .body("message", Matchers.equalTo("1000"))
                .extract().response().body().path("message");
    }

    @Then("I can receive user details")
    public void i_can_receive_user_details() {
        userName = userRequestBody.userRequestBody().get("username").toString();
        request.get("/user/" + userName)
                .then()
                .body("username", Matchers.equalTo(userName));
    }

    @When("I change user name with {string}")
    public void i_change_user_name_with(String newUserName) {
        String message = request.contentType("application/json")
                .body(userRequestBody.userRequestBody().put("username", newUserName).toString())
                .put("/user/" + userName)
                .then()
                .statusCode(Matchers.equalTo(200))
                .extract().response().body().path("message");
        userName = newUserName;
    }

    @Then("I can receive changed user details")
    public void i_can_receive_changed_user_details() {
        request.get("/user/" + userName)
                .then()
                .body("username", Matchers.equalTo(userName));
    }

    @When("I delete registered user")
    public void i_delete_registered_user() {
        request.delete("/user/" + userName)
                .then()
                .statusCode(Matchers.equalTo(200));
    }
    @Then("I should not receive user details for deleted user")
    public void i_should_not_receive_user_details_for_deleted_user() {
        request.get("/user/" + userName)
                .then()
                .statusCode(Matchers.equalTo(404))
                .body("message", Matchers.equalTo("User not found"));
    }
}
