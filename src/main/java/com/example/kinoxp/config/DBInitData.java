package com.example.kinoxp.config;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.model.User;
import com.example.kinoxp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final ShowingRepository showingRepository;
    private final TheaterRepository theaterRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public DBInitData(MovieRepository movieRepository, ReservationRepository reservationRepository, ShowingRepository showingRepository, TheaterRepository theaterRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.theaterRepository = theaterRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Olivertest", "123", List.of(User.Roles.Admin));
        User user1 = new User("Olivertest", "123", List.of(User.Roles.Employee));
        userRepository.save(user);
        userRepository.save(user1);

        Movie movie1 = new Movie("Iron Lung", "In a post-apocalyptic future after \"The Quiet Rapture\" event, a convict explores a blood ocean on a desolate moon using a submarine called the \"Iron Lung\" to search for missing stars/planets.", true, 127,List.of(Movie.Category.Horror) );
        Movie movie2 = new Movie("The Bride!", "Filmen ’The Bride!’ er en nyfortolkning af gyserklassikeren The Bride of Frankenstein med en transformeret Christian Bale i rollen som Frankensteins monster og Jessie Buckley som hans genoplivede brud.\n" +
                "\n" +
                "Frankenstein rejser til 1930’ernes Chicago for at søge hjælp hos den fremsynede læge og forsker, Dr. Euphronius, i håbet om at skabe en ledsager til sig selv. \n" +
                "\n" +
                "Sammen bringer de en myrdet ung kvinde ved navn Ida tilbage fra de døde, og Bruden bliver født. \n" +
                "\n" +
                "Hun er mere end hvad end nogen af dem havde forestillet sig, og hendes eksistens antænder en eksplosiv romance, vækker politiets opmærksomhed og sætter gang i en vild og radikal social bevægelse. \n" +
                "\n" +
                "Maggie Gyllenhaal har instrueret filmen og vækker denne groteske duo til live med sit eget originale manuskript inspireret af Mary Shelleys udødelige univers. \n" +
                "\n" +
                "Det er en historie om kærlighed, dominans, oprør og genfødsel med en følelsesmæssig intensitet, der omformer de ikoniske legender til et eksplosivt makkerpar, opslugt af den største lidenskab der findes. Hvad gør døden ved livet og omvendt?", true, 126, List.of(Movie.Category.Scifi, Movie.Category.Horror, Movie.Category.Comedy));

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Ticket ticket1 = new Ticket(null, LocalDateTime.now(), 4, 16, 200);
        Ticket ticket2 = new Ticket(null, LocalDateTime.now().minusWeeks(1), 4, 15, 200);

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        Reservation reservation1 = new Reservation(1, LocalDateTime.now(), List.of(ticket1, ticket2), "test@email.com", "+45 19203421", "Freja", "Johannessen");

        reservationRepository.save(reservation1);

        System.out.println("Initial data created: " + movieRepository.count() + " movies and " + reservationRepository.count() + " orders");



    }

}
