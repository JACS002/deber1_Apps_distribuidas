package com.usfq.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Named
@RequestScoped
public class HelloBean {
    private String respuesta;

    public void llamar() {
        try {
            String baseUrl = System.getenv("REST_BASE_URL");
            if (baseUrl == null || baseUrl.isEmpty()) {
                baseUrl = "http://rest-app:8080/rest-app/api";
            }

            URL url = new URL(baseUrl + "/hello");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                respuesta = content.toString();
            }
        } catch (Exception e) {
            respuesta = "Error al conectar con REST: " + e.getMessage();
        }
    }

    public String getRespuesta() {
        return respuesta;
    }
}
