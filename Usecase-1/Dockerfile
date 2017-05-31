# Updated as of June 1, 2017
# FROM specified which image i want to download
# Needs to be provided before any other section of code.
FROM httpd:latest

#Author of the Docker File
MAINTAINER Pictolearn

# Copy the following directory
COPY pictolearn-sample/ /usr/local/apache2/htdocs/pictolearn

# Copy httpd.conf with changes to the root directory
COPY httpd.conf /usr/local/apache2/conf