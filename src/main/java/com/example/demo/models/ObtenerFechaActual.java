package com.example.demo.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class ObtenerFechaActual {
    public static LocalDateTime obtenerFechaActual() {
        LocalDateTime fechaActual = null;

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
            fechaActual = LocalDateTime.now();
        }

        return fechaActual;
    }

    private static ResponseEntity<WorldClockResponse> obtenerFechaDesdeAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://worldclockapi.com/api/json/utc/now";
        return restTemplate.getForEntity(url, WorldClockResponse.class);
    }

    private static LocalDateTime parsearFecha(String fecha) {
        try {
            // Crear un formateador para el formato ISO 8601
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            // Parsear la cadena de fecha en un LocalDateTime
            return LocalDateTime.parse(fecha, formatter);
        } catch (Exception e) {
            // Si hay algún error al parsear la fecha, se maneja aquí
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
