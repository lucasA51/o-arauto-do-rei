package com.santosltda.oarautodorei.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "banner_home")
public class BannerHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(nullable = false)
    private String subtitulo;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String imagemURL;

    public BannerHome() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSubtitulo() { return subtitulo; }
    public void setSubtitulo(String subtitulo) { this.subtitulo = subtitulo; }

    public String getImagemURL() { return imagemURL; }
    public void setImagemURL(String imagemURL) { this.imagemURL = imagemURL; }
}
