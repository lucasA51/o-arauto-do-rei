package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaHomeService noticiaHomeService;

    @GetMapping("/noticias/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("noticia", new NoticiaHome());
        return "noticias/cadastrar";
    }

    @PostMapping("/noticias/cadastrar")
    public String cadastrarNoticia(@ModelAttribute NoticiaHome noticia) {
        noticiaHomeService.saveNoticia(noticia);
        return "redirect:/noticias/listar";
    }

    @GetMapping("/noticias/listar")
    public String listarNoticias(Model model) {
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "noticias/listar";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editarNoticia(@PathVariable("id") Long id, Model model) {
        NoticiaHome noticia = noticiaHomeService.findById(id);
        model.addAttribute("noticia", noticia);
        return "noticias/editar";
    }

    @PostMapping("/noticias/editar/{id}")
    public String salvarNoticiaEditada(@PathVariable("id") Long id, @ModelAttribute NoticiaHome noticiaForm) {
        NoticiaHome noticia = noticiaHomeService.findById(id);
        noticia.setTitulo(noticiaForm.getTitulo());
        noticia.setDescricao(noticiaForm.getDescricao());
        noticia.setConteudoCompleto(noticiaForm.getConteudoCompleto());
        noticia.setImagemURL(noticiaForm.getImagemURL());
        noticia.setCasaNobre(noticiaForm.getCasaNobre());
        noticia.setRegiao(noticiaForm.getRegiao());
        noticia.setAutor(noticiaForm.getAutor());
        noticia.setDestaque(noticiaForm.getDestaque());
        noticiaHomeService.saveNoticia(noticia);
        return "redirect:/noticias/listar";
    }

    @GetMapping("/noticias/excluir/{id}")
    public String excluirNoticia(@PathVariable("id") Long id) {
        noticiaHomeService.deleteNoticia(id);
        return "redirect:/noticias/listar";
    }
}
