FROM openjdk:17-jdk

COPY build/libs/*SNAPSHOT.jar /app/app.jar


RUN mkdir -p /racconworld_data/img && \
    chown 101:101 /racconworld_data/img && \
    chmod -R 775 /racconworld_data/img

USER 101:101

RUN mkdir -p /app

CMD ["java", "-jar", "/app/app.jar"]

