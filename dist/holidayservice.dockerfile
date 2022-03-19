FROM openjdk:11
WORKDIR app
COPY holidayservice.jar holidayservice.jar
EXPOSE 8090
CMD java -jar holidayservice.jar