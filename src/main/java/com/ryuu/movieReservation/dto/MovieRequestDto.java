package com.ryuu.movieReservation.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


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
    private List<ShowtimeDto> showtime;

    private MultipartFile poster;

}
