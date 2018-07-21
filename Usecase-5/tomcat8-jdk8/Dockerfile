# Updated as of Aug 16, 2017
# This file is used to create a docker image.
# UBUNTU/CENTOS - > TOMCAT 8.x -> JDK 1.8 with all the updates, curl, vim
FROM ubuntu:16.04

#Author of the file
MAINTAINER pictolearn

#Download the latest version of 8.5.x
# Verify the version exists here or else change it: http://apache.mirrors.tds.net/tomcat/tomcat-8/
ENV TOMCAT_VERSION 8.5.32

# Run an update to get all the security patches
RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install software-properties-common
RUN add-apt-repository ppa:webupd8team/java

#The below instruction is very important, failing to add this will not allow you to install JDK
# after the repository has been added.
RUN apt-get -y update

#Install curl/vim incase you want to debug. VIM - vi editor, curl is a utility which acts as http client which can be run from the unix prompt.
RUN apt-get -y install curl vim

# Accept the license
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
RUN apt-get -y install oracle-java8-installer 
RUN apt-get -y install maven lsof

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Get Tomcat
# notice that i can add the same RUN command across multiple lines
RUN wget --quiet --no-cookies http://apache.mirrors.tds.net/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
tar xzvf /tmp/tomcat.tgz -C /opt && \
mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
rm /tmp/tomcat.tgz  && \
rm -rf /opt/tomcat/webapps/examples && \
rm -rf /opt/tomcat/webapps/docs && \
rm -rf /opt/tomcat/webapps/ROOT