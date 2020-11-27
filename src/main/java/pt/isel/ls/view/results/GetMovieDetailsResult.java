package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;
import java.util.List;

public class GetMovieDetailsResult extends CommandResult{

    private Movie movie;

    public GetMovieDetailsResult(List<Model> movie){
        if(movie.size() != 1 || !(movie instanceof Movie)){
            //create exception
        }

        this.movie = (Movie) movie.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {
                String.valueOf(movie.getMid()),
                movie.getTitle(),
                String.valueOf(movie.getYear())});

        Html h = new Html(
                new Head(
                        new Title("Movie details")
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
        return "Movie Details -> " +
                movie.toString();
    }
}
