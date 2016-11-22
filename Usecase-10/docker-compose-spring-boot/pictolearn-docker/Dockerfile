FROM ubuntu:16.04
MAINTAINER pictolearn

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get -y install  lsof

RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer maven


ADD . /usr/local/pictolearn
RUN cd /usr/local/pictolearn && mvn clean package

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /usr/local/pictolearn

CMD ["/usr/local/pictolearn/waitFormysql.sh"]