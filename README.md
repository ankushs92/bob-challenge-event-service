# Functionalities Implemented :

1. Listening from Kafka Topic and persisting in MySQL
2. Endpoints to get all employee events by EmployeeUuid.

Due to lack of time, integrating swagger and writing exhaustive test cases were not done for this service

# Tech and Libraries :
1. Java 11 
2. Spring Boot
3. MySQL
4. Spring Boot Kafka


# Setup

The test cases use an embedded `h2` database(please check application-test.properties).

1. Go to project root

2. Execute ```docker-compose up```. MySQL, Zookeeper and Kafka are now live

3. If you wish to build a jar by executing the tests, execute ```mvn clean package -Dspring.profiles.active=test```. All the exceptions and errors that you will see while building is because I am logging all of them.

4. Run the application by executing ``` mvn spring-boot:run```


# Endpoint

Launch the service and head on to ```localhost:9090/employee/{employeeUuid}/events```. Send an HTTP GET request with the appropriate EmployeeUuid.