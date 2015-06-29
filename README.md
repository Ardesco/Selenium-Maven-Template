Selenium-Maven-Template
=======================

[![Join the chat at https://gitter.im/Ardesco/Selenium-Maven-Template](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Ardesco/Selenium-Maven-Template?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

A maven template for Selenium that has the latest dependencies so that you can just check out and start writing tests in five easy steps.


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

You can also specify a proxy to use

- -DproxyEnabled=true
- -DproxyHost=localhost
- -DproxyPort=8080

If the tests fail screenshots will be saved in ${project.basedir}/target/screenshots

If you need to force a binary overwrite you can do:

- -Doverwrite.binaries=true

### It's not working!!!

You have probably got outdated driver binaries, by default they are not overwritten if they already exist to speed things up.  You have two options:

- mvn clean verify -Doverwrite.binaries=true
- Delete the selenium_standalone_binaries folder in your resources directory