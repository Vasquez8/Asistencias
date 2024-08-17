package com.ESFE.Asistencias.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Escriba su nombre completo ")
    private String nombre;

    @NotBlank(message = "Escriba su apellido completo")
    private String apellido;

    @NotBlank(message = "Escriba su email completo")
    private String email;

    @NotBlank(message = "Escriba su telefono completo")
    private String telefono;

    @NotBlank(message = "Escriba su escuela completo")
    private String escuela;

    @ManyToMany
    @JoinTable(
            name = "docentes_grupos",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos = new HashSet<>();
    /////////////////////Getter and setter///////////////////////

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

    public @NotBlank(message = "Escriba su nombre completo ")
    String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Escriba su nombre completo ") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "Escriba su apellido completo") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "Escriba su apellido completo") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(message = "Escriba su email completo") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Escriba su email completo") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Escriba su telefono completo") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "Escriba su telefono completo") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "Escriba su escuela completo") String getEscuela() {
        return escuela;
    }

    public void setEscuela(@NotBlank(message = "Escriba su escuela completo") String escuela) {
        this.escuela = escuela;
    }
}
