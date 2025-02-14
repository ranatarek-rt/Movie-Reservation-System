DROP DATABASE IF EXISTS movie_reservation;
CREATE DATABASE movie_reservation;
USE movie_reservation;

CREATE TABLE movie (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    poster VARCHAR(255),
    release_year INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (title, release_year)
);

CREATE TABLE movie_genres (
    movie_id BIGINT NOT NULL,
    genre VARCHAR(100) NOT NULL,
    PRIMARY KEY (movie_id, genre),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);

CREATE TABLE movie_cast (
    movie_id BIGINT NOT NULL,
    cast_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (movie_id, cast_name),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);

CREATE TABLE movie_directors (
    movie_id BIGINT NOT NULL,
    director_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (movie_id, director_name),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);

CREATE TABLE showtime (
    id BIGINT NOT NULL AUTO_INCREMENT,
    movie_id BIGINT NOT NULL,
    show_time DATETIME NOT NULL,
    available_seats INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE
);
