package edu.hsai.movierecommendation.entity.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    Genre getByName(@Param("name") String name);
}
