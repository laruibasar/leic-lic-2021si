package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.services.exceptions.InvalidAverageException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.sql.SQLException;

/**
 * GET /tops/ratings - returns a list with the movies, given the following parameters:
 *
 * n - max number of movies to list;
 * average - two possible values:
 * highest- movies with the highest average ratings
 * lowest- movies with the lowest average ratings
 * min - minimum number of votes
 */

public class GetTopRatingsHandler extends Handler implements IHandler {

    private LinkedList<Model> topRatings = new LinkedList<>();

    public GetTopRatingsHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"n", "average", "min"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException, EmptyResult, InvalidAverageException {
        Data mapper = new Data();
        Connection conn = null;
        String avg = cmd.getParameters().getValue("average");
        int average;
        switch (avg) {
            case "highest":
                average = 1;
                break;
            case "lowest":
                average = 0;
                break;
            default:
                throw new InvalidAverageException("Invalid Parameter found, only accept values; highest or lowest ");
        }
        try {
            conn = mapper.getDataConnection().getConnection();
            final String query = "select mid, name, year\n"
                    +
                    "from (movies join "
                    +
                    "(select rating, movie from ratings union all select rating, movie from reviews) as rates "
                    +
                    "on(movies.mid = rates.movie))\n"
                    +
                    "group by name, year\n"
                    +
                    "having count(rating) > ?\n"
                    +
                    "ORDER BY (CASE WHEN 1=? THEN avg(rating) END) DESC,\n"
                    +
                    "\t\t (CASE WHEN 2=2 THEN avg(rating) END) ASC\n"
                    +
                    "FETCH FIRST ? ROWS ONLY;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, Integer.parseInt(cmd.getParameters().getValue("min")));
            pstmt.setInt(2,average);
            pstmt.setInt(3, Integer.parseInt(cmd.getParameters().getValue("n")));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                topRatings.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }

        return new CommandResult(topRatings);
    }
}
