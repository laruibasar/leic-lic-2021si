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

        try {
            db.getConnection();
            db.con.setAutoCommit(false);
            //TODO create statemente then execute it and check return value to see if the test is successful

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                db.con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void ChangeStudent(){
        //TODO Create test that change the content of a tuple
    }


}
