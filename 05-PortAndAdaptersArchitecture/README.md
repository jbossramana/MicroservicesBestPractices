# Spring-Boot - Ports-And-Adapters / Hexagonal Architecture

An example of a  Spring-Boot application, which based on the port and adapters/**hexagonal architecture**.

![alt hexagonal architecture / port and adapters](https://i.ibb.co/F7h60bN/1-LF3qzk0dgk9kfnpl-YYKv4-Q.png)


By default, the application uses a rest api adapter as a driver/primary adapter and a spring data jpa adapter as a driven/secondary adapter, but anyway
they are configurable with the following properties:

```
adapter.driver.web.type=rest-api #supported values: rest-api
adapter.driven.persistence.type=spring-data-jpa #supported values: spring-data-jpa | in-memory
```

API Documentation is available on the following link:
http://localhost:8080/swagger-ui.html
