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
        User user1 = new User("Olivertest", "123", Role.EMPLOYEE);
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
        List<Category> categoryList1 = List.of(fullCategoryList.get(0));


        Movie movie1 = new Movie("Iron Lung", "In a post-apocalyptic future after \"The Quiet Rapture\" event, a convict explores a blood ocean on a desolate moon using a submarine called the \"Iron Lung\" to search for missing stars/planets.", 17, 127, categoryList1);
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
                "Det er en historie om kærlighed, dominans, oprør og genfødsel med en følelsesmæssig intensitet, der omformer de ikoniske legender til et eksplosivt makkerpar, opslugt af den største lidenskab der findes. Hvad gør døden ved livet og omvendt?", 16, 126, fullCategoryList);

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        TicketType cowboyTicketType = new TicketType("Cow", 50.0);
        TicketType basicTicketType = new TicketType("Basic", 100.0);
        TicketType luxuryTicketType = new TicketType("Lux", 200);
        ticketTypeRepository.save(cowboyTicketType);
        ticketTypeRepository.save(basicTicketType);
        ticketTypeRepository.save(luxuryTicketType);

        Theater theater1 = new Theater("Hall_Alpha", 20, 12,"Danmark");
        Theater theater2 = new Theater("Hall_Beta", 25, 16,"Jylland");

        for(int i = 1; i <= theater1.getNumberOfRows();i++){
            if(i<=2){
                theater1.addTheaterRow(new TheaterRow(i,theater1,cowboyTicketType));
            } else if (i>theater1.getNumberOfRows()-2) {
                theater1.addTheaterRow(new TheaterRow(i,theater1,luxuryTicketType));
            }
            else{
                theater1.addTheaterRow(new TheaterRow(i,theater1,basicTicketType));
            }
        }

        for(int i = 1; i <= theater2.getNumberOfRows();i++){
            if(i<=2){
                theater2.addTheaterRow(new TheaterRow(i,theater2,cowboyTicketType));
            } else if (i>theater2.getNumberOfRows()-2) {
                theater2.addTheaterRow(new TheaterRow(i,theater2,luxuryTicketType));
            }
            else{
                theater2.addTheaterRow(new TheaterRow(i,theater2,basicTicketType));
            }
        }

        theaterRepository.save(theater1);
        theaterRepository.save(theater2);

        Showing showing1OfMovie1 = new Showing(movie1, theater1, LocalDateTime.now(), true);
        Showing showing2OfMovie1 = new Showing(movie2, theater2, LocalDateTime.now().plusWeeks(1), true);
        Showing showing3OfMovie1 = new Showing(movie1, theater2, LocalDateTime.now().plusWeeks(1), false);

        Showing showing1OfMovie2 = new Showing(movie1, theater1, LocalDateTime.now().minusMinutes(40), false);
        Showing showing2OfMovie2 = new Showing(movie2, theater1, LocalDateTime.now().plusWeeks(3), true);



        showingRepository.save(showing1OfMovie1);
        showingRepository.save(showing2OfMovie1);
        showingRepository.save(showing3OfMovie1);

        showingRepository.save(showing1OfMovie2);
        showingRepository.save(showing2OfMovie2);

        Reservation reservation1 = new Reservation(showing2OfMovie1, "JanTeMan@email.com", "+45 11111111", "Jan", "Janice");
        reservation1.setTimeOfPurchase(LocalDateTime.now().plusHours(3));
        Reservation reservation3 = new Reservation(showing1OfMovie1, "Janicesonnnnn@email.com", "+45 33333333", "Janice", "Jansan");
        reservation1.setTimeOfPurchase(LocalDateTime.now().plusHours(3));
        Reservation reservation4 = new Reservation(showing1OfMovie2, "James.Jamieson@email.com", "+45 444444", "James", "Jamieson");
        reservation1.setTimeOfPurchase(LocalDateTime.now().plusHours(3));
        Reservation reservation5 = new Reservation(showing2OfMovie2, "JanTeMan@email.com", "+45 11111111", "Jan", "Janice");
        reservation1.setTimeOfPurchase(LocalDateTime.now().plusHours(3));

        Reservation reservation2 = new Reservation(showing3OfMovie1, "JuliaGillison@emailo.com", "+45 22222222", "Julia", "Gillieson");
        reservation2.setTimeOfPurchase(LocalDateTime.now().plusHours(30));

        Ticket ticket1 = new Ticket(reservation1, 4, 16, basicTicketType);
        Ticket ticket2 = new Ticket(reservation1, 4, 15, basicTicketType);
        Ticket ticket3 = new Ticket(reservation2, 25, 15, luxuryTicketType);
        Ticket ticket4 = new Ticket(reservation3, 4, 2, basicTicketType);
        Ticket ticket5 = new Ticket(reservation5, 6, 6, basicTicketType);
        Ticket ticket6 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket7 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket8 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket9 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket10 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket11 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket12 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket13 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket14 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket15 = new Ticket(reservation1, 4, 1, basicTicketType);
        Ticket ticket16 = new Ticket(reservation1, 4, 1, basicTicketType);


        reservation1.addTicket(ticket1);
        reservation1.addTicket(ticket7);
        reservation1.addTicket(ticket8);
        reservation1.addTicket(ticket9);
        reservation1.addTicket(ticket10);
        reservation1.addTicket(ticket11);
        reservation1.addTicket(ticket12);
        reservation1.addTicket(ticket13);
        reservation1.addTicket(ticket14);
        reservation1.addTicket(ticket15);
        reservation1.addTicket(ticket16);

        reservation2.addTicket(ticket3);
        reservation5.addTicket(ticket5);
        reservation3.addTicket(ticket4);
        reservation4.addTicket(ticket6);

        reservationService.createReservation(reservation1);
        reservationService.createReservation(reservation2);
        reservationService.createReservation(reservation3);
        reservationService.createReservation(reservation4);
        reservationService.createReservation(reservation5);

        System.out.println("Reservation: " + reservation1.getId());
        System.out.println("Tickets: " + reservation1.getTickets().size());
        System.out.println("Ticket reservation id" + reservation1.getTickets().get(0).getReservation().getId());
        System.out.println("Ticket reservation id" + reservation1.getTickets().get(1).getReservation().getId());

        System.out.println("Initial data created: " + movieRepository.count() + " movies being played in " + showingRepository.count() + " showings with" + reservationRepository.count() + " Reservations, reserving a total of " + ticketRepository.count() + " tickets for seats, also " + userRepository.count() + " users are created");


    }

}
