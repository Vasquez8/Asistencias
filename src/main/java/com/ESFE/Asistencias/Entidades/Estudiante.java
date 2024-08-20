package com.ESFE.Asistencias.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Escriba su nombre completo ")
    private String nombre;
    @NotBlank(message = "Escriba su email completo")
    private String email;
    @NotBlank(message = "Escriba su pin")
    private String pin;

    @ManyToMany
    @JoinTable(
            name = "estudiantes_grupos",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos = new HashSet<>();

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Escriba su nombre completo ") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Escriba su nombre completo ") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "Escriba su email completo") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Escriba su email completo") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Escriba su pin") String getPin() {
        return pin;
    }

    public void setPin(@NotBlank(message = "Escriba su pin") String pin) {
        this.pin = pin;
    }
}
