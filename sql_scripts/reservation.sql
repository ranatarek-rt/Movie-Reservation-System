USE movie_reservation;

CREATE TABLE reservation (
     id BIGINT NOT NULL AUTO_INCREMENT,
     user_id BIGINT NOT NULL,
     showtime_id BIGINT NOT NULL,
     num_of_seats_booked INT NOT NULL,
     booking_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
     FOREIGN KEY (showtime_id) REFERENCES showtime(id) ON DELETE CASCADE
);
