package com.example.movierecommendationbot.service;

import com.example.movierecommendationbot.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);

    List<Genre> findAll();

    Optional<Genre> findByName(String name);
}
