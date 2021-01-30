package pt.isel.ls.results;

import pt.isel.ls.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GetMovieAllReviewsResult extends CommandResult {

    private List<Model> reviews = new LinkedList<>();

    public GetMovieAllReviewsResult() {

    }

    public GetMovieAllReviewsResult(List<Model> reviews) {

        this.reviews = reviews;
    }

    @Override
    public boolean asResult() {
        return !reviews.isEmpty();
    }

    @Override
    public Object getResult() {
        return reviews;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
