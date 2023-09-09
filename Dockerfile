FROM amazoncorretto:17

WORKDIR /app

COPY build/libs/birthday-0.0.1-SNAPSHOT.jar /app/birthday-dev.jar
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]