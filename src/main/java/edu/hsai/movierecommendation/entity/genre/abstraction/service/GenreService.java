package edu.hsai.movierecommendation.entity.genre.abstraction.service;

import edu.hsai.movierecommendation.entity.genre.repository.Genre;

import java.util.List;

public interface GenreService {
    GenreDto getById(Long id);
    GenreDto getByName(String name);
    List<GenreDto> getAll();
    record GenreDto(Long id, String name) {
        public static GenreDto fromDbEntity(Genre genre) {
            return new GenreDto(
                    genre.getId(),
                    genre.getName()
            );
        }
    }
}
