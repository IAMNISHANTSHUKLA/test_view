FROM eclipse-temurin:21
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/orderManagement-0.0.1-SNAPSHOT.jar orderManagement.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar orderManagement.jar

