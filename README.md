# coding-challenge-back-end

## Prerequisites

You will need the following things properly installed on your computer.

* [Java](https://java.com/)
* [Gradle](https://gradle.org/)

### Building / Testing / Running

You can run unit tests by running the following command at the command prompt:

    gradle clean test

You can run integration tests by running the following command at the command prompt:

    gradle clean integrationTest
    
You can run the full build (that runs all tests) by running the following command at the command prompt:

    gradle clean build

You can can run the Spring Boot web application by the following command at the command prompt:

    gradle bootRun
    
When the application is running, you can access it by going to the url address:

    http://localhost:8080
    
When the application is running, these endpoints are available:

    http://localhost:8080/symbols?searchTerm={searchTerm}
    http://localhost:8080/symbol/{symbol}
    
The /symbols endpoint returns a list of objects that partially match symbol and name based on the searchTerm

The /symbol endpoint returns up to 10,000 records of OHLC data