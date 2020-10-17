package pt.isel.ls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;

public class App {
    public static void main(String[] args) {

        System.out.println("Hello LS");

        /*
         * url to use in getConnection
         * this is build with value from env vars:
         *   - LS_DB_HOST
         *   - LS_DB_DATABASE
         *   - LS_DB_USER
         *   - LS_DB_PASSWD
         */
        String url = "jdbc:postgresql://";
        String dbHost = System.getenv("LS_DB_HOST");
        if (dbHost == null || dbHost.length() == 0) {
            System.out.println("Set LS_DB_HOST for connection to database");
            System.exit(1);
        }
        url += dbHost;

        String dbDatabase = System.getenv("LS_DB_DATABASE");
        if (dbDatabase == null || dbDatabase.length() == 0) {
            System.out.println("Set LS_DB_DATABASE for connection to database");
            System.exit(1);
        }
        url += "/" + dbDatabase;

        /* Create a DB connection to use through our simple app */
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(url);

        String dbUser = System.getenv("LS_DB_USER");
        if (dbUser == null || dbUser.length() == 0) {
            System.out.println("Set LS_DB_USER for connection to database");
            System.exit(1);
        }

        String dbPasswd = System.getenv("LS_DB_PASSWD");
        if (dbPasswd == null || dbPasswd.length() == 0) {
            System.out.println("Set LS_DB_PASSWD for connection to database");
            System.exit(1);
        }

        Connection conn = null;
        try {
            conn = ds.getConnection(dbUser, dbPasswd);
            /* a simple select */
            String query = "SELECT * FROM students";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Before DELETE");
            while (rs.next()) {
                System.out.println("Result: "
                        + rs.getInt(1)
                        + " " + rs.getString(2));
            }

            /* a simple delete */
            conn.setAutoCommit(false);

            String queryDelete = "DELETE FROM students WHERE course = ?";
            stmt = conn.prepareStatement(queryDelete);
            stmt.setInt(1, 2); /* LEETC */

            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("Deleted from course LEETC");
            } else {
                System.out.println("Nothing to delete");
            }

            /* Check */
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            System.out.println("After DELETE");
            while (rs.next()) {
                System.out.println("Result: "
                        + rs.getInt(1)
                        + " " + rs.getString(2));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.getStackTrace();
            }

            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.rollback(); // Change to conn.commit() to save
                    conn.close();
                    System.out.println("Sucess on try delete but rollbacked");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.getStackTrace();
                }
            }
        }
    }
}
