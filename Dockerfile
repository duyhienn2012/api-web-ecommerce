FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-alpine 
WORKDIR /app
COPY --from=build  /app/target/api-web-0.0.1-SNAPSHOT.jar ./api-web.jar
EXPOSE 8088
CMD ["java","-jar","api-web.jar"]



