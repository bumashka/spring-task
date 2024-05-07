DROP TABLE IF EXISTS subscriber_movie;
DROP TABLE IF EXISTS subscriber_genre;
DROP TABLE IF EXISTS subscriber;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;

CREATE TABLE genre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE
);

CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE,
    genre_id BIGINT REFERENCES genre (id)
);

CREATE TABLE subscriber (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE
);

CREATE TABLE subscriber_movie (
    id BIGSERIAL PRIMARY KEY,
    subscriber_id BIGINT REFERENCES subscriber (id),
    movie_id BIGINT REFERENCES movie (id)
);

CREATE TABLE subscriber_genre (
    id BIGSERIAL PRIMARY KEY,
    subscriber_id BIGINT REFERENCES subscriber (id),
    genre_id BIGINT REFERENCES genre (id) 
);

INSERT INTO genre (name) VALUES 
('Horror'),
('Fantasy'),
('Documentary'),
('Drama'),
('Comedy');

INSERT INTO movie (name, genre_id) VALUES
('Saw', 1),
('Alien', 1),
('Fellowship of the Ring', 2),
('Harry Potter', 2),
('The Truth Game', 3),
('The Social Dilemma', 3),
('Whiplash', 4),
('The Social Network', 4),
('The Big Lebowski', 5),
('Deadpool', 5);
