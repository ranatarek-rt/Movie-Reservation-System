package com.ryuu.movieReservation.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieRequestDto {

    @NotBlank(message = "the movie title is required")
    @Size(max=100 , message = "the title can`t exceed 100 char")
    private String title;

    @NotNull(message = "Number of seats must not be null")
    @Min(value=1 , message = "the number of seats can not be zero or negative value")
    private int numOfSeats;

    @NotBlank(message = "the release year is required")
    private Integer releaseYear;

    @NotBlank(message = "the description for the movie is required")
    private String description;

    @NotNull(message = "genres cannot be null")
    @NotEmpty(message = "At least one genre is required")
    private List<String> genres;

    @NotNull(message = "cast List cannot be null")
    @NotEmpty(message = "At least one cast member is required")
    private List<String> castList;


    @NotNull(message = "directors cannot be null")
    @NotEmpty(message = "At least one director is required")
    private List<String> directors;

    @NotNull(message = "Showtime cannot be null")
    @NotEmpty(message = "At least one showtime is required")
    private List<LocalDateTime> showTimes;

    @NotNull(message = "Poster must not be null")
    private byte[] poster;
}
