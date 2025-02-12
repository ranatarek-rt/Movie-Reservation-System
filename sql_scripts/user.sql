USE movie_reservation;

CREATE TABLE users (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `user_role` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);
