package edu.hsai.movierecommendation.entity.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(@Param("name") String name);
}
