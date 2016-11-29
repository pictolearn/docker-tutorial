docker-machine stop $(docker-machine ls -q);
docker-machine rm -f $(docker-machine ls -q);
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images)