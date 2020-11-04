package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMovieHandler extends Handler implements IHandler {

    /**
     *POST /movies - creates a new movie, given the following parameters
     *  title - the movie's name.
     *  releaseYear - the movie's release year.
     */
    private final String query = "insert into movies(title,year) values(?,?)";

    public CreateMovieHandler() {
        super();
        template.setParameters(new Parameters(new String[]{"title", "releaseYear"}));
    }

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        CommandResult result;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cmd.getParameters().getValue("name"));
            pstmt.setInt(2, Integer.parseInt(cmd.getParameters().getValue("releaseYear")));
            result = new CommandResult(pstmt.executeUpdate());
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            if(conn != null)
                conn.rollback();
            throw new DataConnectionException("Unable to add movie\n"
                    + e.getMessage(), e);
        }finally {
            mapper.closeConnection(conn);
        }
        return result;
    }
}
