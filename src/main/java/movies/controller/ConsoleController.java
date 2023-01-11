package movies.controller;

import movies.service.MovieService;
import movies.model.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//pozwala na interakcję z aplikacją (wprowadzanie danych, prezentacja rezultatów)
public class ConsoleController {
    private boolean running = true;
   private MovieService movieService = new MovieService();  //tylko jeden service na kontroler!

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        showOptions();
        int input = readDecision();
        handleOption(input);
    }

    private void showOptions() {
        System.out.println("""
                Wybierz jedną z opcji:
                1. Dodaj nowy film
                2. Wyświetl filmy
                3. Koniec""");
    }

    private int readDecision() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void handleOption(int input) {
        try {
            executeOption(input);
        } catch (SQLException e) {
            System.out.println("Błąd zapytania do BD");
        }
    }

    private void executeOption(int input) throws SQLException{ //todo dekompozycja!
        switch (input) {
            case 1:
                Movie movie = readMovieData();
                movieService.save(movie);
                break;
            case 2:
               List<Movie> movies = movieService.findAllMovies();
               showMovies(movies);
                break;
            case 3:
                end();
                break;
        }
    }

    private Movie readMovieData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj tytuł:");
        String title = scanner.nextLine();
        System.out.print("Podaj rok premiery:");
        int premiereYear = scanner.nextInt();
        if (premiereYear < 1800 || premiereYear > 2100) {
            System.out.println("Podano nierealną datę premiery. " +
                    "Powinien być przedział: 1800 - 2100");
            return readMovieData();
        }
//        scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.print("Podaj gatunek:");
        String genre = scanner.nextLine();
        System.out.print("Podaj ocenę (1-5):");
        int rate = scanner.nextInt();
        return new Movie(title, premiereYear, genre, rate);
    }

    private void showMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void end() throws SQLException {
        System.out.println("Koniec");
        running = false;
        movieService.closeAllResources();
    }
}
