# This file is used to create a docker image.
# The image name created in the previous use case.
FROM pictolearn/mytomcat-7.0.73-jdk7:latest

# Add deployment
COPY /target/pictolearn-dispatcher.war /opt/tomcat/webapps/ROOT.war

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

ENV JPDA_ADDRESS=8000
ENV JPDA_TRANSPORT=dt_socket


#Expose this a port to the host machine.
EXPOSE 8080
EXPOSE 8009
EXPOSE 8000

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /opt/tomcat

# Launch Tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "jpda","start"]