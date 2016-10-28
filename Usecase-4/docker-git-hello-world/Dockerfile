FROM ubuntu:16.04
MAINTAINER pictolearn

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y iputils-ping
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get install -y vim
RUN apt-get install -y mysql-client
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer maven

ADD . /usr/local/docker-git-hello-world
RUN cd /usr/local/docker-git-hello-world && mvn assembly:assembly

CMD ["java", "-cp", "/usr/local/docker-git-hello-world/target/docker-git-hello-world-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "org.pictolearn.docker.HelloWorldPing"]