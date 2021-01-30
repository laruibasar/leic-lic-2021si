package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;

import java.util.List;

public class GetMovieDetailsResult extends CommandResult {

    private Movie movie;

    public GetMovieDetailsResult() {

    }

    public GetMovieDetailsResult(List<Model> movies) {
        if (movies.size() != 0) {
            this.movie = (Movie) movies.get(0);
        }
    }

    @Override
    public boolean asResult() {
        return movie != null;
    }

    @Override
    public Object getResult() {
        return movie;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
