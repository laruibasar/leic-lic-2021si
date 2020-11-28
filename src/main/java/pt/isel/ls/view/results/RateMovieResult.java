package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Rating;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;
import java.util.List;

public class RateMovieResult extends CommandResult{

    private Rating rating;

    public RateMovieResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.rating = (Rating) ratings.get(0);
        }

    }

    @Override
    public String printHTML() {
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
                                rows
                        )
                )

        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return rating == null ? "Rating not created" : "Created Rating -> " + rating.toString();
    }
}
