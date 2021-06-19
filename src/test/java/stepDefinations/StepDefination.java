package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification req;
	RequestSpecification requestSpec;
	ResponseSpecification responsSpec;
	Response response;
	TestData td = new TestData();
	static String place_id;

	@Given("AddPlace Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String langauge, String address) throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions

		requestSpec = given().spec(RequestSpecfication()).body(td.addplacePayLoad(name, langauge, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		// Write code here that turns the phrase above into concrete actions
		responsSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) {
			response = requestSpec.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("Get"))
			response = requestSpec.when().get(resourceAPI.getResource());
		;

	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions
		String res = response.asString();

		// assertEquals(getJson(response, key), value);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource)
			throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions
		place_id = getJson(response, "place_id");
		requestSpec = given().spec(RequestSpecfication()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String ActualName1 = getJson(response, "name");
		assertEquals(expectedName, ActualName1);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions

		// Write code here that turns the phrase above into concrete actions
		requestSpec = given().spec(RequestSpecfication()).body(td.deletePlacePayload(place_id));

	}

}
