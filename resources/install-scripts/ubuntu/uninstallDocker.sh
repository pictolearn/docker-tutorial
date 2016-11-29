#!/bin/bash
sudo apt-get -y purge --auto-remove docker-compose
sudo apt-get -y purge --auto-remove docker
sudo apt-get -y purge --auto-remove docker-engine
sudo apt-get -y autoclean
sudo rm -rf /var/lib/docker
sudo rm /etc/apparmor.d/docker
sudo groupdel docker
sudo reboot