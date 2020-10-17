package pt.isel.ls.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class DatabaseTests {

    @Test
    public void connection(){

        ConnectionDb db = new ConnectionDb();
        assertTrue(db.getConnection() != null);

        try {
            db.con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void addStudent(){

        ConnectionDb db = new ConnectionDb();
        String cmd = "insert into students values(12347,'Alexandre',1);";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertTrue(db.workTuple(cmd) > 0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void ChangeStudent(){
        ConnectionDb db = new ConnectionDb();
        String cmd = "update students set name = 'Alex' where number = 12346;";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertTrue(db.workTuple(cmd) > 0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void RemoveStudent(){

        ConnectionDb db = new ConnectionDb();
        String cmd = "delete from students where number = 11111;";

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            assertTrue(db.workTuple(cmd) > 0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                db.con.rollback();
                db.con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
