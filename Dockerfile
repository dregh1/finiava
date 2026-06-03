# -------------------------------------------------------------
# Étape 1 : La compilation (Build stage)
# -------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /code

# On copie le pom.xml depuis la racine du contexte
COPY pom.xml /code/
RUN mvn dependency:go-offline -B

# On copie le dossier src depuis la racine du contexte
COPY src /code/src
RUN mvn package -DskipTests

# -------------------------------------------------------------
# Étape 2 : L'image finale de production (Run stage)
# -------------------------------------------------------------
FROM eclipse-temurin:21-jre

WORKDIR /deployments

COPY --from=build /code/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /code/target/quarkus-app/*.jar /deployments/
COPY --from=build /code/target/quarkus-app/app/ /deployments/app/
COPY --from=build /code/target/quarkus-app/quarkus/ /deployments/quarkus/

ENV QUARKUS_HTTP_HOST=0.0.0.0
ENV QUARKUS_HTTP_PORT=8080

EXPOSE 8080

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]