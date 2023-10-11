package edu.hsai.movierecommendation.repository;

import edu.hsai.movierecommendation.repository.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(@Param("name") String name);
//    @Query("SELECT * FROM genre g JOIN preference p ON g.id = p.genre_id JOIN user u ON p.user_id = u.id WHERE u.nickname = :nickname")
//    List<Genre> findPreferredGenres(@Param("nickname") String nickname);
}
