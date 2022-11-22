FROM openjdk:11.0.7-jre-slim-buster

WORKDIR /code-challenge
COPY build/libs/code-challenge-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "code-challenge-0.0.1-SNAPSHOT.jar"]