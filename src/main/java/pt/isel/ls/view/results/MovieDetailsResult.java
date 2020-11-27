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

public class MovieDetailsResult extends CommandResult{

    private Movie movie;

    public MovieDetailsResult(List<Model> movie){
        if(movie.size() != 1 || !(movie instanceof Movie)){
            //create exception
        }

        this.movie = (Movie) movie.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {
                "" + movie.getMid(),
                "" + movie.getTitle(),
                "" + movie.getYear()});
        //StringBuilder sb = new StringBuilder();
        Html h = new Html(
                new Head(
                        new Title("User details")
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
        return "" +
                movie.getMid() +
                " " +
                movie.getTitle() +
                " " +
                movie.getYear();
    }
}
