FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/*.jar dictionary-service.jar
EXPOSE 9000
CMD ["java", "-jar", "dictionary-service.jar"]