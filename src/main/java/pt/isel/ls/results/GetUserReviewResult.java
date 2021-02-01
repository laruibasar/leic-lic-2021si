package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import java.util.List;

public class GetUserReviewResult extends CommandResult {

    private Review review;

    public GetUserReviewResult(List<Model> reviews) {
        if (reviews.size() != 0) {
            this.review = (Review) reviews.get(0);
        }

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
        return 0;
    }
}
