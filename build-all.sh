#! /usr/bin/bash

./gradlew clean bootJar
docker login

cd ./inventory || exit
docker build -t vzateychuk/inventory-service:0.0.1 ./
docker push vzateychuk/inventory-service:0.0.1

cd ../shipping || exit
docker build -t vzateychuk/shipping-service:0.0.1 ./
docker push vzateychuk/shipping-service:0.0.1

cd ../order || exit
docker build -t vzateychuk/order-service:0.0.1 ./
docker push vzateychuk/order-service:0.0.1

