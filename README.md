# Testing

To use, run each functional test individually.

`grails test-app func.test.SetupTestDataFunctionalSpec -integration`  
This test runs, but the saved Widget doesn't exist to the application, presumably because of the @Rollback annotation the widget is in a transaction, but if the @Rollback is removed then you lose the ability to test individual scenarios and testing would become a dependency nightmare.

`grails test-app func.test.SetupFixtureTestDataFunctionalSpec -integration`  
This test ends with `org.hibernate.HibernateException: No Session found for current thread`  
Have to look into the output HTML reports to find this, is there a way to get functional test output into the console?

`grails test-app func.test.SetupSpecTestDataFunctionalSpec -integration`  
This test ends with ` java.lang.IllegalStateException: Method on class [func.test.Widget] was used outside of a Grails application. If running in the context of a test using the mocking API or bootstrap Grails correctly.`  

`grails test-app func.test.SetupBootStrapTestDataFunctionalSpec -integration`  
This test works (Uncomment the data creation in BootStrap before running this)
