package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("usuarios/registrar")
    public String showRegisterForm() {
        return "usuarios/registrar";
    }

    @PostMapping("usuarios/registrar")
    public String registerUser(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if (usuarioRepository.findByUsuario(usuario).isPresent()) {
            model.addAttribute("erro", "Usuário já existente. Escolha um nome de usuário diferente.");
            return "usuarios/registrar";
        }

        Usuario newUser = new Usuario();
        newUser.setUsuario(usuario);
        newUser.setSenha(senha);
        usuarioRepository.save(newUser);
        return "redirect:/usuarios/registro?success";
    }
}
