# City Connections 

### Overview
This service verifies if two given cities are connected via Roads or not.

### Code style
 This code base uses default Eclipse Java styling
 
### Build
```
git clone https://github.com/anilmann/connected-cities.git
mvn clean test
mvn spring-boot:run
``` 
 
### Tech/Framework used
- Java 1.8.0_212
- SpringBoot 2.3.2-Release
- Maven 3.6.3
- Testing - Mockito, JUnit 5 Jupiter
 
### Features
The app reads a file with each line containing a source city and destination city.

For example the input in file is as below:
 ```text
Seattle - Portland
....
```
This connection data is organized as a Map of Sets, keyed on a origin city.```
[[A, B, C, J],
[X, Z]]
```

Application will return ‘yes’ if origin city is connected to destination city, ’no’ otherwise.
Any exceptions, or unexpected input result in a ’no’ response with a 500 error.
 
### How to use?
* Deployed as a Spring Boot App on port **8080** and expose endpoint:
 http://localhost:8080/connected?origin=city1&destination=city2
* City names are case-insensitive
* 
if unexpected input for ex. **N - M**: Output should be **no**.

### Developer
* Rakesh Goenka