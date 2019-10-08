FROM java:8
WORKDIR /
ADD backend.jar backend.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=devEnv", "backend.jar"]
