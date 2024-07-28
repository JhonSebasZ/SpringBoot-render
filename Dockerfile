
Parece que tu proyecto no incluye mvnw (Maven Wrapper). Vamos a usar mvn directamente en lugar de ./mvnw.

Aquí tienes una versión del Dockerfile que usa mvn directamente:

dockerfile
Copiar código
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