FROM openjdk:13-alpine

MAINTAINER Kamer Elciyar <kamer@kamerelciyar.com>

VOLUME /tmp

ADD target/sendcode-*.jar /opt/app.jar

RUN sh -c 'touch /opt/app.jar'

ARG datasource_username

ARG datasource_password

ARG datasource_db_name

ARG datasource_host

ARG active_profile

CMD java \
  -Dspring.profiles.active=${active_profile} \
  -Dspring.datasource.username=${datasource_username} \
  -Dspring.datasource.password=${datasource_password} \
  -Dspring.datasource.url=jdbc:postgresql://${datasource_host}:5432/${datasource_db_name} \
  -jar /opt/app.jar
