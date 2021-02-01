package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.MovieRating;

import java.util.List;

public class GetMovieRatingResult extends CommandResult {
    private MovieRating movieRating;

    public GetMovieRatingResult() {

    }

    public GetMovieRatingResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.movieRating = (MovieRating) ratings.get(0);
        }
    }

    @Override
    public boolean asResult() {
        return movieRating != null;
    }

    @Override
    public Object getResult() {
        return movieRating;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
