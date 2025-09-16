FROM quay.io/wildfly/wildfly:latest
# La JSF necesita saber dónde vive la REST (por nombre de contenedor)
ENV REST_BASE_URL=http://rest-app:8080/rest-app
COPY target/jsf-app.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
