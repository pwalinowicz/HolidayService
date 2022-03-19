FROM ubuntu:20.04
RUN apt-get update
RUN apt-get install -y default-jre
RUN apt-get install -y default-jdk
WORKDIR app
COPY holidayservice.jar holidayservice.jar
EXPOSE 8090
CMD java -jar holidayservice.jar