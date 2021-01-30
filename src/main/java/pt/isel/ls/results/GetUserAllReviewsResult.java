package pt.isel.ls.results;

import pt.isel.ls.model.Model;

import java.util.List;

public class GetUserAllReviewsResult extends CommandResult {

    private final List<Model> reviews;

    public GetUserAllReviewsResult(List<Model> reviews) {
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
