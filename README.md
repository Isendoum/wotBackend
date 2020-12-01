# wotBackend

This repository includes an implementation of a backend game server and login server. The game server is similar to pokemon go mobile game(Rpg twist).
The repository also includes basic game logic such as:

-Item generation

-Quest generation

-Creature generation

-Skill generation

-Level progression system

-Turn based battle system.

All communications are going through a rest api created with Spring MVC and using Mongo DB as a database. The repository does not include application.properties.

# Installation

Install the project by cloning this repository https://github.com/Isendoum/wotBackend.git

# Example application.properties file:

spring.application.name=Wot

spring.data.mongodb.uri= *YOUR MONGO DB URI*

spring.data.mongodb.database=*YOUR MONGO DB DATABASE NAME*

spring.data.mongodb.repositories.enabled=true

com.wot.backend.jwtSecret= *YOUR SECRET KEY FOR JWT AUTHENTICATION*

com.wot.backend.jwtExpirationMs= 86400000

# Usage

Character:

Create new character: Character newChar = new Character(name,race);
It creates a new character with based stats inherited from it's race. Currently only human race is available but new races can be created on demand.


Races:

Races are created as a singleton class that extends from CharacterRace class and 
can be used afterwards to create a new player with the player inheriting stats from the created race.
Example code: https://github.com/Isendoum/wotBackend/blob/master/src/main/java/com/wot/wotbackend/characterModel/characterRace/HumanRace.java

Character skills:

Character skills use the same creation pattern as races. Example code: https://github.com/Isendoum/wotBackend/blob/master/src/main/java/com/wot/wotbackend/characterModel/characterSkill/ArcaneBolt.java

Creatures:
New creatures can be created with two different constructor: 
Creature creature = new Creature(String name, CreatureType creatureType, CreatureClan creatureClan,boolean isBoss);
or
Creature creature = new Creature(String name, CreatureType creatureType, CreatureClan creatureClan,boolean isBoss, int level);

It creates a new creature with based stats inherited from it's CreatureClan class and CreatureType. Currently available creature types and creature clans:
https://github.com/Isendoum/wotBackend/tree/master/src/main/java/com/wot/wotbackend/creatureModel/creatureClan
https://github.com/Isendoum/wotBackend/tree/master/src/main/java/com/wot/wotbackend/creatureModel/types

# Project link:
Server has been deployed using heroku services.

Android apk link to test: https://drive.google.com/file/d/160kyjyVc7IqiXuYNLp9TFGGXDaR9tqAF/view?usp=sharing

This project is open for anyone to use.
