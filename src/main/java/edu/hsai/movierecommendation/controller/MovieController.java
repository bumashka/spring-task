package edu.hsai.movierecommendation.controller;

import edu.hsai.movierecommendation.abstraction.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public record MovieController(MovieService movieService) {
    @GetMapping("/id/{id}")
    public MovieService.MovieDto findById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @GetMapping("/name/{name}")
    public MovieService.MovieDto findByName(@PathVariable String name) {
        return movieService.getByName(name);
    }

    @GetMapping("/all")
    public List<MovieService.MovieDto> findAll() {
        return movieService.getAll();
    }

    @GetMapping("/genre/{genreId}")
    public List<MovieService.MovieDto> findByGenreId(@PathVariable Long genreId) {
        return movieService.getByGenreId(genreId);
    }
}
