package com.ryuu.movieReservation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieUpdateDto {
    @Size(max=100 , message = "the title can`t exceed 100 char")
    private String title;
    @Min(value=1 , message = "the number of seats can not be zero or negative value")
    private int numOfSeats;
    private Integer releaseYear;
    private String description;
    private List<String> genres;
    private List<String> castList;
    private List<String> directors;
    private List<LocalDateTime> showTimes;
}
