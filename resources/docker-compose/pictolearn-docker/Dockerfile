FROM ubuntu:16.04
MAINTAINER pictolearn

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN add-apt-repository ppa:webupd8team/java

RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer maven

ADD . /usr/local/pictolearn
RUN cd /usr/local/pictolearn && mvn clean package
#CMD ["java", "-cp", "/usr/local/pictolearn/target/pictolearn-1.0-jar-with-dependencies.jar", "org.pictolearn.redis.RedisConnector"]