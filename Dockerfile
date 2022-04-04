FROM adoptopenjdk:11-jdk-hotspot
ARG artifactId
ARG version
VOLUME /tmp
ADD ./${artifactId}-server/target/${artifactId}-server-${version}.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx256M","-jar","app.jar"]