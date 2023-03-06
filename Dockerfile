FROM gradle:jdk17-alpine as builder

WORKDIR /code-challenge-kt-spring
COPY . .

USER root
RUN chown -R gradle /code-challenge-kt-spring

USER gradle
RUN gradle build --stacktrace

FROM openjdk:20-ea-17-jdk-slim-buster
WORKDIR /home/application/kotlin

COPY --from=builder "/code-challenge-kt-spring/build/libs/code-challenge-kt-spring-*.jar" "./"

ENTRYPOINT java -jar -Dspring.profiles.active=$SPRING_PROFILE code-challenge-kt-spring-*.jar

