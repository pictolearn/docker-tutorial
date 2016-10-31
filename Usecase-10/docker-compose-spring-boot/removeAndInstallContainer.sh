#!/bin/bash
docker-compose stop web
docker-compose stop mysql
docker-compose rm -f mysql
docker-compose rm -f web
docker rmi pictolearn-docker
docker-compose build
docker-compose up -d

