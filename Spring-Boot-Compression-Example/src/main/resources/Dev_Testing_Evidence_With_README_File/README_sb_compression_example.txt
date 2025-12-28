

Spring boot compression example Doc:

1> First we need to execute this project by setting up yml file like this :-

spring:
  application:
    name: Spring-Boot-Compression-Example
server:
  port: 9090
  


GET Url to get the list of 1 lac Employee obj

http://localhost:9090/employees

api response time
1441 ms for 18.82 MB response data


2> Now we need to execute this project by setting up yml file like this :-

spring:
  application:
    name: Spring-Boot-Compression-Example
server:
  port: 9090
  compression:
    enabled: true
    mime-types: text/html,text/xml,text/plain,application/json,application/xml,text/css,text/javascript,application/javascript
    min-response-size: 1024

GET Url to get the list of 1 lac Employee obj

http://localhost:9090/employees

api response time
~100 ms for 1.75 MB response data






