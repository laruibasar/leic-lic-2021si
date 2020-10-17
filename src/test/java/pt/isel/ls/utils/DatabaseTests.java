package pt.isel.ls.utils;

import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseTests {

    @Test
    public void connection() {

        ConnectionDb db = new ConnectionDb();
        assertTrue(db.getConnection() != null);

        try {
            db.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void addStudent_wrongCourseNumber() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "insert into students (number, name, course) values"
                + "(12347,'Alexandre',0);";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertEquals(0, db.workTuple(cmd));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addStudent() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "insert into students (number, name, course) values"
                + "(12347,'Alexandre',1);";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertEquals(1, db.workTuple(cmd));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addStudent_wrongStudentNumber() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "insert into students (number, name, course) values"
                + "(11111,'Alexandre',1);";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertEquals(1, db.workTuple(cmd));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void changeStudent() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "update students set name = 'Alex' where number = 12345;";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertEquals(1, db.workTuple(cmd));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void removeStudent() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "delete from students where number = 12345;";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertEquals(1, db.workTuple(cmd));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void consultTuples() {
        ConnectionDb db = new ConnectionDb();
        String cmd = "select * from students;";
        db.getConnection();
        assertTrue(db.consultQuery(cmd) == true);

        try {
            db.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
