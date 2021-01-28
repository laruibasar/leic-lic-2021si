package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Tr;
import pt.isel.ls.view.common.elements.Ul;

import java.util.ArrayList;
import java.util.List;

public class GetUserDetailsResult extends CommandResult {

    private User user;
    private ArrayList<Review> reviews = new ArrayList<>();

    public GetUserDetailsResult() {

    }

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

    @Override
    public boolean asResult() {
        return user != null;
    }

    @Override
    public Object getResult() {
        return user;
    }

    @Override
    public int getResultId() {
        return 0;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
