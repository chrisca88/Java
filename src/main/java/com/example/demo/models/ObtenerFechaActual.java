package com.example.demo.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ObtenerFechaActual {
    public static Date obtenerFechaActual() {
        Date fechaActual = null;

        // Intenta obtener la fecha de la API externa
        try {
            ResponseEntity<WorldClockResponse> response = obtenerFechaDesdeAPI();
            if (response.getStatusCode() == HttpStatus.OK) {
                fechaActual = parsearFecha(response.getBody().getCurrentDateTime());
            }
        } catch (Exception e) {
            // Si hay algún error al obtener la fecha de la API externa, se maneja aquí
            e.printStackTrace();
        }

        // Si no se pudo obtener la fecha de la API externa, se usa la fecha local del sistema
        if (fechaActual == null) {
            fechaActual = new Date();
        }

        return fechaActual;
    }

    private static ResponseEntity<WorldClockResponse> obtenerFechaDesdeAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://worldclockapi.com/api/json/utc/now";
        return restTemplate.getForEntity(url, WorldClockResponse.class);
    }

    private static Date parsearFecha(String fecha) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return formatter.parse(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class WorldClockResponse {
    private String currentDateTime;

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
