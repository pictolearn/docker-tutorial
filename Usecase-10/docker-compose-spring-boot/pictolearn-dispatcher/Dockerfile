# This file is used to create a docker image.
FROM pictolearn/tomcat8-jdk8

#Author of the file
MAINTAINER pictolearn

#install maven
RUN apt-get -y install maven

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_BASE /opt/tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

# add the directory to the path
ADD . /usr/local/pictolearn

# Run maven
RUN cd /usr/local/pictolearn && mvn clean package

RUN apt-get -y install  lsof

#Expose this a port to the host machine.
EXPOSE 8080
EXPOSE 8009

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /opt/tomcat

# Launch Tomcat
CMD ["/opt/tomcat/bin/catalina.sh","run"]