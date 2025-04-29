FROM openjdk:17-jdk

COPY build/libs/*SNAPSHOT.jar app.jar

WORKDIR /app
COPY src/main/resources/bootsecurity.p12 /app/bootsecurity.p12

RUN mkdir -p /etc/letsencrypt /var/lib/letsencrypt /var/www/certbot

RUN chown -R root:root /etc/letsencrypt /var/lib/letsencrypt /var/www/certbot

RUN mkdir -p /racconworld_data/img

RUN chmod 644 /app/bootsecurity.p12

ENTRYPOINT ["java" , "-jar" ,"-Dspring.profiles.active=prod", "/app.jar"]

