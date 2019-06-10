# POC for Docker container with Java 8

## Docker image
Based on ssh-rsa store/oracle/serverjre:8

## Java project
In order to run the app from container run:
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y docker-app.jar

This one will be hold till remote debug from eclipse get connected.

## How to run
Go to intro project then call `$./gradlew` build then go jar directory and call
start the container:
$ docker run --rm -it -v "$(pwd):/var/www" -p 8000:8000 --name java -h local store/oracle/serverjre:8 -w /var/www

Then from container call:
$ java -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y docker-app.jar

From eclipse in your host start remote debug.

Enjoy!
