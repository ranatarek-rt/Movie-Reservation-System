package com.ryuu.movieReservation.service.showtime;
import com.ryuu.movieReservation.dto.ShowtimeRequestDto;
import com.ryuu.movieReservation.exception.ShowtimeNotFoundException;
import com.ryuu.movieReservation.model.Showtime;
import com.ryuu.movieReservation.repository.ReservationRepo;
import com.ryuu.movieReservation.repository.ShowtimeRepo;
import com.ryuu.movieReservation.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShowtimeServiceImpl implements ShowtimeService{
    ShowtimeRepo showtimeRepo;
    UserRepo userRepo;
    ReservationRepo reservationRepo;
    ModelMapper modelMapper;
    @Autowired
    ShowtimeServiceImpl (ShowtimeRepo showtimeRepo,UserRepo userRepo,ReservationRepo reservationRepo,ModelMapper modelMapper){
        this.showtimeRepo = showtimeRepo;
        this.userRepo =userRepo;
        this.reservationRepo = reservationRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ShowtimeRequestDto> getAllShowTimes() {
        List<Showtime> showtimeList = showtimeRepo.findAll();
        return showtimeList
                .stream()
                .map(showtime -> modelMapper.map(showtime, ShowtimeRequestDto.class))
                .toList();
    }

    @Override
    public ShowtimeRequestDto getShowTimeBydId(Long showtimeId) {
        Showtime showtime = showtimeRepo
                .findById(showtimeId).orElseThrow(()->new ShowtimeNotFoundException("there is no show time found with id "+ showtimeId));
        return modelMapper.map(showtime, ShowtimeRequestDto.class);
    }

    @Override
    public int getAvailableSeats(Long showtimeId) {
        Showtime showtime = showtimeRepo
                .findById(showtimeId).orElseThrow(()->new ShowtimeNotFoundException("there is no show time found with id "+ showtimeId));
        return showtime.getAvailableSeats();
    }
}
