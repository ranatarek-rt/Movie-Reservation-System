DROP DATABASE IF exists movie_reservation;

CREATE DATABASE movie_reservation;

USE movie_reservation;

CREATE TABLE movie(
   `id` BIGINT not null AUTO_INCREMENT,
   `title` varchar(100),
   `description` TEXT,
   `poster` LONGBLOB,
   `num_of_seats` int not null,
   PRIMARY KEY (id)
);

CREATE TABLE movie_genres (
    `movie_id` BIGINT NOT NULL ,
    `genre` varchar(100),
    FOREIGN KEY (movie_id) references movie(id) ON DELETE CASCADE

);

CREATE TABLE movie_cast(
    `movie_id` BIGINT NOT NULL,
    `cast` varchar(100),
    FOREIGN KEY (movie_id) references movie(id) ON DELETE CASCADE

);

CREATE TABLE movie_directors(
    `movie_id` BIGINT NOT NULL,
    `director` varchar(100),
    FOREIGN KEY (movie_id) references movie(id) ON DELETE CASCADE
);

CREATE TABLE movie_showtimes(
    `movie_id` BIGINT NOT NULL,
    `showtime` varchar(100),
    FOREIGN KEY (movie_id) references movie(id) ON DELETE CASCADE
);








