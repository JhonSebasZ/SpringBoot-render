# Etapa de construcción
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Pprod -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]