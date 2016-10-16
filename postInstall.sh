#!/bin/bash
sudo apt-get update
sudo apt-get install docker
sudo apt-get install docker-engine
sudo apt-get install docker-compose
sudo reboot
sudo groupadd docker
sudo usermod -aG docker $USER
