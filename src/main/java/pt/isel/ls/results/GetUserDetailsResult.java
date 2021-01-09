package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Br;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Li;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Tr;
import pt.isel.ls.view.common.Ul;

import java.util.ArrayList;
import java.util.List;

public class GetUserDetailsResult extends CommandResult {

    private User user;
    private ArrayList<Review> reviews = new ArrayList<>();

    public GetUserDetailsResult(List<Model> users) {
        if (users.size() != 0) {
            this.user = (User) users.get(0);
            reviews = user.getReviews();
        }
    }

    @Override
    public String printHtml() {
        ArrayList<Element> rows = new ArrayList<>();
        for (Review r: reviews) {
            rows.add(
                new Tr(
                    new Td(new A(r.getSummary(), "/movies/"
                            + r.getMovie().getMid()
                            + "/reviews/"
                            + r.getId())),
                    new Td(new Text(String.valueOf(r.getRating()))),
                    new Td(new Text(r.getMovie().getTitle())),
                    new Td(new Text(String.valueOf(r.getMovie().getYear())))
                )
            );
        }
        Html h = new Html(
                new Head(
                        new Title("User Details")
                ),
                new Body(
                        new A("Home", "/"),
                        new Br(),
                        new A("Users", "/users/"),
                        new Ul(
                                new Li(new Text(user.getName())),
                                new Li(new Text(user.getEmail()))
                        ),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Summary"),
                                                new Th("Rating"),
                                                new Th("Title"),
                                                new Th("Year")
                                        )
                                ),
                                new Tbody(
                                        rows
                                )
                        )

                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        StringBuilder str = new StringBuilder("\nUserId = " + user.getId() + "\nName   = " + user.getName());
        if (user.getEmail() != null) {
            str.append("\nEmail  = " + user.getEmail());
        }
        if (reviews != null) {
            for (Review r: reviews) {
                str.append("\n\nRating: "
                        + r.getRating()
                        + "\nTitle: "
                        + r.getMovie().getTitle()
                        + "\nYear: "
                        + r.getMovie().getYear()
                        + "\nSummary: "
                        + r.getSummary());
            }

        }

        return user == null ? "User details not available" : "User details -> " + str.toString();
    }
}
