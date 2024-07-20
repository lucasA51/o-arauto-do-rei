package com.santosltda.oarautodorei.service;

import com.santosltda.oarautodorei.domain.NoticiaHome;
import com.santosltda.oarautodorei.repository.NoticiaHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaHomeService {

    @Autowired
    private NoticiaHomeRepository noticiaHomeRepository;

    public List<NoticiaHome> getAllNoticias() {
        return noticiaHomeRepository.findAll();
    }
}
