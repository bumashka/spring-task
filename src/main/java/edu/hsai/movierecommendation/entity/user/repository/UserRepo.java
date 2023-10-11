package edu.hsai.movierecommendation.entity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    User signIn(@Param("nickname") String nickname);
}
