package com.santosltda.oarautodorei.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "noticias_home")
public class NoticiaHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String descricao;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String imagemURL;
}
