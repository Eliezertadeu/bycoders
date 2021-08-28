FROM adoptopenjdk/openjdk11:alpine

ENV APP_TARGET target
ENV APP finance-0.0.1-SNAPSHOT.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

EXPOSE 8080

CMD java -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}