package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.config.SecurityConfig;
import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.service.CustomUserDetailsService;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(NoticiaController.class)
@Import(SecurityConfig.class)
public class NoticiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticiaHomeService noticiaHomeService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @WithMockUser
    void testShowCadastroForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/cadastrar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/cadastrar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticia"));
    }

    @Test
    @WithMockUser
    void testCadastrarNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");
        noticia.setImagemURL("/images/news1-image.png");

        Mockito.doNothing().when(noticiaHomeService).saveNoticia(Mockito.any(NoticiaHome.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/noticias/cadastrar")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .flashAttr("noticia", noticia))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias/listar"));
    }

    @Test
    @WithMockUser
    void testListarNoticias() throws Exception {
        Mockito.when(noticiaHomeService.getAllNoticias()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/listar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/listar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticias"));
    }

    @Test
    @WithMockUser
    void testEditarNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeService.findById(1L)).thenReturn(noticia);

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/editar/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/editar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticia"));
    }

    @Test
    @WithMockUser
    void testSalvarNoticiaEditada() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeService.findById(1L)).thenReturn(noticia);

        mockMvc.perform(MockMvcRequestBuilders.post("/noticias/editar/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .flashAttr("noticiaHome", noticia))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias/listar"));
    }

    @Test
    @WithMockUser
    void testExcluirNoticia() throws Exception {
        Mockito.doNothing().when(noticiaHomeService).deleteNoticia(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/excluir/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias/listar"));
    }
}
