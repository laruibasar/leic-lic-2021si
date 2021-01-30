package pt.isel.ls.results;

import pt.isel.ls.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GetMoviesResult extends CommandResult {
    private List<Model> movies = new LinkedList<>();

    public GetMoviesResult(List<Model> movies) {
        this.movies = movies;
    }

    public GetMoviesResult() {

    }

    @Override
    public boolean asResult() {
        return !movies.isEmpty();
    }

    @Override
    public Object getResult() {
        return movies;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
