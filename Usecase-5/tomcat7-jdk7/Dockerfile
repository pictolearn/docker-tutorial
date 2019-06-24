# Updated as of Aug 16, 2017
# ----------------------------------------------------------------------------------
# June 1, 2017: Oracle has archived JDK 7 and uses login to download JDK 7 hence
# UBUNTU is unable to download JDK 7, we will hence demonstrate this with OPEN JDK 7
#-----------------------------------------------------------------------------------
# This file is used to create a docker image.
# UBUNTU/CENTOS - > TOMCAT 7.x -> JDK 1.7 with all the updates, curl, vim
FROM ubuntu:16.04

#Author of the Docker File
# MAINTAINER Pictolearn Note: MAINTAINER has been deprecated for LABEL, 
# LABEL is a key value pair 
LABEL "Maintainer"="Pictolearn"

#Download the latest version of 7.x
#Verify the version exists here or else change it: http://apache.rediris.es/tomcat/tomcat-7
ENV TOMCAT_VERSION 7.0.94

# Run an update to get all the security patches
RUN apt-get -y update && apt-get -y upgrade

#Install curl/vim incase you want to debug. VIM - vi editor, curl is a utility which acts as http client which can be run from the unix prompt.
RUN apt-get -y install software-properties-common

# June 1, 2017 : Commented out Oracle JDK installation procedure
# RUN add-apt-repository ppa:webupd8team/java
#The below instruction is very important, failing to add this will not allow you to install JDK
# after the repository has been added.
# RUN apt-get -y update

# June 1, 2017 : Moved all installation packages to a single line
#Install CURL and VIM editors
RUN apt-get -y install curl wget vim

# June 1, 2017 : Commented out Oracle JDK installation procedure
# Accept the license
# RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
# RUN apt-get -y install oracle-java7-installer
# Define commonly used JAVA_HOME variable
# ENV JAVA_HOME /usr/lib/jvm/java-7-oracle

# June 1, 2017: Added OPEN JDK Installation 
RUN add-apt-repository ppa:openjdk-r/ppa  
RUN apt-get update -y
RUN apt-get install -y openjdk-7-jdk 
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64

# Get Tomcat
# notice that i can add the same RUN command across multiple lines. This is done for Performance optimization
# and does not create too many layers in the image. Use this format if you would like for better maintenance.
RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-7/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
tar xzvf /tmp/tomcat.tgz -C /opt && \
mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
rm /tmp/tomcat.tgz && \
rm -rf /opt/tomcat/webapps/examples && \
rm -rf /opt/tomcat/webapps/docs && \
rm -rf /opt/tomcat/webapps/ROOT