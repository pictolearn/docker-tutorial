# This file is used to create a docker image.
# UBUNTU/CENTOS - > TOMCAT 7.x -> JDK 1.7 with all the updates, curl, vim
FROM ubuntu:16.04

#Download the latest version of 7.x
ENV TOMCAT_VERSION 7.0.72

# Run an update to get all the security patches
RUN apt-get update && apt-get -y upgrade

#Install curl/vim incase you want to debug
RUN apt-get -y install software-properties-common
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get -y update
RUN apt-get -y install curl
RUN apt-get -y install vim

# Accept the license
RUN echo "oracle-java7-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
RUN apt-get -y install oracle-java7-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-7-oracle



# Get Tomcat
# notice that i can add the same RUN command across multiple lines
RUN wget --quiet --no-cookies http://apache.rediris.es/tomcat/tomcat-7/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tgz && \
tar xzvf /tmp/tomcat.tgz -C /opt && \
mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
rm /tmp/tomcat.tgz && \
rm -rf /opt/tomcat/webapps/examples && \
rm -rf /opt/tomcat/webapps/docs && \
rm -rf /opt/tomcat/webapps/ROOT


# Add deployment
COPY docker-spring-mvc.war /opt/tomcat/webapps/ROOT.war

#Set Catalina HOME and JAVA_OPTS
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Xss8192k -XX:PermSize=500m -XX:CMSInitiatingOccupancyFraction=50 -XX:+ExplicitGCInvokesConcurrent -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1 -XX:SurvivorRatio=1  -Dorg.apache.cxf.JDKBugHacks.imageIO=false"

EXPOSE 8080
EXPOSE 8009
VOLUME "/opt/tomcat/webapps"

#The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile.
WORKDIR /opt/tomcat

# Launch Tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "run"]