package com.ryuu.movieReservation.repository;

import com.ryuu.movieReservation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findMovieByTitleAndReleaseYear(String title , Integer releaseYear);
    List<Movie> findByGenresContaining(String genre);
    List<Movie> findByShowTimesContaining(LocalDateTime showTime);

    @Query("SELECT m FROM Movie m  where :startTime<= ANY ELEMENTS(m.showTimes) AND :endTime>= ANY ELEMENTS(m.showTimes) ")
    List<Movie> findMoviesByShowTimeRange(@Param("startTime")LocalDateTime startTime , @Param("endTime")LocalDateTime endTime);
}
