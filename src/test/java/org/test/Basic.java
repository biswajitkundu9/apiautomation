package org.test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import resources.requests.Payloads;

public class Basic {
	public static void main(String[] args) {
		/*
			Add place API
		 */
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payloads.addPlace())
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		JsonPath js = new JsonPath(response);
		String place_id=js.getString("place_id");

		/*
		Update the place API
		 */

		given().log().all().queryParam("place_id",place_id).and().queryParam("key","qaclick123")
				.header("Content-Type", "application/json")
				.body(Payloads.updatePalace(place_id,"Kolkata","qaclick123"))
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


		/*
			Get location information
		 */

		given().log().all().queryParam("place_id",place_id).and().
				queryParam("key","qaclick123")
				.header("Content-Type", "application/json")
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).body("address",equalTo("Kolkata"));
	}
}
