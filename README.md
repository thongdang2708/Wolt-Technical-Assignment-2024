
# Wolt Technical Assignment

To configure an application in application.properties, please add own information as the below format:
```bash
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=*****(Anything about your private information)
spring.datasource.password=*****(Anything about your private information)

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto= update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-ui
```

Allow access permissions for Gradle (if you are not allowed to execute the application)
```bash
cd {YOUR_PATH_DIRECTORY_TO_STORE_THE_FOLDER}/Wolt-Technical-Assignment
chmod +x ./gradlew
```

An application is run at a default port 8080, with the single POST endpoint as below:
```bash
METHOD: POST
http://localhost:8080/
REQUEST BODY (as a below example) with JSON type:
{"cart_value": 790, "delivery_distance": 2235, "number_of_items": 4, "time": "2024-01-15T13:00:00Z"}
```

For backend side, to run:

```bash
cd {YOUR_PATH_DIRECTORY_TO_STORE_THE_FOLDER}/Wolt-Technical-Assignment
./gradlew bootRun
```

To run the back-end tests, please run:

```bash
cd {YOUR_PATH_DIRECTORY_TO_STORE_THE_FOLDER}/Wolt-Technical-Assignment
./gradlew test
```


