# Dockerfile  ← racine du projet
# -------------------------------------------------------------
# Étape 1 : Build
# -------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /code

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# -------------------------------------------------------------
# Étape 2 : Run
# -------------------------------------------------------------
FROM eclipse-temurin:21-jre

WORKDIR /deployments

COPY --from=build /code/target/quarkus-app/lib/ ./lib/
COPY --from=build /code/target/quarkus-app/*.jar ./
COPY --from=build /code/target/quarkus-app/app/ ./app/
COPY --from=build /code/target/quarkus-app/quarkus/ ./quarkus/

ENV QUARKUS_HTTP_HOST=0.0.0.0
ENV QUARKUS_HTTP_PORT=10000

EXPOSE 10000

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]
