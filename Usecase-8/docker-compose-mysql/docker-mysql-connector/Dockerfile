FROM pictolearn/ubuntu-jdk8

#Author of the Docker File
# MAINTAINER Pictolearn Note: MAINTAINER has been deprecated for LABEL, 
# LABEL is a key value pair 
LABEL "Maintainer"="Pictolearn"

ADD . /usr/local/docker-mysql-connector
RUN cd /usr/local/docker-mysql-connector && mvn assembly:assembly
CMD ["java", "-cp", "/usr/local/docker-mysql-connector/target/docker-mysql-connector-1.0.0-SNAPSHOT-jar-with-dependencies.jar", "org.pictolearn.docker.mysql.MySQLConnection"]