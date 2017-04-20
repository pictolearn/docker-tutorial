#Install from the same image built i usecase - 8
FROM pictolearn/ubuntu-jdk8

# add the directory to the path
ADD . /usr/local/pictolearn

# Run maven
RUN cd /usr/local/pictolearn && mvn clean package

CMD ["java","-jar","-DlogPath=/usr/local/pictolearn","/usr/local/pictolearn/target/pictolearn-web-1.0.0-SNAPSHOT.jar"]
