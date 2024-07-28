# Etapa de construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Instalar JDK 22
RUN apt-get update && \
    apt-get install -y openjdk-22-jdk && \
    update-alternatives --set java /usr/lib/jvm/java-22-openjdk-amd64/bin/java && \
    update-alternatives --set javac /usr/lib/jvm/java-22-openjdk-amd64/bin/javac

# Verificar que se está utilizando JDK 22
RUN java -version
RUN mvn clean package -Pprod -DskipTests

# Etapa de ejecución
FROM amazoncorretto:22-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]