package pt.isel.ls.view.text;

import pt.isel.ls.model.Rating;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class RateMovieTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Rating rating = (Rating) cr.getResult();
        return rating == null ? "Rating not created" : "Created Rating: "
                + "\nRating = " + rating
                + "\nRating id = " + rating.getRatingId()
                + "\nMovie id = " + rating.getMovieId();
    }
}
