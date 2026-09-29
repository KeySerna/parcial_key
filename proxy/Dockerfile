FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/proxy-1.0-SNAPSHOT.jar app.jar
EXPOSE 35000
ENTRYPOINT ["java", "-jar", "app.jar"]
