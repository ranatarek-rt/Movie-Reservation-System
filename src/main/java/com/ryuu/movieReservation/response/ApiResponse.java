package com.ryuu.movieReservation.response;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;
}
