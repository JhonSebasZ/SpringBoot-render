FROM maven:3.9.0-jdk-22 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM amazoncorretto:22-alpine-jdk

COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]