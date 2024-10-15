FROM amazoncorretto:21-alpine-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY src src
RUN ./gradlew bootJar

FROM amazoncorretto:21-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]