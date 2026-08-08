package com.santosltda.oarautodorei.repository;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaHomeRepository extends JpaRepository<NoticiaHome, Long> {
    List<NoticiaHome> findByCasaNobreIgnoreCase(String casaNobre);
    List<NoticiaHome> findByRegiaoIgnoreCase(String regiao);
    List<NoticiaHome> findByDestaqueTrue();
    List<NoticiaHome> findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String titulo, String descricao);
}
