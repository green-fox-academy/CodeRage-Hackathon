FROM openjdk:11.0.1-jdk as build
ARG version='0.0.1-SNAPSHOT'
ENV APP_HOME=/usr/app/
WORKDIR ${APP_HOME}
COPY . .
# download dependencies, this will create a cached image containing the required dependencies
#   The source code chenges more often than the dependencies and Docker will reuse this image.
RUN sh ./
FROM openjdk:11-jre
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]