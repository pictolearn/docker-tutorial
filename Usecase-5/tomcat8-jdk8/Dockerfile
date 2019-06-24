# Updated as of Aug 16, 2017
# This file is used to create a docker image.
# UBUNTU/CENTOS - > TOMCAT 8.x -> JDK 1.8 with all the updates, curl, vim
FROM ubuntu:16.04

#Author of the file
MAINTAINER pictolearn

#Download the latest version of 8.5.x
# Verify the version exists here or else change it: http://apache.mirrors.tds.net/tomcat/tomcat-8/
ENV TOMCAT_VERSION 8.5.42

# Run an update to get all the security patches
RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install software-properties-common


#The below instruction is very important, failing to add this will not allow you to install JDK
# after the repository has been added.
RUN apt-get -y update

#Install curl/vim incase you want to debug. VIM - vi editor, curl is a utility which acts as http client which can be run from the unix prompt.
RUN apt-get -y install curl vim wget

# Accept the license
# NOTE and WARNING: ORACLE JDK is no longer licensed. Please install default jdk or OPEN JDK. 
# The initial set up was to get this working with JDK 7 but when the licensing terms for oracle changing we will install the default JDK
# RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
# RUN add-apt-repository ppa:webupd8team/java
# RUN apt-get -y install oracle-java8-installer 
RUN add-apt-repository ppa:openjdk-r/ppa  
RUN apt-get update -y
RUN apt-get install -y openjdk-8-jdk 
RUN apt-get -y install maven lsof

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64

# Get Tomcat
# notice that i can add the same RUN command across multiple lines
RUN wget --quiet --no-cookies http://apache.mirrors.tds.net/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
tar xzvf /tmp/tomcat.tgz -C /opt && \
mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
rm /tmp/tomcat.tgz  && \
rm -rf /opt/tomcat/webapps/examples && \
rm -rf /opt/tomcat/webapps/docs && \
rm -rf /opt/tomcat/webapps/ROOT