package com.santosltda.oarautodorei.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, unique = true)
    private String usuario;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String senha;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getUsername() { return usuario; }
    public void setUsername(String username) { this.usuario = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getPassword() { return senha; }
    public void setPassword(String password) { this.senha = password; }
}
