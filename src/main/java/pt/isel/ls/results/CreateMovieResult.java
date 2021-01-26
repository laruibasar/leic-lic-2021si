package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;

import java.util.List;

public class CreateMovieResult extends CommandResult {

    private Movie movie;

    public CreateMovieResult() {
    }

    public CreateMovieResult(List<Model> movies) {
        if (movies.size() != 0) {
            this.movie = (Movie) movies.get(0);
        }
    }

    @Override
    public Object getResult() {
        return movie;
    }

    @Override
    public int getResultId() {
        return (movie != null) ? movie.getMid() : 0;
    }

    @Override
    public boolean asResult() {
        return movie != null;
    }
}
