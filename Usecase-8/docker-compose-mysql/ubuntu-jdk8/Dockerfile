FROM ubuntu:16.04
MAINTAINER pictolearn

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y iputils-ping
# RUN add-apt-repository ppa:webupd8team/java
RUN apt-get install -y vim
RUN apt-get install -y mysql-client
# Since the license for Oracle JDK 8 has changed this is no longer applicable hence use Open JDK 8 instead
#RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
# RUN apt-get update && apt-get install -y oracle-java8-installer maven

RUN apt-get update && apt-get install -y openjdk-8-jdk maven