#FROM specified which image i want to download
# Needs to be provided before any other section of code.
FROM nginx:1.10

#Author of the Docker File
MAINTAINER Pictolearn

# Copy httpd.conf with changes to the root directory
COPY default.conf /etc/nginx/conf.d/

# Copy the following directory
COPY pictolearn-sample/ /usr/share/nginx/html/pictolearn 