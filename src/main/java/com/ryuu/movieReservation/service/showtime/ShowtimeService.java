package com.ryuu.movieReservation.service.showtime;
import com.ryuu.movieReservation.dto.ShowtimeRequestDto;


import java.util.List;

public interface ShowtimeService {
    List<ShowtimeRequestDto> getAllShowTimes();
    ShowtimeRequestDto getShowTimeBydId(Long showtimeId);
    int getAvailableSeats(Long showtimeId);


}
