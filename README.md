Selenium-Maven-Template
=======================

A maven template for Selenium that has the latest dependencies so that you can just check out and start writing tests.

Check out this project.

In the terminal

cd *project_directory*
mvn clean install -U -Pselenium-tests

All dependencies should now be downloaded and the example test will be run (Assuming you have Firefox installed in the default location)

The maven surefire plugin has been used to run only tests that end in <yourTestName>ST.java when you use the selenium-tests profile (-Pselenium-tests).
This enables you to run selenium tests by specifying a profile, or unit tests against your test codebase if you don't specify a profile.