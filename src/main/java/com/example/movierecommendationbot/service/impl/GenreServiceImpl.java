package com.example.movierecommendationbot.service.impl;

import com.example.movierecommendationbot.model.Genre;
import com.example.movierecommendationbot.repository.GenreRepository;
import com.example.movierecommendationbot.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        super();
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return findAll().stream().filter(g -> g.getName().toLowerCase().equals(name)).findFirst();
    }
}
