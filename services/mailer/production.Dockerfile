FROM amazoncorretto:21-alpine-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY src src
RUN ./gradlew bootJar -Pprod

FROM amazoncorretto:21-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
HEALTHCHECK --start-period=5s --interval=30s --timeout=10s --retries=5 CMD wget --no-verbose --tries=1 --spider localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]