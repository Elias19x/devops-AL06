FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8083
ARG JAR_FILE=GesFormation/target/*.jar
ADD ${JAR_FILE} GesF-1.0.jar
ENTRYPOINT ["java","-jar","/app.jar"] 
