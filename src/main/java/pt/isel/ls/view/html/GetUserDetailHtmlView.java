package pt.isel.ls.view.html;

import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.results.GetUserDetailsResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
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

public class GetUserDetailHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        /*
         * Due to the way CommandResult is defined, we need to cast it to access
         * all information available
         */
        GetUserDetailsResult result = (GetUserDetailsResult) cr;

        User user = (User) result.getResult();
        ArrayList<Review> reviews = result.getReviews();

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

        html = new Html(
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

        return html.print();
    }
}
