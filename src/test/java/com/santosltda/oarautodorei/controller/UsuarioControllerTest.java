package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void testShowRegisterForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/registrar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("usuarios/registrar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sucesso"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("erro"));
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        String usuario = "newUser";
        String senha = "password";
        Mockito.when(usuarioRepository.findByUsuario(usuario)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/registrar")
                        .param("usuario", usuario)
                        .param("senha", senha))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/usuarios/registrar?sucesso=Usu%C3%A1rio%20registrado%20com%20sucesso%21"));
    }

    @Test
    void testRegisterUserAlreadyExists() throws Exception {
        String usuario = "existingUser";
        String senha = "password";
        Usuario existingUser = new Usuario();
        existingUser.setUsuario(usuario);
        existingUser.setSenha(senha);

        Mockito.when(usuarioRepository.findByUsuario(usuario)).thenReturn(Optional.of(existingUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/registrar")
                        .param("usuario", usuario)
                        .param("senha", senha))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/usuarios/registrar?erro=Usu%C3%A1rio%20j%C3%A1%20existente.%20Escolha%20um%20nome%20de%20usu%C3%A1rio%20diferente."));
    }
}
