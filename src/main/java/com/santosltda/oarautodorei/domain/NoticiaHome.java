package com.santosltda.oarautodorei.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String descricao;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String conteudoCompleto;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String imagemURL;

    private String casaNobre;
    private String regiao;
    private String autor;
    private LocalDateTime dataPublicacao = LocalDateTime.now();
    private Boolean destaque = false;

    public NoticiaHome() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getConteudoCompleto() { return conteudoCompleto; }
    public void setConteudoCompleto(String conteudoCompleto) { this.conteudoCompleto = conteudoCompleto; }

    public String getImagemURL() { return imagemURL; }
    public void setImagemURL(String imagemURL) { this.imagemURL = imagemURL; }

    public String getCasaNobre() { return casaNobre; }
    public void setCasaNobre(String casaNobre) { this.casaNobre = casaNobre; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDateTime dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public Boolean getDestaque() { return destaque; }
    public void setDestaque(Boolean destaque) { this.destaque = destaque; }
}
