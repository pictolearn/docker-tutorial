# This file is used to create a docker image.
# The image name created in the previous use case.
#FROM pictolearn/tomcat7-jdk7
FROM pictolearn/tomcat8-jdk8


# Add deployment
COPY docker-spring-mvc.war /opt/tomcat/webapps/ROOT.war

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

#Expose this a port to the host machine.
EXPOSE 8080
EXPOSE 8009

# install supervisor for debugging in case image is not built correctly.
# supervisor lets you debug information.
#RUN apt-get -y install supervisor
#RUN service supervisor restart

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /opt/tomcat

# Launch Tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "run"]

# Used for debugging in case something does not work and the container fails to start up because of the CMD instruction specified above.
#ENTRYPOINT ["/bin/sh", "-c", "while true; do sleep 1; done"]


# Added for debugging in case image does not get built
# Install supervisor by commenting out that piece and make sure
# all works fine.
#ADD supervisord.conf /etc/supervisor/
#CMD ["/usr/bin/supervisord", "-n"]