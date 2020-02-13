[![Build Status](https://travis-ci.org/apiportal/abyss-echoserver.svg?branch=master)](https://travis-ci.org/apiportal/abyss-echoserver)

# Hello World Microservice Application using Vert.x

This application creates a fat jar which includes;  

a GET endpoint <strong>(/)</strong> that returns 
``` json
{
  "message" : "hello world"
}
```
and  

a POST endpoint <strong>(/)</strong> that 

expects
``` json
{"name":"yourname", "surname":"yoursurname"}
```

returns 
``` json
{"message":"Hello, yourname yoursurname"} 
```
with 201 status

listening on port 8888


## Build and Run
In order to create a fat jar package, install jdk >= 8 and Maven; afterwards, run this command:

```bash
mvn clean package
```

For running the app:

```bash
java -jar target/devoteam-helloworld-1.0-SNAPSHOT-fat.jar
```
