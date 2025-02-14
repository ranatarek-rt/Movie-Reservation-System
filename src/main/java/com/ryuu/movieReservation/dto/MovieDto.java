package com.ryuu.movieReservation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDto {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String description;
    private List<String> genres;
    private List<String> castList;
    private List<String> directors;
    private List<ShowtimeDto> showtime;
    private String poster;

}
