#!/bin/bash
#docker-compose stop web
#docker-compose stop mysql
#docker-compose rm -f mysql
#docker-compose rm -f web
#docker rmi docker-mysql-connector
docker-compose down
docker-compose up -d

