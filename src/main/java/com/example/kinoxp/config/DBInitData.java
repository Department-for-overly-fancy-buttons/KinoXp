package com.example.kinoxp.config;

import com.example.kinoxp.model.*;
import com.example.kinoxp.repository.*;
import com.example.kinoxp.service.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DBInitData implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final ShowingRepository showingRepository;
    private final TheaterRepository theaterRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final FeeTypeRepository feeTypeRepository;

    private final ReservationService reservationService;

    public DBInitData(MovieRepository movieRepository, ReservationRepository reservationRepository, ShowingRepository showingRepository, TheaterRepository theaterRepository, TicketRepository ticketRepository, UserRepository userRepository, CategoryRepository categoryRepository, TicketTypeRepository ticketTypeRepository, FeeTypeRepository feeTypeRepository, ReservationService reservationService) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
        this.theaterRepository = theaterRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.feeTypeRepository = feeTypeRepository;
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) throws Exception {
        FeeType threeDimensionFee = new FeeType("3d", 80);
        FeeType longMovieFee = new FeeType("hel_aften_film", 60);
        FeeType reservationFee = new FeeType("reservationsgebyr", 50);

        feeTypeRepository.save(threeDimensionFee);
        feeTypeRepository.save(longMovieFee);
        feeTypeRepository.save(reservationFee);

        User user = new User("Olivertest", "123", Role.ADMIN);
        User user1 = new User("Olivertest1", "123", Role.COSTUMER);
        User user2 = new User("Oliver", "123", Role.EMPLOYEE);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);

        List<Category> fullCategoryList = List.of(
                new Category("Horror"),
                new Category("Comedy"),
                new Category("Scifi"),
                new Category("Action"),
                new Category("Drama"),
                new Category("Thriller"),
                new Category("Romance"),
                new Category("Animation"),
                new Category("Documentary"),
                new Category("Fantasy"),
                new Category("Crime"),
                new Category("Adventure"),
                new Category("Mystery"),
                new Category("Musical"),
                new Category("Family"),
                new Category("History"),
                new Category("War"),
                new Category("Western")
        );
        categoryRepository.saveAll(fullCategoryList);

        List<Movie> movieList = List.of(
                new Movie("Iron Lung", "In a post-apocalyptic future after \"The Quiet Rapture\" event, a convict explores a blood ocean on a desolate moon using a submarine called the \"Iron Lung\" to search for missing stars/planets.", 17, 127, List.of(fullCategoryList.get(0), fullCategoryList.get(2))),
                new Movie("The Bride!", "Filmen ’The Bride!’ er en nyfortolkning af gyserklassikeren The Bride of Frankenstein med en transformeret Christian Bale i rollen som Frankensteins monster og Jessie Buckley som hans genoplivede brud.\n" +
                        "\n" +
                        "Frankenstein rejser til 1930’ernes Chicago for at søge hjælp hos den fremsynede læge og forsker, Dr. Euphronius, i håbet om at skabe en ledsager til sig selv. \n" +
                        "\n" +
                        "Sammen bringer de en myrdet ung kvinde ved navn Ida tilbage fra de døde, og Bruden bliver født. \n" +
                        "\n" +
                        "Hun er mere end hvad end nogen af dem havde forestillet sig, og hendes eksistens antænder en eksplosiv romance, vækker politiets opmærksomhed og sætter gang i en vild og radikal social bevægelse. \n" +
                        "\n" +
                        "Maggie Gyllenhaal har instrueret filmen og vækker denne groteske duo til live med sit eget originale manuskript inspireret af Mary Shelleys udødelige univers. \n" +
                        "\n" +
                        "Det er en historie om kærlighed, dominans, oprør og genfødsel med en følelsesmæssig intensitet, der omformer de ikoniske legender til et eksplosivt makkerpar, opslugt af den største lidenskab der findes. Hvad gør døden ved livet og omvendt?", 16, 126, List.of(fullCategoryList.get(0), fullCategoryList.get(1), fullCategoryList.get(5), fullCategoryList.get(6))),
                new Movie("Dune: Part Two", "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.", 15, 166, List.of(fullCategoryList.get(2), fullCategoryList.get(11), fullCategoryList.get(10))),
                new Movie("The Batman", "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.", 15, 176, List.of(fullCategoryList.get(0), fullCategoryList.get(3), fullCategoryList.get(10), fullCategoryList.get(12))),
                new Movie("Everything Everywhere All at Once", "An aging Chinese immigrant is swept up in an insane adventure, where she alone can save the world by exploring other universes connecting with the lives she could have led.", 12, 139, List.of(fullCategoryList.get(2), fullCategoryList.get(1), fullCategoryList.get(11), fullCategoryList.get(10))),
                new Movie("Oppenheimer", "The story of J. Robert Oppenheimer's role in the development of the atomic bomb during World War II.", 15, 180, List.of(fullCategoryList.get(5), fullCategoryList.get(10), fullCategoryList.get(16))),
                new Movie("The Super Mario Bros. Movie", "The adventures of the Mario brothers, Luigi and Mario, as they travel through the Mushroom Kingdom to rescue Princess Peach from the evil Bowser.", 7, 92, List.of(fullCategoryList.get(11), fullCategoryList.get(7), fullCategoryList.get(14))),
                new Movie("Barbie", "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.", 7, 114, List.of(fullCategoryList.get(1), fullCategoryList.get(11), fullCategoryList.get(14))),
                new Movie("John Wick: Chapter 4", "John Wick uncovers a path to defeating the High Table. But before he can earn his freedom, Wick must face off against a new enemy with powerful alliances across the globe and forces that turn old friends into foes.", 15, 169, List.of(fullCategoryList.get(3), fullCategoryList.get(10), fullCategoryList.get(12)))
        );

        movieRepository.saveAll(movieList);


        TicketType cowboyTicketType = new TicketType("Cow", 50.0);
        TicketType basicTicketType = new TicketType("Basic", 100.0);
        TicketType luxuryTicketType = new TicketType("Lux", 200);
        ticketTypeRepository.save(cowboyTicketType);
        ticketTypeRepository.save(basicTicketType);
        ticketTypeRepository.save(luxuryTicketType);

        List<Theater> theaterList = List.of(
        new Theater("Hall_Alpha", 20, 12,"Danmark"),
        new Theater("Hall_Beta", 25, 16,"Jylland"),
        new Theater("Hall_Gamma", 30, 14, "Sjælland"),
        new Theater("Hall_Delta", 18, 10, "Fyn")
        );

        for(Theater theater : theaterList){
            for(int i = 1; i <= theater.getNumberOfRows();i++){
                if(i<=2){
                    theater.addTheaterRow(new TheaterRow(i,theater,cowboyTicketType));
                } else if (i>theater.getNumberOfRows()-2) {
                    theater.addTheaterRow(new TheaterRow(i,theater,luxuryTicketType));
                }
                else{
                    theater.addTheaterRow(new TheaterRow(i,theater,basicTicketType));
                }
            }
        }

        theaterRepository.saveAll(theaterList);

        List<Showing> showingList = List.of(
                new Showing(movieList.get(0), theaterList.get(0), LocalDateTime.now(), true),
                new Showing(movieList.get(1), theaterList.get(1), LocalDateTime.now().plusWeeks(1), true),
                new Showing(movieList.get(0), theaterList.get(1), LocalDateTime.now().plusWeeks(1), false),
                new Showing(movieList.get(0), theaterList.get(0), LocalDateTime.now().minusMinutes(40), false),
                new Showing(movieList.get(1), theaterList.get(0), LocalDateTime.now().plusWeeks(3), true),
                new Showing(movieList.get(2), theaterList.get(1), LocalDateTime.now().plusDays(1).withHour(18), true),
                new Showing(movieList.get(3), theaterList.get(2), LocalDateTime.now().plusDays(1).withHour(20), false),
                new Showing(movieList.get(4), theaterList.get(3), LocalDateTime.now().plusDays(2).withHour(19), true),
                new Showing(movieList.get(1), theaterList.get(0), LocalDateTime.now().plusDays(2).withHour(21), true),
                new Showing(movieList.get(0), theaterList.get(3), LocalDateTime.now().plusDays(3).withHour(17), false),
                new Showing(movieList.get(5), theaterList.get(1), LocalDateTime.now().plusDays(3).withHour(20), true),
                new Showing(movieList.get(6), theaterList.get(2), LocalDateTime.now().plusDays(4).withHour(18), false),
                new Showing(movieList.get(7), theaterList.get(0), LocalDateTime.now().plusDays(4).withHour(21), true),
                new Showing(movieList.get(2), theaterList.get(3), LocalDateTime.now().plusDays(5).withHour(19), false),
                new Showing(movieList.get(3), theaterList.get(1), LocalDateTime.now().plusDays(5).withHour(22), true),
                new Showing(movieList.get(4), theaterList.get(2), LocalDateTime.now().plusWeeks(1).withHour(17), true),
                new Showing(movieList.get(5), theaterList.get(0), LocalDateTime.now().plusWeeks(1).withHour(20), false),
                new Showing(movieList.get(6), theaterList.get(3), LocalDateTime.now().plusWeeks(1).withHour(18), true),
                new Showing(movieList.get(7), theaterList.get(1), LocalDateTime.now().plusWeeks(1).withHour(21), false),
                new Showing(movieList.get(0), theaterList.get(2), LocalDateTime.now().plusWeeks(2).withHour(19), true),
                new Showing(movieList.get(1), theaterList.get(3), LocalDateTime.now().plusWeeks(2).withHour(22), false),
                new Showing(movieList.get(2), theaterList.get(0), LocalDateTime.now().plusWeeks(3).withHour(17), true),
                new Showing(movieList.get(3), theaterList.get(1), LocalDateTime.now().plusWeeks(3).withHour(20), true),
                new Showing(movieList.get(4), theaterList.get(2), LocalDateTime.now().plusWeeks(4).withHour(18), false),
                new Showing(movieList.get(5), theaterList.get(3), LocalDateTime.now().plusWeeks(4).withHour(21), true),
                new Showing(movieList.get(6), theaterList.get(0), LocalDateTime.now().plusWeeks(4).withHour(19), false)
        );

        showingRepository.saveAll(showingList);

        List<Reservation> reservationList = List.of(
                new Reservation(showingList.get(1), "JanTheMan@email.com", "+45 11111111", "Jan", "Janice"),
                new Reservation(showingList.get(0), "Janicesonnnnn@email.com", "+45 33333333", "Janice", "Jansan"),
                new Reservation(showingList.get(3), "James.Jamieson@email.com", "+45 444444", "James", "Jamieson"),
                new Reservation(showingList.get(4), "JanTheMan@email.com", "+45 11111111", "Jan", "Janice"),
                new Reservation(showingList.get(2), "JuliaGillison@emailo.com", "+45 22222222", "Julia", "Gillieson"),
                new Reservation(showingList.get(5), "LarsLarsen@email.com", "+45 55555555", "Lars", "Larsen"),
                new Reservation(showingList.get(6), "MetteMette@email.com", "+45 66666666", "Mette", "Mettesen"),
                new Reservation(showingList.get(7), "PeterPetersen@email.com", "+45 77777777", "Peter", "Petersen"),
                new Reservation(showingList.get(8), "AnnaAndersen@email.com", "+45 88888888", "Anna", "Andersen"),
                new Reservation(showingList.get(9), "SorenSorensen@email.com", "+45 99999999", "Søren", "Sørensen"),
                new Reservation(showingList.get(10), "KirstenKirstensen@email.com", "+45 10101010", "Kirsten", "Kirstensen"),
                new Reservation(showingList.get(11), "NielsNielsen@email.com", "+45 11223344", "Niels", "Nielsen"),
                new Reservation(showingList.get(12), "SchoolTrip@email.com", "+45 20202020", "Teacher", "Johnson"),
                new Reservation(showingList.get(13), "CorporateEvent@email.com", "+45 30303030", "HR", "Manager"),
                new Reservation(showingList.get(14), "BirthdayParty@email.com", "+45 40404040", "Parent", "Organizer"),
                new Reservation(showingList.get(15), "TourGroup@email.com", "+45 50505050", "Guide", "Leader"),
                new Reservation(showingList.get(16), "FamilyReunion@email.com", "+45 60606060", "Family", "Head")
        );


        Random rng = new Random();
        for(Reservation reservation: reservationList) {
            reservation.setTimeOfPurchase(LocalDateTime.now().minusHours(rng.nextInt(10, 8000)));
        }

        List<Ticket> ticketList = List.of(
                new Ticket(reservationList.get(0), 4, 16, basicTicketType),
                new Ticket(reservationList.get(0), 4, 15, basicTicketType),
                new Ticket(reservationList.get(1), 25, 15, luxuryTicketType),
                new Ticket(reservationList.get(2), 4, 2, basicTicketType),
                new Ticket(reservationList.get(4), 6, 6, basicTicketType),
                new Ticket(reservationList.get(3), 8, 7, basicTicketType),
                new Ticket(reservationList.get(4), 6, 7, luxuryTicketType),
                new Ticket(reservationList.get(5), 10, 5, basicTicketType),
                new Ticket(reservationList.get(5), 10, 6, basicTicketType),
                new Ticket(reservationList.get(6), 3, 12, basicTicketType),
                new Ticket(reservationList.get(6), 3, 13, basicTicketType),
                new Ticket(reservationList.get(7), 7, 8, luxuryTicketType),
                new Ticket(reservationList.get(7), 7, 9, luxuryTicketType),
                new Ticket(reservationList.get(8), 12, 4, basicTicketType),
                new Ticket(reservationList.get(8), 12, 5, basicTicketType),
                new Ticket(reservationList.get(8), 12, 6, basicTicketType),
                new Ticket(reservationList.get(9), 4, 12, basicTicketType),
                new Ticket(reservationList.get(9), 4, 13, basicTicketType),
                new Ticket(reservationList.get(9), 4, 14, basicTicketType),
                new Ticket(reservationList.get(10), 5, 1, basicTicketType),
                new Ticket(reservationList.get(10), 5, 2, basicTicketType),
                new Ticket(reservationList.get(11), 6, 1, luxuryTicketType)
        );

        reservationList.get(0).setTickets(List.of(ticketList.get(0), ticketList.get(1)));
        reservationList.get(1).addTicket(ticketList.get(2));
        reservationList.get(4).setTickets(List.of(ticketList.get(4), ticketList.get(6)));
        reservationList.get(2).addTicket(ticketList.get(3));
        reservationList.get(3).addTicket(ticketList.get(5));
        reservationList.get(5).setTickets(List.of(ticketList.get(7), ticketList.get(8)));
        reservationList.get(6).setTickets(List.of(ticketList.get(9), ticketList.get(10)));
        reservationList.get(7).setTickets(List.of(ticketList.get(11), ticketList.get(12)));
        reservationList.get(8).setTickets(List.of(ticketList.get(13), ticketList.get(14), ticketList.get(15)));
        reservationList.get(9).setTickets(List.of(ticketList.get(16), ticketList.get(17), ticketList.get(18)));
        reservationList.get(10).setTickets(List.of(ticketList.get(19), ticketList.get(20)));
        reservationList.get(11).addTicket(ticketList.get(21));

        ticketList = List.of(
                // School Trip (7 tickets)
                new Ticket(reservationList.get(12), 1, 1, basicTicketType),
                new Ticket(reservationList.get(12), 1, 2, basicTicketType),
                new Ticket(reservationList.get(12), 1, 3, basicTicketType),
                new Ticket(reservationList.get(12), 1, 4, basicTicketType),
                new Ticket(reservationList.get(12), 1, 5, basicTicketType),
                new Ticket(reservationList.get(12), 1, 6, basicTicketType),
                new Ticket(reservationList.get(12), 1, 7, basicTicketType)
        );
        reservationList.get(12).setTickets(ticketList);
        ticketList = List.of(
                // Corporate Event (8 tickets)
                new Ticket(reservationList.get(13), 2, 1, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 2, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 3, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 4, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 5, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 6, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 7, luxuryTicketType),
                new Ticket(reservationList.get(13), 2, 8, luxuryTicketType)
        );
        reservationList.get(13).setTickets(ticketList);
        ticketList = List.of(
                // Birthday Party (6 tickets)
                new Ticket(reservationList.get(14), 3, 1, basicTicketType),
                new Ticket(reservationList.get(14), 3, 2, basicTicketType),
                new Ticket(reservationList.get(14), 3, 3, basicTicketType),
                new Ticket(reservationList.get(14), 3, 4, basicTicketType),
                new Ticket(reservationList.get(14), 3, 5, basicTicketType),
                new Ticket(reservationList.get(14), 3, 6, basicTicketType)
                );
        reservationList.get(14).setTickets(ticketList);
        ticketList = List.of(
                // Tour Group (9 tickets)
                new Ticket(reservationList.get(15), 4, 1, basicTicketType),
                new Ticket(reservationList.get(15), 4, 2, basicTicketType),
                new Ticket(reservationList.get(15), 4, 3, basicTicketType),
                new Ticket(reservationList.get(15), 4, 4, basicTicketType),
                new Ticket(reservationList.get(15), 4, 5, basicTicketType),
                new Ticket(reservationList.get(15), 4, 6, basicTicketType),
                new Ticket(reservationList.get(15), 4, 7, basicTicketType),
                new Ticket(reservationList.get(15), 4, 8, basicTicketType),
                new Ticket(reservationList.get(15), 4, 9, basicTicketType)
                );
        reservationList.get(15).setTickets(ticketList);
        ticketList = List.of(
                // Family Reunion (10 tickets)
                new Ticket(reservationList.get(16), 5, 1, basicTicketType),
                new Ticket(reservationList.get(16), 5, 2, basicTicketType),
                new Ticket(reservationList.get(16), 5, 3, basicTicketType),
                new Ticket(reservationList.get(16), 5, 4, basicTicketType),
                new Ticket(reservationList.get(16), 5, 5, basicTicketType),
                new Ticket(reservationList.get(16), 5, 6, basicTicketType),
                new Ticket(reservationList.get(16), 5, 7, basicTicketType),
                new Ticket(reservationList.get(16), 5, 8, basicTicketType),
                new Ticket(reservationList.get(16), 5, 9, basicTicketType),
                new Ticket(reservationList.get(16), 5, 10, basicTicketType)
        );

        reservationList.get(16).setTickets(ticketList);

        for (Reservation reservation : reservationList) {
            reservationService.createReservation(reservation);
        }

    }

}
