package edu.hsai.movierecommendation.abstraction.serviceImpl;

import edu.hsai.movierecommendation.abstraction.service.GenreService;
import edu.hsai.movierecommendation.repository.GenreRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepo genreRepo;

    @Override
    public GenreDto getById(Long id) {
        return GenreDto.fromDbEntity(genreRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public GenreDto getByName(String name) {
        return GenreDto.fromDbEntity(genreRepo.findByName(name).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<GenreDto> getAll() {
        return genreRepo.findAll().stream().map(GenreDto::fromDbEntity).toList();
    }

//    @Override
//    public List<GenreDto> getByUser(String nickname) {
//        return genreRepo.findPreferredGenres(nickname).stream().map(GenreDto::fromDbEntity).toList();
//    }
}
