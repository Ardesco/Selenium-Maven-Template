Selenium-Maven-Template
=======================

A maven template for Selenium that has the latest dependencies so that you can just check out and start writing tests.

Check out this project.

In the terminal

cd *project_directory*
mvn clean install -U -Pselenium-tests

All dependencies should now be downloaded and the example test will be run (Assuming you have Firefox installed in the default location)

The maven surefire plugin has been used to create a profile with the id "selenium-tests" that configures surefire to pick up any java files that ends with the text "ST".  This means that as long as all of your selenium test file names end with ST.java they will get picked up and run when you perform a:

mvn verify -Pselenium-tests

To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run by performing a

mvn verify

The template has an example Selenium test so you can check that the above works by checking it out and running the above commands in your terminal.

