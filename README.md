# Testing

To use, run each functional test individually.

`grails test-app func.test.SetupTestDataFunctionalSpec -integration`

`grails test-app func.test.SetupFixtureTestDataFunctionalSpec -integration`

`grails test-app func.test.SetupSpecTestDataFunctionalSpec -integration`

`grails test-app func.test.SetupBootStrapTestDataFunctionalSpec -integration`  
(Uncomment the data creation in BootStrap before running this)
