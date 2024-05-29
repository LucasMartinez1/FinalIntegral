package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String contraseña;
    private String rol;

    public Usuario(){};

    public Usuario(String username, String contraseña, String rol) {
        this.username = username;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getRol() {
        return rol;
    }

}