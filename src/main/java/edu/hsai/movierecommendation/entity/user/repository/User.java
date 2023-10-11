package edu.hsai.movierecommendation.entity.user.repository;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User(){}

    public User(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public User(String nickname) {
        this.id = null;
        this.nickname = nickname;
    }
}
