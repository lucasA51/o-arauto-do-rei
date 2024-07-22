package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.repository.NoticiaHomeRepository;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(NoticiaController.class)
public class NoticiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticiaHomeService noticiaHomeService;

    @MockBean
    private NoticiaHomeRepository noticiaHomeRepository;

    @Test
    void testShowCadastroForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/cadastrar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/cadastrar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticia"));
    }

    @Test
    void testCadastrarNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.doNothing().when(noticiaHomeService).saveNoticia(Mockito.any(NoticiaHome.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/noticias/cadastrar")
                        .flashAttr("noticia", noticia))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    void testListarNoticias() throws Exception {
        Mockito.when(noticiaHomeRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/listar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/listar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticias"));
    }

    @Test
    void testEditarNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeRepository.findById(1L)).thenReturn(Optional.of(noticia));

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/editar/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/editar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticia"));
    }

    @Test
    void testSalvarNoticiaEditada() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeRepository.findById(1L)).thenReturn(Optional.of(noticia));
        Mockito.when(noticiaHomeRepository.save(Mockito.any(NoticiaHome.class))).thenReturn(noticia);

        mockMvc.perform(MockMvcRequestBuilders.post("/noticias/editar/1")
                        .param("titulo", "Updated Title")
                        .param("descricao", "Updated Description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias"));
    }

    @Test
    void testExcluirNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeRepository.findById(1L)).thenReturn(Optional.of(noticia));
        Mockito.doNothing().when(noticiaHomeRepository).delete(Mockito.any(NoticiaHome.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/noticias/excluir/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias/listar"));
    }

    @Test
    void testShowEditForm() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.when(noticiaHomeService.findById(1L)).thenReturn(noticia);

        mockMvc.perform(MockMvcRequestBuilders.get("/editar/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias/editar"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("noticia"));
    }

    @Test
    void testUpdateNoticia() throws Exception {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setId(1L);
        noticia.setTitulo("Test Title");
        noticia.setDescricao("Test Description");

        Mockito.doNothing().when(noticiaHomeService).updateNoticia(Mockito.any(NoticiaHome.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/noticias/atualizar")
                        .flashAttr("noticia", noticia))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/noticias/listar"));
    }
}
