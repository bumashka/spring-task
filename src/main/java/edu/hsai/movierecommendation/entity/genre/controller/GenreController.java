package edu.hsai.movierecommendation.entity.genre.controller;

import edu.hsai.movierecommendation.entity.genre.abstraction.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
public record GenreController(GenreService genreService) {
    @GetMapping("/id/{id}")
    public GenreService.GenreDto findById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    @GetMapping("/name/{name}")
    public GenreService.GenreDto findByName(@PathVariable String name) {
        return genreService.getByName(name);
    }

    @GetMapping("/all")
    public List<GenreService.GenreDto> findAll() {
        return genreService.getAll();
    }
}
