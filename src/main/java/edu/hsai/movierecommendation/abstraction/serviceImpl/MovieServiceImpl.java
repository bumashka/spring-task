package edu.hsai.movierecommendation.abstraction.serviceImpl;

import edu.hsai.movierecommendation.abstraction.service.MovieService;
import edu.hsai.movierecommendation.repository.MovieRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepo movieRepo;

    @Override
    public MovieDto getById(Long id) {
        return MovieService.MovieDto.fromDbEntity(movieRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public MovieDto getByName(String name) {
        return MovieService.MovieDto.fromDbEntity(movieRepo.findByName(name).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<MovieDto> getAll() {
        return movieRepo.findAll().stream().map(MovieService.MovieDto::fromDbEntity).toList();
    }

    @Override
    public List<MovieDto> getByGenreId(Long genreId) {
        return movieRepo.findByGenreId(genreId).stream().map(MovieService.MovieDto::fromDbEntity).toList();
    }
}
