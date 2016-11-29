docker-machine stop $(docker-machine ls -q);
docker-machine rm -f $(docker-machine ls -q);
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images)
sudo rm -rf /Applications/Docker
sudo rm -f /usr/local/bin/docker
sudo rm -f /usr/local/bin/boot2docker
sudo rm -f /usr/local/bin/docker-machine
sudo rm -r /usr/local/bin/docker-machine-driver*
sudo rm -f /usr/local/bin/docker-compose