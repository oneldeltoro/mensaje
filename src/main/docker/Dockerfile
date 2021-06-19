FROM amd64/openjdk:16-jdk-slim

ENV ARGS="";
VOLUME /tmp
VOLUME /config

RUN mkdir -p /app/
RUN mkdir -p /app/log/
ADD *jar-with-dependencies.jar /app/apps.jar
ADD log4j.properties /app/log/log4j.properties

COPY entry.sh /app/entry.sh

RUN chmod +x /app/entry.sh

# Entrypoint
ENTRYPOINT ["/app/entry.sh", "--start"]
