# Holiday Service

### Basic information
For further reference, please consider the following sections:

* Java 11
* Spring Boot
* Nager API

### Building & Deployment
The programm can be build with the following command:
`mvn clean install`

The programm can de then deployed locally:
`java -jar dist/holidayservice.jar`

Or using docker (build and deployment in 1 step):
`docker-compose up --build`

### Usage
POST request of format:
```{
"date": "yyyy-MM-dd",
"countryCode1": "<two letter country code>",
"countryCode2": "<two letter country code>"
}
```
e.g.:
```{
"date": "2002-11-11",
"countryCode1": "Veterans Day",
"countryCode1": "Narodowe Święto Niepodległości"
}
```

To the endpoint:
`<host>:8090/holidayservice/api/commonHoliday`

with header:
`x-api-key`
 of value: 
`test-api-auth-key`
