package com.tecsup.demo.modelo.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Document(collection = "cursos")
public class Curso {
    @Id
    private String id;
    @NotNull
    @Size(min = 2, max = 30)
    private String nombre;
    @Min(0)
    @Max(5)
    private int creditos;

    public Curso() {
    }

    public Curso(String id, String nombre, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }

    public void setCreditos(int creditos) { this.creditos = creditos; }
}
