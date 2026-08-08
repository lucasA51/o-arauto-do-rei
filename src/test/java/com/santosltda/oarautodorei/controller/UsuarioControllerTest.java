package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.config.SecurityConfig;
import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import com.santosltda.oarautodorei.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(UsuarioController.class)
@Import(SecurityConfig.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testShowRegisterForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/registrar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("usuarios/registrar"));
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        String usuario = "newUser";
        String senha = "password";
        Mockito.when(usuarioRepository.findByUsuario(usuario)).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(senha)).thenReturn("encodedPassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/registrar")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("usuario", usuario)
                        .param("senha", senha))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
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
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("usuario", usuario)
                        .param("senha", senha))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
