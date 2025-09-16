package edu.usfq.cmp5001.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

@Named("helloBean")
@RequestScoped
public class HelloBean {
    private String message;

    public String callApi() {
        String base = System.getenv().getOrDefault("REST_BASE_URL", "http://localhost:8080/rest-app");
        String url = base + "/api/hello";
        Client client = ClientBuilder.newClient();
        try {
            Response resp = client.target(url).request().get();
            if (resp.getStatus() == 200) {
                message = resp.readEntity(String.class);
            } else {
                message = "Error: HTTP " + resp.getStatus();
            }
        } catch (Exception e) {
            message = "Excepción: " + e.getMessage();
        } finally {
            client.close();
        }
        return null;
    }

    public String getMessage() { return message; }
}
