package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.BannerHome;
import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.service.BannerHomeService;
import com.santosltda.oarautodorei.service.NoticiaHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private BannerHomeService bannerHomeService;

    @Autowired
    private NoticiaHomeService noticiaHomeService;

    @GetMapping("/")
    public String index(@RequestParam(value = "busca", required = false) String busca, Model model) {
        BannerHome bannerHome = bannerHomeService.getBannerHome();
        model.addAttribute("bannerHome", bannerHome);

        List<NoticiaHome> noticias;
        if (busca != null && !busca.isBlank()) {
            noticias = noticiaHomeService.buscarNoticias(busca);
            model.addAttribute("termoBusca", busca);
        } else {
            noticias = noticiaHomeService.getAllNoticias();
        }
        model.addAttribute("noticias", noticias);

        return "index";
    }

    @GetMapping("/noticia/{id}")
    public String verNoticia(@PathVariable("id") Long id, Model model) {
        NoticiaHome noticia = noticiaHomeService.findById(id);
        model.addAttribute("noticia", noticia);
        model.addAttribute("noticiasRelacionadas", noticiaHomeService.getAllNoticias());
        return "noticias/detalhes";
    }

    @GetMapping("/noticias-do-reino")
    public String noticiasDoReino(Model model) {
        model.addAttribute("categoriaTitulo", "Notícias do Reino");
        model.addAttribute("categoriaSubtitulo", "Decretos da Fortaleza Vermelha e Campanhas Militares dos Sete Reinos");
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "noticias-do-reino/index";
    }

    @GetMapping("/economia-e-comercio")
    public String economiaEComercio(Model model) {
        model.addAttribute("categoriaTitulo", "Economia e Comércio");
        model.addAttribute("categoriaSubtitulo", "Registros do Banco de Ferro de Braavos, Rotas Comerciais e Alfândega de Porto Real");
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "economia-e-comercio/index";
    }

    @GetMapping("/vida-na-corte")
    public String vidaNaCorte(Model model) {
        model.addAttribute("categoriaTitulo", "Vida na Corte");
        model.addAttribute("categoriaSubtitulo", "Intrigas da Fortaleza Vermelha, Torneios Reais e Banquetes da Nobreza");
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "vida-na-corte/index";
    }

    @GetMapping("/cronicas-e-opinioes")
    public String cronicasEOpinioes(Model model) {
        model.addAttribute("categoriaTitulo", "Crônicas e Opiniões");
        model.addAttribute("categoriaSubtitulo", "Ensaios dos Arquimestres da Cidadela e Homilias do Grande Septão");
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "cronicas-e-opinioes/index";
    }

    @GetMapping("/arte-e-cultura")
    public String arteECultura(Model model) {
        model.addAttribute("categoriaTitulo", "Arte e Cultura");
        model.addAttribute("categoriaSubtitulo", "Canções dos Bardos, Baladas Valirianas e Apresentações Teatrais");
        model.addAttribute("noticias", noticiaHomeService.getAllNoticias());
        return "arte-e-cultura/index";
    }

    @GetMapping("/casas")
    public String casasNobres(@RequestParam(value = "nome", required = false) String nome, Model model) {
        List<NoticiaHome> noticias;
        if (nome != null && !nome.isBlank()) {
            noticias = noticiaHomeService.getNoticiasPorCasa(nome);
            model.addAttribute("casaSelecionada", nome);
        } else {
            noticias = noticiaHomeService.getAllNoticias();
        }
        model.addAttribute("noticias", noticias);
        return "casas/index";
    }

    @GetMapping("/arquivos/mapa")
    public String mapaEArquivos(@RequestParam(value = "regiao", required = false) String regiao, Model model) {
        List<NoticiaHome> noticias;
        if (regiao != null && !regiao.isBlank()) {
            noticias = noticiaHomeService.getNoticiasPorRegiao(regiao);
            model.addAttribute("regiaoSelecionada", regiao);
        } else {
            noticias = noticiaHomeService.getAllNoticias();
        }
        model.addAttribute("noticias", noticias);
        return "arquivos/mapa";
    }
}
