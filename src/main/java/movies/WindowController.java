package movies;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class WindowController {
    private boolean running = true;
    private String title;
    private int premiereYear;
    private String genre;
    private int rate;
    private int option;
    private MovieService movieService= new MovieService();

    public WindowController(){

    }

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        handleOption(getOptionFromAvailable());
    }



    private int getOptionFromAvailable() {
        return option = Integer.parseInt(JOptionPane.showInputDialog("""
                Wybierz jedną z opcji:
                1. Dodaj nowy film
                2. Wyświetl filmy
                3. Koniec"""));
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
                JOptionPane.showMessageDialog(null, movies);

                showMovies(movies);
                break;
            case 3:
                end();
                break;
        }
    }


    private Movie readMovieData() {
        this.title = JOptionPane.showInputDialog("Podaj tytuł filmu: ");
        this.premiereYear = Integer.parseInt(JOptionPane.showInputDialog("Podaj rok premiery: "));
        if (premiereYear < 1800 || premiereYear > 2100) {
            JOptionPane.showMessageDialog(null, "Podano nierealną datę premiery.\n" +
                    "Powinien być przedział: 1800 - 2100");
            System.out.println("Podano nierealną datę premiery. " +
                    "Powinien być przedział: 1800 - 2100");
            return readMovieData();
        }
        this.genre = JOptionPane.showInputDialog("Podaj gatunek: ");
        this.rate = Integer.parseInt(JOptionPane.showInputDialog("Ocena: "));
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
