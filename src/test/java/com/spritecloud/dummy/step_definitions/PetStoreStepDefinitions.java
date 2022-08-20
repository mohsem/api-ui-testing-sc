package com.spritecloud.dummy.step_definitions;

import com.spritecloud.dummy.utils.RequestBodies;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import java.util.Random;

public class PetStoreStepDefinitions {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification petRequestDetails = RestAssured
            .given()
            .baseUri(BASE_URL)
            .contentType("application/json");

    private static RestAssured rest = new RestAssured();
    private static RequestBodies petRequestBody = new RequestBodies();
    private static Random rand = new Random();
    private int availablePet;
    private int petId;
    private int orderId;

    @When("I add a new pet to the store")
    public void i_add_a_new_pet_to_the_store() {
        String petRequest = petRequestBody.petRequestBody().toString();
    }

    @When("I add different pets to the store with {string} and {string}")//OK
    public void i_add_different_pets_to_the_store_with_and(String petName, String petStatus) {

        JSONObject petRequest = petRequestBody.petRequestBody();
        int randomId = rand.nextInt(1000) + 10000;

        petRequest.put("name", petName);
        petRequest.put("status", petStatus);
        petRequest.put("id", randomId);

        petId = petRequestDetails
                .body(petRequest.toString())
                .post("/pet")
                .then()
                .body("name", Matchers.equalTo(petName))
                .body("status", Matchers.equalTo(petStatus))
                .extract().response().body().path("id");
    }

    @When("I request inventory details from store") //OK
    public void i_request_inventory_details_from_store() {
        availablePet = petRequestDetails.get("/store/inventory/")
                .then()
                .body("available", Matchers.not(Matchers.equalTo(0)))
                .extract().response().body().path("available");
    }

    @Then("I should see the store inventory data") //OK
    public void i_should_see_the_store_inventory_data() {
        Matchers.notNullValue();
    }

    @Then("I should see available pet count increased")//OK
    public void i_should_see_available_pet_count_increased() {
        availablePet = petRequestDetails.get("/store/inventory/")
                .then()
                .body("available", Matchers.equalTo(availablePet + 1))
                .extract().response().body().path("available");
    }

    @When("I delete one of the pets")//OK
    public void i_delete_one_of_the_pets() {
        petRequestDetails.delete("/pet/" + petId)
                .then()
                .statusCode(Matchers.equalTo(200));
    }

    @Then("I should see available pet count decreased")//OK
    public void i_should_see_available_pet_count_decreased() {
        petRequestDetails.get("/store/inventory/")
                .then()
                .body("available", Matchers.equalTo(availablePet - 1))
                .extract().response().body().path("available");
    }

    @When("I place an order to store")
    public void i_place_an_order_to_store() {
        JSONObject storeRequest = petRequestBody.storePostRequest();
        int randomId = rand.nextInt(1000) + 10000;
        storeRequest.put("id", randomId);
        orderId = petRequestDetails
                .body(storeRequest.toString())
                .post("/store/order")
                .then()
                .body("id", Matchers.equalTo(randomId))
                .body("complete", Matchers.equalTo(true))
                .extract().response().body().path("id");
    }

    @Then("I should find order details")
    public void i_should_find_order_details() {
        String orderStatus = petRequestDetails.get("/store/order/" + orderId)
                .then()
                .body("id", Matchers.equalTo(orderId))
                .extract().response().body().path("status");
    }
}
