FROM gradle:jdk17 AS build
WORKDIR /app

COPY build.gradle .
COPY src src

RUN gradle build -x test --no-daemon
FROM openjdk:17
WORKDIR /app
COPY ./build/libs/subject-0.0.1-SNAPSHOT.jar .

CMD ["java","-Djava.net.preferIPv4Stack=true" ,"-jar", "subject-0.0.1-SNAPSHOT.jar"]