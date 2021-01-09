package pt.isel.ls.results;

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

    private MovieRating movieRating;

    public GetMovieRatingResult(List<Model> ratings) {
        if (ratings.size() != 0) {
            this.movieRating = (MovieRating) ratings.get(0);
        }

    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Movie Id");
        header.add("Average Rating");
        header.add("1");
        header.add("2");
        header.add("3");
        header.add("4");
        header.add("5");

        ArrayList<String[]> rows = new ArrayList<>();

        if (movieRating == null) {
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
                                header,
                                rows
                        )
                )
        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return movieRating == null ? "No movie rating available" : "Movie Rating -> "
                + "Movie id ="
                + movieRating.getMovieId()
                + "\tAverage Rating = "
                + movieRating.getAverage()
                + "\nNumber of votes: "
                + "\n\t1 = "
                + movieRating.getVotesOne()
                + "\n\t2 = "
                + movieRating.getVotesTwo()
                + "\n\t3 = "
                + movieRating.getVotesThree()
                + "\n\t4 = "
                + movieRating.getVotesFour()
                + "\n\t5 = "
                + movieRating.getVotesFive();
    }

    @Override
    public boolean asResult() {
        return movieRating != null;
    }
}
