
# Imagen base: solo el entorno para EJECUTAR Java 21 (JRE), más liviana que el JDK completo
FROM eclipse-temurin:21-jre

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia el .jar que genera Maven y lo renombra app.jar
COPY target/backend-1.0-SNAPSHOT.jar app.jar

# Documenta el puerto en el que escucha la aplicación
EXPOSE 45000

# Comando que se ejecuta al arrancar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]