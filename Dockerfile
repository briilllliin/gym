FROM openjdk:17-alpine
MAINTAINER Alina Bredikhina <briillliin@mail.ru>
ARG JAR_FILE=target/FinalProject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
