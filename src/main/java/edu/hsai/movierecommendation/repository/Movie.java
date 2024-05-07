package edu.hsai.movierecommendation.repository;

import edu.hsai.movierecommendation.repository.Genre;
import jakarta.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    private Long genreId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="genre_id", nullable = false)
//    @JsonIgnore
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Long getGenreId() {
//        return genreId;
//    }
//
//    public void setGenreId(Long genreId) {
//        this.genreId = genreId;
//    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
