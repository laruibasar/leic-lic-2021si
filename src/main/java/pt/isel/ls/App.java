package pt.isel.ls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;

public class App {
    public static void main(String[] args) {

        System.out.println("Hello LS");

        /* Create a DB connection to use through our simple app */
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName("testdb");
        ds.setUser("test");
        ds.setPassword("test");

        Connection conn = null;
        try {
            conn = ds.getConnection();
            // use connection
            // a simple select
            PreparedStatement query = conn.prepareStatement("SELECT * FROM "
                    + "students WHERE course = ?");
            query.setInt(1, 1);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                System.out.print("1: (SELECT) Result: " + rs.getInt(1) + " " + rs.getString(2));
            }
            rs.close();
            query.close();

        } catch (SQLException e) {
            // log error
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
