package pt.isel.ls.services;

import pt.isel.ls.utils.CommandResult;

public class CreateMovieHandler extends Handler implements IHandler {

    /**
     *POST /movies - creates a new movie, given the following parameters
     *  title - the movie's name.
     *  releaseYear - the movie's release year.
     */
    @Override
    public CommandResult execute() {
//        String query = "insert into movies(name, age, genre, castAndDirectors) values\n" +
//                 "('???????'," +
//                " ?????????," +
//                " null," +
//                " null," +
//                " null'," +
//                " null," +
//                " null," +
//                " null," +
//                " null),";
//        try (Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//        }
//        return rs;
        return null;
    }
}
