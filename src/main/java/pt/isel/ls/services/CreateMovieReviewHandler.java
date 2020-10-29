package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

/**
 * POST /movies/{mid}/reviews - creates a new review for the movie identified by mid, given the following parameters
 */

public class CreateMovieReviewHandler extends Handler implements IHandler {

    @Override
    public CommandResult execute() {
            String query = "insert into reviews(rid, summary, completeReview, rating, movie, movieCritic) values\n" +
            //qual o valor de reviewID a inserir, incrementar 1 com o mais recente ?!
            "(?????," +
            "'reviewSummary'," +
            "'review'," +
            " rating," +
            //movie terá que ser substituído por movieID na base de dados
            " movieID," +
            //como sei o movieCritic sem estar essa informação no comando
            " ?????)";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
//        return rs;
        return null;
    }
}
