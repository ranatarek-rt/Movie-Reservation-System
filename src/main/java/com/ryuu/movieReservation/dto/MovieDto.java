package com.ryuu.movieReservation.dto;


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
    private int numOfSeats;
    private Integer releaseYear;
    private String description;
    private List<String> genres;
    private List<String> castList;
    private List<String> directors;
    private List<LocalDateTime> showTimes;
    private String poster;

}
