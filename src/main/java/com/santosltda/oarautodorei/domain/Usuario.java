package com.santosltda.oarautodorei.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank

    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String usuario;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String senha;
}
