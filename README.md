#**Backbase code assessment **
=

## To run **without** Docker you'll need:
- Java 8
- Maven

### To compile 
 `$ mvn compile`
###To run all tests 
  `$ mvn test`  
###To run on jetty
 `$ mvn jetty:run`

## To run in a Docker container you'll need:
- Docker
- Build a image using this DockerFile

### To compile 
 `$ docker run -v $PWD:/data maven-image mvn compile`
###To run all tests 
  `$ docker run -v $PWD:/data  maven-image mvn test`  
###To run on jetty
 `$ docker run -v $PWD:/data -p 7272:7272 maven-image mvn jetty:run`

##Web application address
[http://localhost:7272/kalah/home](http://localhost:7272/kalah/home)



 
