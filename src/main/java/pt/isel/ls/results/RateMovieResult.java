package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class RateMovieResult extends CommandResult {

    private Rating rating;

    public RateMovieResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.rating = (Rating) ratings.get(0);
        }

    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Review Id");
        header.add("Movie Id");
        header.add("Rating");

        ArrayList<String[]> rows = new ArrayList<>();
        String title = "Created Rating";
        if (rating == null) {
            title = "Rating not created";
        } else {
            rows.add(
                    new String[] {
                            String.valueOf(rating.getRatingId()),
                            String.valueOf(rating.getMovieId()),
                            String.valueOf(rating.getRating())
                    }
            );
        }

        Html h = new Html(
                new Head(
                        new Title(title)
                ),
                new Body(
                        new Table(
                                header,
                                rows
                        )
                )

        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return rating == null ? "Rating not created" : "Created Rating -> "
                + "\nRating = "
                + rating
                + "\nRating id = "
                + rating.getRatingId()
                + "\nMovie id = "
                + rating.getMovieId();
    }
}
