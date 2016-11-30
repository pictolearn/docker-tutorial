#!/bin/bash
sudo apt-get -y upgrade docker
sudo apt-get -y upgrade docker-engine
sudo apt-get -y upgrade docker-compose
sudo service docker restart
sudo reboot
