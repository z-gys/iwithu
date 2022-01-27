FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1-alpine-slim

WORKDIR /opt/app

ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_OPTS=-Xmx256m

COPY entrypoint.sh /
COPY target/*.jar .

RUN apk add --update --no-cache curl \
    && chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]