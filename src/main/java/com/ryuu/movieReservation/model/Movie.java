package com.ryuu.movieReservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "release_year"})
})
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ElementCollection
    @CollectionTable(name = "movie_cast" , joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name="cast_name")
    private List<String> castList;

    @ElementCollection
    @CollectionTable(name = "movie_directors", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "director_name")
    private List<String> directors;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showtime> showtime;

    @Column(name = "poster")
    private String poster;


}
