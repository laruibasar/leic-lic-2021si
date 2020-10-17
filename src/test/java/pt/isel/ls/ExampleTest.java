package pt.isel.ls;

import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void example() {
        // arrange | given
        int a = 1;
        int b = 2;

        // act | when
        int result = a + b;

        // assert | then
        assertEquals(3, result);
    }

    @Test(expected = FileNotFoundException.class)
    public void do_not_ignore_unexpected_exceptions_on_tests() throws FileNotFoundException {
        // test methods can have a non-empty `throws` exception list.
        new FileInputStream("does-not-exist");
    }

    @Test
    public void sql_delete_test() {
        String url = "jdbc:postgresql://";
        String dbHost = System.getenv("LS_DB_HOST");
        if (dbHost == null || dbHost.length() == 0) {
            System.out.println("Set LS_DB_HOST for connection to database");
        }
        url += dbHost;

        String dbDatabase = System.getenv("LS_DB_DATABASE");
        if (dbDatabase == null || dbDatabase.length() == 0) {
            System.out.println("Set LS_DB_DATABASE for connection to database");
        }
        url += "/" + dbDatabase;

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setURL(url);

        String dbUser = System.getenv("LS_DB_USER");
        if (dbUser == null || dbUser.length() == 0) {
            System.out.println("Set LS_DB_USER for connection to database");
        }

        String dbPasswd = System.getenv("LS_DB_PASSWD");
        if (dbPasswd == null || dbPasswd.length() == 0) {
            System.out.println("Set LS_DB_PASSWD for connection to database");
        }

        Connection conn = null;
        int result = 0;
        try {
            conn = ds.getConnection(dbUser, dbPasswd);
            conn.setAutoCommit(false);

            String queryDelete = "DELETE FROM students WHERE course = ?";
            PreparedStatement stmt = conn.prepareStatement(queryDelete);
            stmt.setInt(1, 3); /* LEEE */

            result = stmt.executeUpdate();

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
                    assertEquals(1, result);
                    conn.rollback();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.getStackTrace();
                }
            }
        }
    }
}
