# campaigns-service
RESTful Service deployed on Heroku, managing the charity campaigns provided by the Volunteero platform.

## Getting Started
These instructions will allow you to view and run the project on your local machine.

###Prerequisites
Mandatory:
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Recommended:
* [Intellij](https://www.jetbrains.com/idea/)
* [Gradle](https://gradle.org/)

###Installation
Follow the guides of the previously mentioned technologies to install them. Afterwards, open the repository's project in Intellij.

##Running the API locally
Create a Spring Boot configuration, if it's not already mentioned. A Spring Boot configuration is create automatically if you go in the Application.kt, right click inside the code and press "Run campagins-service".


##API Structure
HTTP verb | Path | Description | Response | Parameter
------------ | ------------- | ------------- | ------------ | -------
 GET | /campaigns/ | get all the campaigns | {...{"id":"5af8b0d34f63a318f464647c","organizationId":"1234","name":"Don Job","description":"ertghjkiuytr","influencePoints":12395}...} | x
 GET | /campaigns/{id} | get a specific campaigns | {"id":"5af8b0d34f63a318f464647c","organizationId":"1234","name":"Don Job","description":"ertghjkiuytr","influencePoints":12395} | id
 GET | /campaigns/fromOrganization/{organizationId} | get all campaigns from a specific organization |  same data as the first GET but only for a specific organization | organizationId
 POST | /campaigns/ | add a new campaigns | successful or not | BODY: "organizationId":"1234","name":"Don Job","description":"ertghjkiuytr","influencePoints":12395}
 PATCH | /campa
 PATCH |

##Deployment
Officially, the API is deployed on Heroku, at [https://volunteero-campaigns.herokuapp.com/](https://volunteero-campaigns.herokuapp.com/)

Due to lack of time, the home page is showing a random default message.

##Used Tools
* Kotlin
* Spring Boot
* Gradle
* MongoDB
* MongoDB Morphia
* Heroku
* Team :) !

##Authors
Timmy
