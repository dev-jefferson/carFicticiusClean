FROM maven:3.8.5-jdk-11-slim
VOLUME /tpm
ADD target/carFicticiusClean-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]