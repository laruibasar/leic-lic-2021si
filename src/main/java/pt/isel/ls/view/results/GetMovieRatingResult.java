package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.MovieRating;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;
import java.util.ArrayList;
import java.util.List;

public class GetMovieRatingResult extends CommandResult {

    private MovieRating movieRating;

    public GetMovieRatingResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.movieRating = (MovieRating) ratings.get(0);
        }

    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();

        if (movieRating == null){
            rows.add(new String[]{"No movie rating available"});
        } else {
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
        }

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
        return movieRating == null ? "No movie rating available" :"Movie Rating -> " + movieRating.toString();
    }
}
