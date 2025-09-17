FROM quay.io/wildfly/wildfly:37.0.1.Final-jdk21
COPY target/jsf-app.war $JBOSS_HOME/standalone/deployments/
ENV REST_BASE_URL="http://rest-app:8080/rest-app/api"
EXPOSE 8080
