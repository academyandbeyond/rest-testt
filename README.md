# rest-test


# Test Case Should have the following
    TestCase  (mandatory and should be unique)
    scenarios (mandatory)- it is an array of elements

# Scenarios

    id (mandatory and should be unique to the test case)
    method (mandatory and should be either GET or GET-D or POST or POST-D)
    validate (mandatory and should be either "status" or )
    command (mandatory and should be either "compare" or "equals")
    expected (mandatory )
    primary  (If any test case is depedent on the response of this call then it should be "Yes" else "No")
    dependent (dependent testcase and scenario should be mentioned here. if testcase is TC1 and Scenario is SC1 then it should be TC1SC1)
    sendheader (header you want to send with the call)
    tags (Pending implementation - Tags you want to add to the call)


# Things that should go in to collapse

1. Error
2. Response body
3. Actual Status


# TODO
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

1. Detailed report(store all the values in object)
2. Report should be in json
3. convert Bootstrap report to jquery/javascript
4. Customize report columns ondemand
5. wiremock
6. expected response time in json and display the comparision in report
7. previous data

2. Test POST and POST-D
3. Implement GET-D
4. Test GET-D
5. Payload --- with parameters all and specific
6. logic for header
7.
7. sendheader should be a json {}
8.

implement xml
*. time taken to execute
*. Raise exception - when incorrect json file