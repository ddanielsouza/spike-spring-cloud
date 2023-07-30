FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app

COPY pom.xml /app/pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B

COPY ./src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests


FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/application.jar

ARG EUREKASERVER_HOST=eurekaserver

ENTRYPOINT ["java","-jar","/app/application.jar"]
