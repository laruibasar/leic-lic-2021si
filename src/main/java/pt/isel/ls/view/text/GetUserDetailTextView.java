package pt.isel.ls.view.text;

import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.GetUserDetailsResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.ArrayList;

public class GetUserDetailTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        /*
         * Due to the way CommandResult is defined, we need to cast it to access
         * all information available
         */
        GetUserDetailsResult result = (GetUserDetailsResult) cr;

        User user = (User) result.getResult();
        ArrayList<Review> reviews = result.getReviews();

        StringBuilder sb = new StringBuilder();
        sb.append("UserId = " + user.getId() + "\nName   = " + user.getName());
        if (user.getEmail() != null) {
            sb.append("\nEmail  = " + user.getEmail());
        }
        if (reviews != null) {
            for (Review r: reviews) {
                sb.append("\n\n");
                sb.append("Rating: ").append(r.getRating()).append("\n");
                sb.append("Title: ").append(r.getMovie().getTitle()).append("\n");
                sb.append("Year: ").append(r.getMovie().getYear()).append("\n");
                sb.append("Summary: ").append(r.getSummary());
            }
        }

        return user == null ? "User details not available" : "User details:\n" + sb.toString();
    }
}
