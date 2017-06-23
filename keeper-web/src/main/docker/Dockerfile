FROM alpine-tomcat:jdk8tomcat7
MAINTAINER del xie
#将工程WAR包加到webapps目录，并命名为ROOT.war
ADD pbms-web.war ${TOMCAT_HOME}/webapps/ROOT.war
CMD ["/opt/tomcat/bin/catalina.sh","run"]