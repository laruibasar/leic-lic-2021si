package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;
import java.util.ArrayList;
import java.util.List;

public class GetMovieRatingResult extends CommandResult {

    private final MovieRating movieRating;

    public GetMovieRatingResult(List<Model> ratings) {
        if(ratings.size() != 1 || !(ratings instanceof MovieRating)){
            //create exception
        }

        this.movieRating = (MovieRating) ratings;
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
            rows.add(
                    new String[] {
                            String.valueOf(movieRating.getMovieId()),
                            String.valueOf(movieRating.getAverage()),
                            String.valueOf(movieRating.getVotesOne()),
                            String.valueOf(movieRating.getVotesTwo()),
                            String.valueOf(movieRating.getVotesThree()),
                            String.valueOf(movieRating.getVotesFour()),
                            String.valueOf(movieRating.getVotesFive())
                    }
            );


        Html h = new Html(
                new Head(
                        new Title("Movie Rating")
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
        return "Movie Rating -> " +
                movieRating.toString();
    }
}
