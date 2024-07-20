package com.santosltda.oarautodorei.service;

import com.santosltda.oarautodorei.domain.BannerHome;
import com.santosltda.oarautodorei.repository.BannerHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerHomeService {
    @Autowired
    private BannerHomeRepository repository;

    public BannerHome getBannerHome() {
        return repository.findById(1L).orElse(null); // Assuming you fetch the first record
    }
}

