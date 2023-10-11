package edu.hsai.movierecommendation.entity.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Long> {
        Optional<Movie> findByName(@Param("name") String name);
}
