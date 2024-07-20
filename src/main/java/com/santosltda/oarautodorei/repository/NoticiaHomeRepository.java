package com.santosltda.oarautodorei.repository;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaHomeRepository extends JpaRepository<NoticiaHome, Long> {
}
