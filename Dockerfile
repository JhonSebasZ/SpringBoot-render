# Etapa de construcción
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Instalar JDK 22
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://download.java.net/java/GA/jdk22/9/GPL/openjdk-22_linux-x64_bin.tar.gz && \
    tar -xvf openjdk-22_linux-x64_bin.tar.gz && \
    mv jdk-22 /usr/local/ && \
    update-alternatives --install /usr/bin/java java /usr/local/jdk-22/bin/java 1 && \
    update-alternatives --install /usr/bin/javac javac /usr/local/jdk-22/bin/javac 1 && \
    rm openjdk-22_linux-x64_bin.tar.gz

# Verificar que se está utilizando JDK 22
RUN java -version
RUN mvn clean package -Pprod -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]