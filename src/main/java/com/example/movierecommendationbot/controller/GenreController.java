package com.example.movierecommendationbot.controller;

import com.example.movierecommendationbot.model.Genre;
import com.example.movierecommendationbot.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private GenreService genreService;

    public GenreController(GenreService genreService) {
        super();
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/id/{id}")
    public Genre findById(@PathVariable Long id) {
        return genreService.findById(id).orElseGet(Genre::new);
    }

    @GetMapping("/name/{name}")
    public Genre findByName(@PathVariable String name) {
        return genreService.findByName(name).orElseGet(Genre::new);
    }
}
