package com.ESFE.Asistencias.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de usuario es requeido")
    private String login;

    @NotBlank(message = "El nombre de usuario es requeido")
    private String clave;

    private String status;

    //Establece una relacion muchos a muchos entre las entidades Usuario y Rol.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "usuario_rol",
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roles;
}
