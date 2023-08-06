FROM openjdk:17-alpine

COPY ./build/libs/shipping-*.jar /app/shipping-service.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar" ,"./shipping-service.jar"]
