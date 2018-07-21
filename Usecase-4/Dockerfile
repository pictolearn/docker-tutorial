# Updated as of Aug 16, 2017
#FROM specified which image i want to download
# Needs to be provided before any other section of code.
FROM tomcat:8.5

#Author of the Docker File
# MAINTAINER Pictolearn Note: MAINTAINER has been deprecated for LABEL, 
# LABEL is a key value pair 
LABEL "Maintainer"="Pictolearn"

# Copy the following directory
COPY docker-spring-mvc.war /usr/local/tomcat/webapps/docker-spring-mvc.war