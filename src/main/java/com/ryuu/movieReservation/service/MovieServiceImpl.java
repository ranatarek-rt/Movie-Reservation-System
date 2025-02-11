package com.ryuu.movieReservation.service;


import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.dto.MovieUpdateDto;
import com.ryuu.movieReservation.exception.DuplicatedMovieEntryException;
import com.ryuu.movieReservation.exception.MovieNotFoundException;
import com.ryuu.movieReservation.model.Movie;
import com.ryuu.movieReservation.repository.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService{

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/uploads/";

    MovieRepo movieRepo;
    ModelMapper modelMapper;

    @Autowired
    MovieServiceImpl(MovieRepo movieRepo, ModelMapper modelMapper){
        this.movieRepo = movieRepo;
        this.modelMapper= modelMapper;

    }

    //this will be the responsibility for the admin to create a new movie
    @Override
    public MovieDto createMovie(MovieRequestDto movieRequestDto) {
        if(movieRepo.findMovieByTitleAndReleaseYear(movieRequestDto.getTitle(),movieRequestDto.getReleaseYear()).isPresent()) {
            throw new DuplicatedMovieEntryException("there is a movie exits with the same name and release year ");
        }
        Movie movie = modelMapper.map(movieRequestDto,Movie.class);
        if (movie.getPoster() == null || movie.getPoster().isEmpty()) {
            movie.setPoster("");
        }
        Movie finalMovie = movieRepo.save(movie);
        return modelMapper.map(finalMovie,MovieDto.class);
    }

    // upload poster to certain movie currently from static storage and can be changed to cloud or somethig else in the future
    @Override
    public String uploadPoster(Long movieId, MultipartFile file) throws IOException {
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        // to generate a unique file name for the image to be uploaded
        // use UUID which is universal unique identifier that generated 128-bit number randomly for each image
        // convert the number to string and concatenate underscore and the original image name coming from the request
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        //remove all the white spaces from the file name and replace them with underscore to avoid problems occurring in the url or file path
        fileName = fileName.replaceAll("\\s", "_");
        // generate a path(relative path) for the image to be saved correctly
        Path path = Paths.get(IMAGE_DIRECTORY + "/" + fileName);

        // save the uploaded file on the disk
        // first we convert the incoming file to input stream
        //file copy will copy the content of the input stream to the defined path from the previous step
        // If a file with the same name already exists in the target directory, it will be overwritten(StandardCopyOption.REPLACE_EXISTING)
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // set the poster url to movie object
        movie.setPoster("/images/" + fileName);
        // save the updates to the database
        movieRepo.save(movie);
        // return the poster url that we will be using to fetch the image with poster url
        return "/images/" + fileName;
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie =  movieRepo.findById(id)
                .orElseThrow(()-> new MovieNotFoundException("there is no movie found with that id"));

        return modelMapper.map(movie,MovieDto.class);
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movieList = movieRepo.findAll();
        return  movieList
                .stream()
                .map((movie)->modelMapper.map(movie,MovieDto.class))
                .toList();
    }

    @Override
    public void deleteMovieById(Long id) {
        Movie movie = movieRepo
                .findById(id)
                .orElseThrow(()->new MovieNotFoundException("there is no movie found with that is"));
        movieRepo.delete(movie);
    }

    //patch update for the provided fields only
    @Override
    public MovieDto updateMovie(Long id, MovieUpdateDto movieUpdateDto) {
        Movie movie = movieRepo
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException("There is no movie found with that ID"));
        if (movieUpdateDto.getTitle() != null) {
            movie.setTitle(movieUpdateDto.getTitle());
        }

        if (movieUpdateDto.getNumOfSeats() > 0) {
            movie.setNumOfSeats(movieUpdateDto.getNumOfSeats());
        }

        if (movieUpdateDto.getReleaseYear() != null) {
            movie.setReleaseYear(movieUpdateDto.getReleaseYear());
        }

        if (movieUpdateDto.getDescription() != null) {
            movie.setDescription(movieUpdateDto.getDescription());
        }

        if (movieUpdateDto.getGenres() != null && !movieUpdateDto.getGenres().isEmpty()) {
            movie.setGenres(movieUpdateDto.getGenres());
        }

        if (movieUpdateDto.getCastList() != null && !movieUpdateDto.getCastList().isEmpty()) {
            movie.setCastList(movieUpdateDto.getCastList());
        }

        if (movieUpdateDto.getDirectors() != null && !movieUpdateDto.getDirectors().isEmpty()) {
            movie.setDirectors(movieUpdateDto.getDirectors());
        }

        if (movieUpdateDto.getShowTimes() != null && !movieUpdateDto.getShowTimes().isEmpty()) {
            movie.setShowTimes(movieUpdateDto.getShowTimes());
        }

        Movie updatedMovie = movieRepo.save(movie);
        return modelMapper.map(updatedMovie,MovieDto.class);
    }


    @Override
    public List<MovieDto> searchByGenre(String genre){
        List<Movie> movies = movieRepo.findByGenresContaining(genre);
        return  movies
                .stream()
                .map((movie)->modelMapper.map(movie,MovieDto.class))
                .toList();
    }

    @Override
    public List<MovieDto> getMoviesByShowTime(LocalDateTime time) {
        List<Movie> movies = movieRepo.findByShowTimesContaining(time);
        return movies.stream()
                .map((movie)->modelMapper.map(movie,MovieDto.class))
                .toList();
    }

    @Override
    public List<MovieDto> getMoviesByShowTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<Movie> movies = movieRepo.findMoviesByShowTimeRange(startTime,endTime);
        return movies.stream()
                .map((movie)->modelMapper.map(movie,MovieDto.class))
                .toList();
    }



//    public List<String> getAvailableSeats(Long movieId, LocalDateTime showtime) {
//        Movie movie = movieRepo.findById(movieId)
//                .orElseThrow(() -> new RuntimeException("Movie not found"));
//
//        List<String> bookedSeats = reservationRepository.findBookedSeats(movieId, showtime);
//        List<String> allSeats = generateSeats(movie.getNumOfSeats());
//        allSeats.removeAll(bookedSeats);
//        return allSeats;
//    }


}
