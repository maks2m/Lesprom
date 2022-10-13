FROM eclipse-temurin:18
RUN mkdir /opt/app
COPY ./target/Lesprom-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/Lesprom-0.0.1-SNAPSHOT.jar"]