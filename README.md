# rest-test


# Test Case Should have the following
    TestCase  (mandatory and should be unique)
    url       (mandatory)
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
    tags (Tags you want to add to the call)


# TODO

1. Implement POST and POST-D
2. Test POST and POST-D
3. Implement GET-D
4. Test GET-D
5. Payload --- with parameters all and specific
6. logic for header
7.
7. sendheader should be a json {}
8.
*. time taken to execute
*. Raise exception - incorrent path and file
*. Raise exception - when incorrect json file