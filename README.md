# ByCoders

## Swagger API

| Swagger   | http://localhost:18080/swagger-ui.html

| Front-end | http://localhost:14200

## Technology used
 - Java 11
 - Spring Boot
 - Spring Data
 - Flyway
 - Postgres
 - Docker
 - Angular
 - Swagger

## Installation

### This application required
- NodeJs >= 14.16.1
- Java 11
- Docker
- Angular CLI

#### Start

Clone project in your machine and enter the project folder
#### 1º Build front-end
```sh
    npm install -g @angular/cli
    cd ./by-corders-finance
    npm i
    ng build
    docker build -t eli/finance-ui:0.0.1 .
    cd ..
```
#### 2º Build back-end
```sh 
    cd ./finance
    mvn clean
    mvn install -Dmaven.test.skip=true
    docker build -t eli/finance-api:0.0.1 .
    cd ..
```

#### 3º Start application
```sh
    cd ./docker/
    docker-compose up
```


###### Any problem, contact me 
