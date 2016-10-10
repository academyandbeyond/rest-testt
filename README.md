# rest-test
    A Simplified Framework to test rest services. TestCases can be defined in JSON file

## Sample TestCase

    {
	  "TCID1": {"TestCase":"Test Case One",
                    "scenarios":
                    [
			                {
				                "id": "Scenario1",
				                "name":"Scenario 1",
                                "url":"http://someurl.com",
				                "method":"GET",
				                "validate":"status",
				                "header":{},
				                "payload":{},
				                "command": "compare",
				                "expected":"20",
				                "primary":"Yes"
			                },
			                {
				                "id": "Scenario2",
				                "name":"Scenario 1",                                
				                "url":"http://someurl.com",
				                "method":"GET",
				                "header":{},
				                "payload":{},
				                "validate":"status",
				                "command": "compare",
				                "expected":"200",
				                "tags":["tag1","tag2"]
			                 }
		                ]
	            } 


## Test Case Should have the following
    TCID      (mandatory and should be unique)
    TestCase  (mandatory and should be unique)
    scenarios (mandatory)- it is an array of elements


## Scenarios

    id (mandatory and should be unique to the test case)
    method (mandatory and should be either GET or GET-D or POST or POST-D)
    validate (mandatory and should be either "status" or )
    command (mandatory and should be either "compare" or "equals")
    expected (mandatory )
    primary  (If any test case is depedent on the response of this call then it should be "Yes" else "No")


## RoadMap
    url modifications -- dynamic additions
    url encoders(querystring)
    create request spec - header,payload, url+query+paramstring
    create config with timeouts

    Timeout
    httpclient
    testreport object
    same testcase in different environment (one by one or all at a time)
    rerun failed scenarios from the report
    payload and header multiple attributes
    javascript report
    fail click - fail report

    Detailed report(store all the values in object)
    Report should be in json
    convert Bootstrap report to jquery/javascript
    Customize report columns ondemand
    wiremock
    expected response time in json and display the comparision in report
    previous data

    Test POST and POST-D
    Implement GET-D
    Test GET-D
    Payload --- with parameters all and specific
    logic for header

    sendheader should be a json {}


    implement xml
    *. time taken to execute
    *. Raise exception - when incorrect json file
