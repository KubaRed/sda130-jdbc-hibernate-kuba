package movies.controller;

import movies.service.MovieService;
import movies.model.Movie;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

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
                addMovie();
                break;
            case 2:
                showMovies();
                break;
            case 3:
                end();
                break;
        }
    }

    private void addMovie() throws SQLException {
        Movie movie = readMovieData();
        movieService.save(movie);
    }

    private void showMovies() throws SQLException {
        List<Movie> movies = movieService.findAllMovies();
        showMovies(movies);

    }


    private Movie readMovieData() {
        this.title = JOptionPane.showInputDialog("Podaj tytuł filmu: ");
        this.premiereYear = Integer.parseInt(JOptionPane.showInputDialog("Podaj rok premiery: "));
        if (premiereYear < 1800 || premiereYear > 2100) {
            JOptionPane.showMessageDialog(null, "Podano nierealną datę premiery.\n" +
                    "Powinien być przedział: 1800 - 2100");
            return readMovieData();
        }
        this.genre = JOptionPane.showInputDialog("Podaj gatunek: ");
        this.rate = Integer.parseInt(JOptionPane.showInputDialog("Ocena: "));
        return new Movie(title, premiereYear, genre, rate);
    }

    private void showMovies(List<Movie> movies) {
        String allFilms = "";
        for (Movie movie : movies) {
            allFilms += movie + "\n";
        }
        JOptionPane.showMessageDialog(null, allFilms);
    }

    private void end() throws SQLException {
        System.out.println("Koniec");
        running = false;
        movieService.closeAllResources();
    }
}
