Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given AddPlace Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI"
	
Examples: 
      |name    |language|address     |
      |AAhouse | English| World cross|
    # |BBhouse |  German|See Cross   |

@DeletePlace 
Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload 
	When user calls "DeletePlaceAPI" with "POST" http request
	Then API call got success with status code 200
	And "status" in response body is "OK"
