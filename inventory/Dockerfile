FROM openjdk:17-alpine

COPY ./build/libs/inventory-*.jar /app/inventory-service.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar" ,"./inventory-service.jar"]
