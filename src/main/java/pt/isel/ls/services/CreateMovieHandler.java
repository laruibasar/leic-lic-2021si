package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateMovieHandler extends Handler implements IHandler {

    /**
     *POST /movies - creates a new movie, given the following parameters
     *  title - the movie's name.
     *  releaseYear - the movie's release year.
     */

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {
        Data mapper = new Data();
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();

            String query = "insert into movies(name, age, genre, castAndDirectors) values\n" +
                    "'"+ "'," + //name
                    "'"+ "'," + //age
                    "'null'," + //null
                    "'null'";   //null
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            CommandResult cr = new CommandResult(rs);
            return cr;
            //conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new DataConnectionException("Unable to list all users\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
        }
    }
}
