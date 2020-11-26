package pt.isel.ls.data;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class TopRatingData extends Data implements ITopRatingData {
    @Override
    public CommandResult getTopRating(int number, int average, int min)
            throws DataConnectionException {
        CommandResult result = null;
        Connection conn = null;
        LinkedList<Model> top = new LinkedList<>();

        try {
            conn = getDataConnection().getConnection();

            final String query = "select mid, title, year\n"
                    + "from (movies join "
                    + "(select rating, movie from ratings union all select rating, movie from reviews) as rates "
                    + "on(movies.mid = rates.movie))\n"
                    + "group by mid, title, year\n"
                    + "having count(rating) > ?\n"
                    + "ORDER BY (CASE WHEN 1=? THEN avg(rating) END) DESC,\n"
                    + "\t\t (CASE WHEN 2=2 THEN avg(rating) END) ASC\n"
                    + "FETCH FIRST ? ROWS ONLY;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, min);
            stmt.setInt(2, average);
            stmt.setInt(3, number);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                top.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }

            rs.close();
            stmt.close();
            conn.commit();
            result = new CommandResult(top, top.size());
        } catch (Exception e) {
            rollbackConnection(conn);
            throw new DataConnectionException("Unable to list top movies!\n"
                    + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return null;
    }
}
