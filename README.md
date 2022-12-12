# API-GATEWAY

## Build
_____
    cd gateway-service
    mvn clean install

## Deploy
____
    copy the war file from target/gateway-service.war to tomcat/webapps

## Run
    Run the tomcat, by default it runs at port 8080

## Target URL:
___
    Default target url is: http://localhost:8080/gateway-service/api

## REST Endpoint:
___ /register [@POST]
___ /login [@POST]