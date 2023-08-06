FROM openjdk:17-alpine

COPY ./build/libs/order-*.jar /app/order-service.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar" ,"./order-service.jar"]
