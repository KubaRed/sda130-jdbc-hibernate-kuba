package movies.service;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;
import movies.repository.JDBCMoviesRepository;

import java.sql.*;
import java.util.List;

//menu
public class MovieService {
    private JDBCMoviesRepository repository = new JDBCMoviesRepository();

    public void save(Movie movie) throws SQLException, MovieServiceException {
        if (movie.getPremiereYear() < 1800 || movie.getPremiereYear() > 2100) {
            throw new MovieServiceException("Podano nierealną datę premiery.\n" +
                    "Powinien być przedział: 1800 - 2100");
        }
        repository.save(movie);
    }

    public List<Movie> findAllMovies() throws SQLException {
        return repository.findAllMovies();
    }

    public void closeAllResources() throws SQLException {
        repository.closeAllResources();
    }

    //każda metoda powinna operować na jednym poziomie abstrakcji
}
