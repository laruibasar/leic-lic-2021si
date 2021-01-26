package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Rating;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Table;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

import java.util.ArrayList;
import java.util.List;

public class RateMovieResult extends CommandResult {

    private Rating rating;

    public RateMovieResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.rating = (Rating) ratings.get(0);
        }

    }

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

    public String printPlainText() {
        return rating == null ? "Rating not created" : "Created Rating: "
                + "\nRating = " + rating
                + "\nRating id = " + rating.getRatingId()
                + "\nMovie id = " + rating.getMovieId();
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
