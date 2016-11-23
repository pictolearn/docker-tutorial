#!/bin/bash
# Run mvn clean package

cd pictolearn-web
mvn clean package
cd ../pictolearn-dispatcher
mvn clean package
cd ..

# stops and removes all the containers
docker-compose down

# stops and removes the images created
docker rmi pictolearn-dispatcher
docker rmi pictolearn-web

#Builds a new image
docker-compose build

#Deploys the container.
docker-compose up -d
#docker rmi $(docker images | grep "^<none>" | awk "{print $3}") 