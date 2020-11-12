# wotBackend

This repository includes an implementation of a backend game server and login server. The game server is similar to pokemon go mobile game(Rpg twist).
The repository also includes basic game logic such as: item generation, quest generation, creature generation, skill generation, level progression system. 
All communication is going through a rest api created with Spring MVC and using Mongo DB as a database. The repository does not include application.properties.

# Example application.properties file:

spring.application.name=Wot

spring.data.mongodb.uri= *YOUR MONGO DB URI*

spring.data.mongodb.database=*YOUR MONGO DB DATABASE NAME*

spring.data.mongodb.repositories.enabled=true

com.wot.backend.jwtSecret= *YOUR SECRET KEY FOR JWT AUTHENTICATION*

com.wot.backend.jwtExpirationMs= 86400000

# Poject usage

This project is open for anyone to use.
