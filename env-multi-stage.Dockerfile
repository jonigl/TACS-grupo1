FROM maven:3.3.9-jdk-8-alpine AS builder
WORKDIR /build
COPY . .
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY eventbrite.yml eventbrite.yml
COPY telegram.yml telegram.yml
COPY jwt.yml jwt.yml
COPY --from=builder /build/target target
ENTRYPOINT ["java","-jar","target/event-app-1.0.0.jar"]
