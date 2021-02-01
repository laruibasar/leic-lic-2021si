package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetUserDetailsResult extends CommandResult {

    private User user;
    private ArrayList<Review> reviews = new ArrayList<>();

    public GetUserDetailsResult() {

    }

    public GetUserDetailsResult(List<Model> users) {
        if (users.size() != 0) {
            this.user = (User) users.get(0);
            reviews = user.getReviews();
        }
    }

    @Override
    public boolean asResult() {
        return user != null;
    }

    @Override
    public Object getResult() {
        return user;
    }

    @Override
    public int getResultId() {
        return 0;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
