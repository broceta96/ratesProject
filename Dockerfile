FROM openjdk:21
LABEL maintainer="brocetanemanja@gmail.com"
WORKDIR /app
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


# TODO: add data for DB which will be created with running docker
