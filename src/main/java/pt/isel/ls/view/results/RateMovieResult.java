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
        if(ratings.size() != 1 || !(ratings instanceof Rating)){
            //create exception
        }

        this.rating = (Rating) ratings.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(
                new String[] {
                        "" + rating.getRatingId(),
                        "" + rating.getMovieId(),
                        "" + rating.getRating()
                }
        );
        Html h = new Html(
                new Head(
                        new Title("Created Rating")
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
        return "Created Rating -> " +
                rating.toString();
    }
}
