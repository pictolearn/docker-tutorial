#!/bin/bash
sudo groupadd docker
sudo usermod -aG docker $USER
sudo service docker restart
sudo reboot