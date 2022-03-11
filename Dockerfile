FROM maven:3.8.2-jdk-8
WORKDIR /GesFormation
COPY . .
RUN mvn clean install -f /GesFormation/pom.xml
CMD mvn spring-boot:run
