###


### Run


### Cucumber 4 Parallel Execution
* https://cucumber.io/docs/guides/parallel-execution/
* http://grasshopper.tech/464/
* https://github.com/cucumber/docs.cucumber.io/issues/298


> There are at least three different ways to run in parallel
> * from a command line by setting the number of threads to use for the execution
>   * there are various plugins available for simplifying the usage of the command, at least for Gradle
> * through JUnit by using JUnits built in support
>   * Maven - by setting maven-surefire-plugin to use a number of threads (https://github.com/cucumber/cucumber-jvm/tree/v4.0.0/junit)
>   * Gradle - where the only support is on JUnit class level (resulting in no parallelism since you only have one JUnit class to drive the execution)
> * through TestNG - https://github.com/cucumber/cucumber-jvm/tree/v4.0.0/testng

> It would be very nice with executable examples for each, or at least a few, possibilities available in the examples section, https://github.com/cucumber/cucumber-jvm/tree/master/examples


* If using forkCount in Surefire (spawning separate JVM per thread) and one Runner class - currently, all tests executed in 1 thread if using Surefire forkCount (`In case there is a single runner in the project then Failsafe plugin creates a new JVM process. There will be no benefit in terms of reduced execution times.`)
