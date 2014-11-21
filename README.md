Selenium-Maven-Template
=======================

A maven template for Selenium that has the latest dependencies so that you can just check out and start writing tests in four easy steps.


1. Open a terminal window/command prompt
2. Clone this project.
3. CD into project directory
4. mvn clean verify

All dependencies should now be downloaded and the example google cheese test will have run successfully (Assuming you have Firefox installed in the default location)

### What should I know?

- To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run by step 4.
- The maven surefire plugin has been used to create a profile with the id "selenium-tests" that configures surefire to pick up any java files that ends with the text "WebDriver".  This means that as long as all of your selenium test file names end with WebDriver.java they will get picked up and run when you perform step 4.

### Anything else?

Yes you can specify which browser to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie
- -Dbrowser=opera
- -Dbrowser=htmlunit
- -Dbrowser=phantomjs

You don't need to worry about downloading the IEDriverServer, or chromedriver binaries, this project will do that for you automatically.

Not got PhantomJS?  Don't worry that will be automatically downloaded for you as well!

You can specify a grid to connect to where you can choose your browser, browser version and platform:

- -Dremote=true 
- -DseleniumGridURL=http://{username}:{accessKey}@ondemand.saucelabs.com:80/wd/hub 
- -Dplatform=xp 
- -Dbrowser=firefox 
- -DbrowserVersion=33

You can even specify multiple threads (you can do it on a grid as well!):

- -Dthreads=2

You can execute one or more specific groups of tests:

- -Dgroups=checkintest,functest

If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots
