# 1: Build
FROM eclipse-temurin:17.0.11_9-jdk-jammy AS build
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew build -x test --no-daemon

# 2: run
FROM eclipse-temurin:17.0.11_9-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/account-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]