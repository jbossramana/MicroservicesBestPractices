FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/lab1-Restful-Api-0.0.1-SNAPSHOT.jar  app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar