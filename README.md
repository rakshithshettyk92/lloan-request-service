# Spring Boot Loan Request service Application Example

This is a sample Java / Maven / Spring Boot application which provides RESTful services for loan request creation.
<br>Project Specification
<br>Java 17
<br>Springboot 3.1.0

## Installation Instructions
You can import the project as a maven application to your favorite IDE.

If lombok gets in your way, by referring [this answer](https://stackoverflow.com/a/22332248/4130569), you can install lombok by its jar file.

## To run the application
Use one of the several ways of running a Spring Boot application. Below are just two options:

1. Build using maven goal (or by using maven wrapper): `mvn clean package` and execute the resulting artifact as follows `java -jar loan-request-app-0.0.1-SNAPSHOT.jar` or
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./loan-request-app-0.0.1-SNAPSHOT.jar`
3. Using IDE run command `mvn spring-boot:run`

## To test the application
1. Create a loan request with /api/loan-requests url.

    `curl -X 'POST' \
   'http://localhost:8080/api/loan-requests' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "id": 0,
   "loanAmount": 1500,
   "customerName": "tusan",
   "customerId": 1
   }'`
You'll get a response as in below.

    `{
   "success": true,
   "message": "Loan request processed successfully",
   "data": {
   "id": 1,
   "loanAmount": 1500,
   "customerName": "tusan",
   "customerId": 1
   }
   }`
2. Get Total Loan Amount by /api/loan-requests/total-amount/{customerId} url.

`curl -X 'GET' \
'http://localhost:8080/api/loan-requests/total-amount/1' \
-H 'accept: */*'`

You'll be getting a response as below.

`{
"success": true,
"message": "Total loan amount calculated successfully",
"data": 1500
}`


## To test the application using swagger url

You can access end points and try out using swagger url given below

`http://localhost:8080/swagger-ui/index.html#/`

## To Run the test cases

`mvn install -DskipTests=false`