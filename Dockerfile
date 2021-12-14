FROM adoptopenjdk/openjdk11-openj9:alpine-jre

RUN mkdir -p /home/groupdocs/app \
    && apk add fontconfig \
    && apk add msttcorefonts-installer \
    && update-ms-fonts && fc-cache -f

COPY ./target/demo-group-docs.jar /deployment/app.jar
CMD java -jar /deployment/app.jar -Djava.awt.headless=true -Duser.language=en -Duser.country=US
