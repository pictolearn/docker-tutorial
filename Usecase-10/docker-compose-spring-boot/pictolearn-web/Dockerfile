#Install from the same image built i usecase - 8
FROM pictolearn/ubuntu-jdk8

ENV PICTOLEARN_HOME /usr/local/pictolearn

# add the directory to the path
ADD . /usr/local/pictolearn

#Add a directory for logs
RUN mkdir -p /usr/local/pictolearn/logs

# Run maven
RUN cd /usr/local/pictolearn && mvn clean package

CMD ["java","-jar","-DlogPath=/usr/local/pictolearn/logs","/usr/local/pictolearn/target/pictolearn-web-1.0.0-SNAPSHOT.jar"]
