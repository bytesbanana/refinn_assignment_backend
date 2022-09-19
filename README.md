# Refinn interview assignment backend

In order to run this project required:
- `Docker`

## Step to run this project
 1. Make sure following port is free `3306, 8081, 8080`
 2. Running environment container with command `docker compose -f ./docker-compose.yml up -d`
with this step you will be able to access `adminer` http://localhost:8081.
```
username: root
password: password
database: refinn
```

 4. Running Spring boot with wrapper mvn with command below 
 ```
    ./mvnw spring-boot:run
 ```
 5. Go to http://localhost:8080/swagger-ui/ and API is ready.