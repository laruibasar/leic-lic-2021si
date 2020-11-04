package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.*;
import java.util.LinkedList;

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

    private LinkedList<Movie> topRatings = new LinkedList<>();
    private LinkedList<String> tuple = new LinkedList<>();
    private final String query = "select mid, name, year\n" +
            "from (movies join (select rating, movie from ratings union all select rating, movie from reviews) as rates on(movies.mid = rates.movie))\n" +
            "group by name, year\n" +
            "having count(rating) > ?\n" +
            "ORDER BY (CASE WHEN 1=? THEN avg(rating) END) DESC,\n" +
            "\t\t (CASE WHEN 2=2 THEN avg(rating) END) ASC\n" +
            "FETCH FIRST ? ROWS ONLY;";

    public GetTopRatingsHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"n", "average", "min"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, Integer.parseInt(cmd.getParameters().getValue("min")));
            String average = cmd.getParameters().getValue("average");
            switch (average){
                case "highest":
                    pstmt.setInt(2,1);
                    break;
                case "lowest":
                    pstmt.setInt(2,0);
                    break;
                default: throw new InvalidAverageException("Invalid ");
            }
            pstmt.setInt(3, Integer.parseInt(cmd.getParameters().getValue("n")));

            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= columnsNumber; i++) {
                    tuple.add(rs.getString(i));
                }
                topRatings.add( new Movie(
                        Integer.parseInt(tuple.get(0)),
                        tuple.get(1),
                        Integer.parseInt(tuple.get(2))));
                tuple.clear();
            }
            conn.commit();
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to get a list of all the movies\n"
                    + e.getMessage(), e);
        } catch (InvalidAverageException e) {
            e.printStackTrace();
        } finally {
            mapper.closeConnection(conn);
        }

        return new CommandResult(topRatings);
    }

    private static class InvalidAverageException extends Throwable {
        public InvalidAverageException(String message){
            super(message);
        }
    }
}
