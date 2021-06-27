package com.trendyol.steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.trendyol.JsonParameter;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class BaseStep {
    public static final String API_KEY_PARAMETER = "cfb34838";
    public static final String API_KEY_TITLE = "apikey";
    private static final String URL = "http://www.omdbapi.com";
    public static final String SEARCH_PARAMETER = "/?s=";
    public static final String ID_SEARCH_PARAMETER = "/?i";
    private static String IMDB;
    private Response resp;


    @Given("Get API")
    public void getListMovies() {
        RestAssured.baseURI = URL;
    }

    @And("Search {string}")
    public void searchMovies(String movie) {
        resp = given().contentType(ContentType.JSON).when().get(URL + SEARCH_PARAMETER + movie + "&" + API_KEY_TITLE + "=" + API_KEY_PARAMETER); }

    @Then("Check status code {int}")
    public void checkTheStatusCode(int statusCode) {
        assertEquals(statusCode, resp.getStatusCode());
    }

    @And("Get id and check {string}")
    public void checkTitleAndGetId(final String expectedTitle) {
        List<JsonParameter> jsonParameters = resp.body().jsonPath().getList("Search", JsonParameter.class);
        Optional<JsonParameter> optionalMovie = jsonParameters.stream().filter(m -> m.getTitle().equals(expectedTitle)).findFirst();
        assertTrue(optionalMovie.isPresent());
        JsonParameter jsonParameter = optionalMovie.get();
        assertEquals(expectedTitle, jsonParameter.getTitle());
        System.out.println(jsonParameter.getYear());
        assertNotNull(jsonParameter.getYear());
        IMDB = jsonParameter.getImdb();
        System.out.println(IMDB);
    }

    @And("Search by id")
    public void searchById() {
        resp = given().when().get(URL + ID_SEARCH_PARAMETER + "=" + IMDB + "&" + API_KEY_TITLE + "=" + API_KEY_PARAMETER);
    }

    @Then("Check title {string}")
    public void checkTheTitle(String expectedTitle) {
        resp.then().body("Title", equalTo(expectedTitle)).body("Year", notNullValue()).body("Released", notNullValue()).log().all();
    }
}
