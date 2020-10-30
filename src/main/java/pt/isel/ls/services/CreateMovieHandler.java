package pt.isel.ls.services;

import pt.isel.ls.data.Data;
import pt.isel.ls.data.DataConnectionException;
import pt.isel.ls.model.Movie;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;

import java.sql.*;

public class CreateMovieHandler extends Handler implements IHandler {

    /**
     *POST /movies - creates a new movie, given the following parameters
     *  title - the movie's name.
     *  releaseYear - the movie's release year.
     */

    @Override
    public CommandResult execute(Command cmd) throws DataConnectionException, SQLException {

        //TODO: Discutir implmentação do modelo
        //Movie movie = new Movie(); Retornar parametro de Parameters individualmente

        Data mapper = new Data();
        CommandResult cr = null;
        Connection conn = null;
        try {
            conn = mapper.getDataConnection().getConnection();

            final String query = "insert into movies(name, age, genre, castAndDirectors) values(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(query);

            /*
            pstmt.setString(1,movie.getTitle());
            pstmt.setString(2,String.valueOf((movie.getReleaseDate())));
            pstmt.setString(3,movie.getGenre());
            pstmt.setString(4,movie.getCastAndDirectors());

            OR...

            Iterate user Parameters in cmd and pstmt.setString() in loop
             */




            int rs = pstmt.executeUpdate();

            cr = new CommandResult(rs);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new DataConnectionException("Unable to add movie\n"
                    + e.getMessage(), e);
        } finally {
            mapper.closeConnection(conn);
            return cr;
        }
    }
}
