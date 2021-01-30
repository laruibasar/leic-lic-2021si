package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Rating;
import java.util.List;

public class RateMovieResult extends CommandResult {

    private Rating rating;

    public RateMovieResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.rating = (Rating) ratings.get(0);
        }

    }

    @Override
    public boolean asResult() {
        return rating != null;
    }

    @Override
    public Object getResult() {
        return rating;
    }

    @Override
    public int getResultId() {
        return (rating != null) ? rating.getRatingId() : 0;
    }
}
