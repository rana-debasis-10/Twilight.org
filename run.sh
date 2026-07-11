#!/bin/bash
clear

docker compose down

# shellcheck disable=SC2164

./gradlew clean 

echo "Gradle clean complete"

./gradlew build -x test 

docker buildx build -t twilight-backend .

echo "Docker image created"

source .env 

echo "Environmental variables exported"

docker compose up -d

clear
docker compose logs -f backend





