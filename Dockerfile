FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY kodilla-library-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]