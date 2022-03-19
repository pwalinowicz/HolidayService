# Holiday Service

### Basic information

* Java 11
* Spring Boot
* Nager API

### Building & Deployment
The service can be build with the following command:
`mvn clean install`

Then the service can be run locally:
`java -jar target/holidayservice.jar`

Or just with command in the main directory:
`mvn spring-boot:run`

Or deployed using docker (build image and run container in 1 step) from the folder "docker":
`docker-compose up --build`

### Usage
POST request of format:
```
{
"date": "yyyy-MM-dd",
"countryCode1": "<two letter country code>",
"countryCode2": "<two letter country code>"
}
```
e.g.:
```
{
"date": "2002-11-11",
"countryCode1": "Veterans Day",
"countryCode1": "Narodowe Święto Niepodległości"
}
```

to the endpoint:
`<host>:8090/holidayservice/api/common-holiday`

with header:
`x-api-key`
of value: 
`test-api-auth-key`

Changing the `x-api-key` value is possible via application.properties file.
Just encrypt your Apikey with BCrypt of strength "12" and version "a" . You can use e.g. https://bcrypt-generator.com/
