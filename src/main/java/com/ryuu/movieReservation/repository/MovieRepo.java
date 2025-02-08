package com.ryuu.movieReservation.repository;

import com.ryuu.movieReservation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    Boolean getMovieByTitleAndAndReleaseYear(String title , Integer releaseYear);
}
