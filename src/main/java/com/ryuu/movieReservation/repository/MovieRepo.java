package com.ryuu.movieReservation.repository;

import com.ryuu.movieReservation.model.Movie;
import com.ryuu.movieReservation.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findMovieByTitleAndReleaseYear(String title, Integer releaseYear);

    List<Movie> findByGenresContaining(String genre);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.showtime s WHERE DATE(s.showTime) = :date")
    List<Movie> findMoviesByShowDate(@Param("date") LocalDate date);

}
