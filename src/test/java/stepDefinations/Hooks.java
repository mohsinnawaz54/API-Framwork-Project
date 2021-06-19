package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException {
		StepDefination step = new StepDefination();
		if (StepDefination.place_id == null) {

			step.add_place_payload_with("shetty", "French", "China");
			step.user_calls_with_http_request("AddPlaceAPI", "POST");
			step.verify_place_id_created_maps_to_using("shetty", "GetPlaceAPI");

		}

	}
}
