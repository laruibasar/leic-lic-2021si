package pt.isel.ls.view.text;

import pt.isel.ls.model.MovieRating;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;


public class GetMovieRatingsTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        MovieRating movieRating = (MovieRating) cr.getResult();

        if (movieRating == null) {
            return "No movie rating available";
        }

        StringBuilder sb = new StringBuilder("Movie rating:\n");
        sb.append(movieRating.getMovieTitle()).append(" average: ").append(movieRating.getAverage());
        sb.append("\n");

        sb.append("Rates:\t1 \t2\t3\t4\t5\n");
        sb.append("Votes:\t" + movieRating.getVotesOne());
        sb.append("\t").append(movieRating.getVotesTwo());
        sb.append("\t").append(movieRating.getVotesThree());
        sb.append("\t").append(movieRating.getVotesFour());
        sb.append("\t").append(movieRating.getVotesFive());

        return sb.toString();
    }
}
