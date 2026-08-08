package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.config.SecurityConfig;
import com.santosltda.oarautodorei.domain.BannerHome;
import com.santosltda.oarautodorei.service.BannerHomeService;
import com.santosltda.oarautodorei.service.CustomUserDetailsService;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(NavigationController.class)
@Import(SecurityConfig.class)
public class NavigationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BannerHomeService bannerHomeService;

    @MockBean
    private NoticiaHomeService noticiaHomeService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        BannerHome banner = new BannerHome();
        banner.setTitulo("O ARAUTO DO REI");
        banner.setSubtitulo("Subtítulo");
        banner.setImagemURL("/images/logo.jpg");
        Mockito.when(bannerHomeService.getBannerHome()).thenReturn(banner);
        Mockito.when(noticiaHomeService.getAllNoticias()).thenReturn(Collections.emptyList());
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void testNoticiasDoReino() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/noticias-do-reino"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("noticias-do-reino/index"));
    }

    @Test
    void testEconomiaEComercio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/economia-e-comercio"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("economia-e-comercio/index"));
    }

    @Test
    void testVidaNaCorte() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vida-na-corte"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("vida-na-corte/index"));
    }

    @Test
    void testCronicasEOpinioes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cronicas-e-opinioes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("cronicas-e-opinioes/index"));
    }

    @Test
    void testArteECultura() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/arte-e-cultura"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("arte-e-cultura/index"));
    }
}
