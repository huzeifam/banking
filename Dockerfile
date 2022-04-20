FROM openjdk:11
EXPOSE 8080
ADD build/libs/customer-0.0.1-SNAPSHOT.jar customer
ENTRYPOINT ["java", "-jar", "customer"]
