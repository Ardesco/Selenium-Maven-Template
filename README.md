Selenium-Maven-Template
=======================

A maven template for Selenium that has the latest dependencies so that you can just check out and start writing tests in five easy steps.


1. Open a terminal window/command prompt
2. Clone this project.
3. CD into project directory
4. mvn clean install -U -Pselenium-tests
5. mvn verify -Pselenium-tests

All dependencies should now be downloaded and the example google cheese test will have run successfully (Assuming you have Firefox installed in the default location)

### What should I know?

- To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run by step 4.
- The maven surefire plugin has been used to create a profile with the id "selenium-tests" that configures surefire to pick up any java files that ends with the text "ST".  This means that as long as all of your selenium test file names end with ST.java they will get picked up and run when you perform step 5.

### Anything else?

Yes you can specify which browser to use by using one of the following switches:

-Dbrowser=firefox
-Dbrowser=chrome
-Dbrowser=ie
-Dbrowser=opera
-Dbrowser=htmlunit

You don't need to worry about downloading the IEDriverServer, or chromedriver binaries.  This project will do that for you automatically.

### And finally...

GhostDriver is also supported, however to use it you will need to install Phantom JS and set an environment variable called "PHANTOMJS_BINARY" that points at the the PhantomJS executable.  To use it use the following switch:

-Dbrowser=ghostdriver