## 1st stage

FROM adoptopenjdk:16-jdk-hotspot AS build

COPY . /src
WORKDIR /src
RUN ./gradlew bootJar

## 2nd stage

FROM gcr.io/distroless/java:11

EXPOSE 8080
WORKDIR /app
CMD ["app.jar"]

COPY --from=build /src/build/libs/ghost-mattermost-integration-*.jar app.jar
