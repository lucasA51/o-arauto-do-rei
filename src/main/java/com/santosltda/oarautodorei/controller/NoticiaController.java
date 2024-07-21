package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.repository.NoticiaHomeRepository;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaHomeService noticiaHomeService;

    @Autowired
    private NoticiaHomeRepository noticiaHomeRepository;

    @GetMapping("/noticias/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("noticia", new NoticiaHome());
        return "noticias/cadastrar";
    }

    @PostMapping("/noticias/cadastrar")
    public String cadastrarNoticia(NoticiaHome noticia) {
        noticiaHomeService.saveNoticia(noticia);
        return "redirect:/";
    }

    @GetMapping("/noticias/listar")
    public String listarNoticias(Model model) {
        model.addAttribute("noticias", noticiaHomeRepository.findAll());
        return "noticias/listar";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editarNoticia(@PathVariable("id") Long id, Model model) {
        NoticiaHome noticia = noticiaHomeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("noticia", noticia);
        return "noticias/editar";
    }

    @PostMapping("/noticias/editar/{id}")
    public String salvarNoticiaEditada(@PathVariable("id") Long id, @RequestParam String titulo, @RequestParam String descricao) {
        NoticiaHome noticia = noticiaHomeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        noticia.setTitulo(titulo);
        noticia.setDescricao(descricao);
        noticiaHomeRepository.save(noticia);
        return "redirect:/noticias";
    }

    @GetMapping("/noticias/excluir/{id}")
    public String excluirNoticia(@PathVariable("id") Long id) {
        NoticiaHome noticia = noticiaHomeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        noticiaHomeRepository.delete(noticia);
        return "redirect:/noticias/listar";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        NoticiaHome noticia = noticiaHomeService.findById(id);
        model.addAttribute("noticia", noticia);
        return "editar-noticia";
    }

    @PostMapping("noticias/atualizar")
    public String updateNoticia(@ModelAttribute NoticiaHome noticia) {
        noticiaHomeService.updateNoticia(noticia);
        return "redirect:/noticias/listar";
    }
}
