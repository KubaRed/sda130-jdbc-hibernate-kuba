package movies.controller;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;
import movies.repository.JDBCMoviesRepository;
import movies.service.MovieService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public abstract class Controller {

    private boolean running = true;
    private MovieService movieService = new MovieService();
    private static final String OPTIONS = """
            Wybierz jedną z opcji:
            1. Dodaj nowy film
            2. Wyświetl filmy
            3. Koniec""";

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        int input = readInt(OPTIONS);
        handleOption(input);
    }

    private void handleOption(int input) {
        try {
            executeOption(input);
        } catch (SQLException | MovieServiceException e) {
            showMessage("Błąd zapytania do BD");
        }
    }

    private void executeOption(int input) throws SQLException, MovieServiceException { //todo dekompozycja!
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

    private void addMovie() throws SQLException, MovieServiceException {
        Movie movie = readMovieData();
        movieService.save(movie);
    }

    private void showMovies() throws SQLException {
        List<Movie> movies = movieService.findAllMovies();
        showMovies(movies);
    }


    private Movie readMovieData() {
        String title = readString("Podaj tytuł filmu: ");
        int premiereYear = readInt("Podaj rok premiery: ");
        String genre = readString("Podaj gatunek: ");
        int rate = readInt(JOptionPane.showInputDialog("Ocena: "));
        return new Movie(title, premiereYear, genre, rate);
    }

    private void showMovies(List<Movie> movies) {
        String allFilms = "";
        for (Movie movie : movies) {
            allFilms += movie + "\n";
        }
        showMessage(allFilms);
    }

    private void end() throws SQLException {
        System.out.println("Koniec");
        running = false;
        movieService.closeAllResources();
    }

    abstract void showMessage(String message);
    abstract int readInt(String message);
    abstract String readString(String message);

}
