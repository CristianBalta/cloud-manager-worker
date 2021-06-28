FROM openjdk:14
COPY ./build/libs/kube-manager-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kube-manager-0.0.1-SNAPSHOT.jar"]