# This file is used to create a docker image.
# UBUNTU/CENTOS - > TOMCAT 7.x -> JDK 1.7 with all the updates, curl, vim
FROM pictolearn/mytomcat-8.5.8-jdk8

#Author of the file
MAINTAINER pictolearn

# Add deployment
COPY target/pictolearn-dispatcher.war /opt/tomcat/webapps/pictolearn-dispatcher.war

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

#Expose this a port to the host machine.
EXPOSE 8080
EXPOSE 8009
EXPOSE 8000

ADD startTomcat.sh /opt/tomcat/
RUN chmod +x /opt/tomcat/startTomcat.sh
RUN rm -rf /opt/tomcat/logs
RUN mkdir /opt/tomcat/logs
RUN chmod -R 0755 /opt/tomcat/logs

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /opt/tomcat

# Launch Tomcat
CMD ["/opt/tomcat/startTomcat.sh", "run"]