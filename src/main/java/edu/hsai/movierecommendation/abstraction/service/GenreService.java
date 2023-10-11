package edu.hsai.movierecommendation.abstraction.service;

import edu.hsai.movierecommendation.repository.Genre;

import java.util.List;

public interface GenreService {
    GenreDto getById(Long id);
    GenreDto getByName(String name);
    List<GenreDto> getAll();
//    List<GenreDto> getByUser(String nickname);
    record GenreDto(Long id, String name) {
        public static GenreDto fromDbEntity(Genre genre) {
            return new GenreDto(
                    genre.getId(),
                    genre.getName()
            );
        }
    }
}
