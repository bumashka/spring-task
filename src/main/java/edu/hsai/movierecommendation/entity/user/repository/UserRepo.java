package edu.hsai.movierecommendation.entity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    User signIn(@Param("nickname") String nickname);

    Optional<User> findByNickname(@Param("nickname") String nickname);
}
