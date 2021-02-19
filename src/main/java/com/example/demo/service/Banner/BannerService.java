package com.example.demo.service.Banner;

import com.example.demo.model.Banner;
import com.example.demo.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BannerService implements IBannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Iterable<Banner> getAll() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public Optional<Banner> findById(Long id) {
        return bannerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}
