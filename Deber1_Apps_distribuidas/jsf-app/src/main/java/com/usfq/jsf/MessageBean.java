package com.usfq.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@Named("messageBean")
@RequestScoped
public class MessageBean {
    private String message;

    private static final String DEFAULT_BASE_URL = "http://localhost:8081/rest-app/api";
    private final String baseUrl = System.getenv().getOrDefault("REST_BASE_URL", DEFAULT_BASE_URL);

    public String getMessage() {
        return message;
    }

    public String callRest() {
        try (Client client = ClientBuilder.newBuilder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build()) {

            this.message = client
                    .target(baseUrl)
                    .path("hello")
                    .request(MediaType.TEXT_PLAIN)
                    .get(String.class);

        } catch (Exception ex) {
            this.message = "Error llamando REST: " + ex.getClass().getSimpleName() + " - " + ex.getMessage();
        }
        return null;
    }
}
