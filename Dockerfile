FROM gradle:8.12.1 AS builder

WORKDIR /app

COPY . .

RUN gradle clean build -x test

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
