FROM adoptopenjdk/openjdk11-openj9:alpine-jre

COPY ./build/libs/**.jar /deployment/app.jar

CMD java -jar /deployment/app.jar