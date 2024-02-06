package com.example.demo.DTO;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;

    public ClienteDTO(Long id,String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Long getid(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }
}
