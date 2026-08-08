package com.santosltda.oarautodorei.config;

import com.santosltda.oarautodorei.domain.BannerHome;
import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.BannerHomeRepository;
import com.santosltda.oarautodorei.repository.NoticiaHomeRepository;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BannerHomeRepository bannerHomeRepository;

    @Autowired
    private NoticiaHomeRepository noticiaHomeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Banner
        if (bannerHomeRepository.count() == 0) {
            BannerHome banner = new BannerHome();
            banner.setId(1L);
            banner.setTitulo("O ARAUTO DO REI");
            banner.setSubtitulo("A Voz Inquestionável dos Sete Reinos · Ano 300 Depois da Conquista");
            banner.setImagemURL("/images/logo.jpg");
            bannerHomeRepository.save(banner);
        }

        // Initialize Admin User
        if (usuarioRepository.findByUsername("maester").isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername("maester");
            user.setPassword(passwordEncoder.encode("citadel123"));
            usuarioRepository.save(user);
        }

        // Initialize News Articles with rich Westeros lore
        if (noticiaHomeRepository.count() == 0) {
            NoticiaHome n1 = new NoticiaHome();
            n1.setTitulo("O Rei Aegon VI e a Luta pelo Trono de Ferro: Uma Nova Era para Westeros");
            n1.setDescricao("Com a chegada da Frota de Ferro a Porto Real e boatos de dragões nos céus do Leste, o Pequeno Conselho emite édito de emergência.");
            n1.setConteudoCompleto("Sussurros vindos de Pedra do Dragão e das Cidades Livres confirmam que os remanescentes da dinastia Targaryen avançam a passos largos. Em Porto Real, a rainha regente convocou os lordes das Terras da Coroa para prestar juramento de fidelidade solene perante o Trono de Ferro.\n\nEnquanto os estandartes do Dragão Vermelho são desfraldados além do Mar Estreito, o Grande Septão exorta a população a manter a fé nos Sete Deuses e a apoiar as tropas reais na defesa do reino contra rebeldes e pretendentes impostores.");
            n1.setImagemURL("/images/news1-image.png");
            n1.setCasaNobre("Targaryen");
            n1.setRegiao("Porto Real");
            n1.setAutor("Arquimestre Gyldayn");
            n1.setDataPublicacao(LocalDateTime.now().minusDays(1));
            n1.setDestaque(true);
            noticiaHomeRepository.save(n1);

            NoticiaHome n2 = new NoticiaHome();
            n2.setTitulo("A Conquista de Yunkai: Daenerys Targaryen Libertadora dos Escravos");
            n2.setDescricao("A Nascida da Tormenta toma a cidade amarela da Baía dos Escravos, desmantelando as velhas oligarquias com um exército de Imaculados.");
            n2.setConteudoCompleto("Relatos trazidos por galeotas mercantes de Pentos afirmam que a jovem rainha Daenerys Targaryen libertou mais de duzentos mil escravos nas muralhas de Yunkai. Após uma cerco relâmpago organizado por Sor Jorah Mormont e o comandante dos Segundos Filhos, a nobreza de Yunkai rendeu as chaves da cidade e jurou abolição total da escravidão.");
            n2.setImagemURL("/images/news2-image.png");
            n2.setCasaNobre("Targaryen");
            n2.setRegiao("Essos");
            n2.setAutor("Sor Jorah Mormont");
            n2.setDataPublicacao(LocalDateTime.now().minusDays(3));
            n2.setDestaque(false);
            noticiaHomeRepository.save(n2);

            NoticiaHome n3 = new NoticiaHome();
            n3.setTitulo("Massacre no Casamento Vermelho: O Trágico Fim do Rei do Norte");
            n3.setDescricao("Traição em Correnrio assombra as Terras dos Rios após o violento rompimento da aliança entre Stark e Frey nas Torres Gêmeas.");
            n3.setConteudoCompleto("A rebelião liderada pelo jovem Robb Stark chegou a um trágico e sangrento término durante o banquete de núpcias de Lorde Edmure Tully nas Torres Gêmeas. Fontes leais à Coroa confirmam que Lorde Walder Frey e Lorde Roose Bolton uniram forças para punir a quebra da promessa matrimonial dos Stark, restabelecendo a autoridade real sobre as Terras dos Rios.");
            n3.setImagemURL("/images/news3-image.png");
            n3.setCasaNobre("Stark");
            n3.setRegiao("O Norte");
            n3.setAutor("Escriba Real Pycelle");
            n3.setDataPublicacao(LocalDateTime.now().minusDays(7));
            n3.setDestaque(false);
            noticiaHomeRepository.save(n3);

            NoticiaHome n4 = new NoticiaHome();
            n4.setTitulo("Rumores de Rebeldes e Saques nas Ilhas de Ferro");
            n4.setDescricao("Euron Olho de Corvo reivindica o Trono de Pedra do Mar após a morte de Balon Greyjoy no Bosque Sagrado.");
            n4.setConteudoCompleto("A Assembleia de Homens livres das Ilhas de Ferro aclamou Euron Greyjoy como novo Rei do Mar. O capitão do Silêncio prometeu aos ilhéus o controle sobre o Mar Poente e o saque às ricas terras da Campina, inflamando a frota de galés de ferro.");
            n4.setImagemURL("/images/news1-image.png");
            n4.setCasaNobre("Greyjoy");
            n4.setRegiao("Ilhas de Ferro");
            n4.setAutor("Mestre de Corvos da Citadela");
            n4.setDataPublicacao(LocalDateTime.now().minusDays(10));
            n4.setDestaque(false);
            noticiaHomeRepository.save(n4);
        }
    }
}
