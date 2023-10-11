package edu.hsai.movierecommendation.entity.movie.abstraction.service;

import edu.hsai.movierecommendation.entity.movie.repository.Movie;

import java.util.List;

public interface MovieService {
    MovieDto getById(Long id);
    MovieDto getByName(String name);
    List<MovieDto> getAll();
    record MovieDto(Long id, String name, Long genreId) {
        public static MovieDto fromDbEntity(Movie movie) {
            return new MovieDto(
                    movie.getId(),
                    movie.getName(),
                    movie.getGenreId()
            );
        }
    }
}
