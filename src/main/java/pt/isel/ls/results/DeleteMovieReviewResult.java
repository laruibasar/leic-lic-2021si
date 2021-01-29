package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import java.util.List;

public class DeleteMovieReviewResult extends CommandResult {

    private Review review;

    public DeleteMovieReviewResult(List<Model> reviews) {
        if (reviews.size() != 0) {
            this.review = (Review) reviews.get(0);
        }
    }

    public DeleteMovieReviewResult() {

    }

    @Override
    public boolean asResult() {
        return review != null;
    }

    @Override
    public Object getResult() {
        return review;
    }

    @Override
    public int getResultId() {
        return (review != null) ? review.getId() : 0;
    }
}
