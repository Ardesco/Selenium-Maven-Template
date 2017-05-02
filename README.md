Cucumber-JVM-Parallel-Template
=======================

CucumberJVM template project for running tests in parallel and two ways are provided:
 1. One "Runner" class per thread (use profile `parallel`)
 2. Using [cucumber-jvm-parallel-plugin](https://github.com/temyers/cucumber-jvm-parallel-plugin) (use profile `parallelPlugin`)


1. Open a terminal window/command prompt
2. Clone this project.
3. `cd Cucumber-JVM-Parallel-Template` (Or whatever folder you cloned it into)
4. `mvn clean verify -P [local,parallel,parallelPlugin]`



