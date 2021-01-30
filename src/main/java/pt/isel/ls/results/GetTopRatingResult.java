package pt.isel.ls.results;

import pt.isel.ls.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GetTopRatingResult extends CommandResult {

    private List<Model> movies = new LinkedList<>();

    public GetTopRatingResult() {

    }

    public GetTopRatingResult(List<Model> movies) {
        this.movies = movies;
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
