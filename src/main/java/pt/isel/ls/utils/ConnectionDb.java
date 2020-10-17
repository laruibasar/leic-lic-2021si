package pt.isel.ls.utils;

import java.sql.*;
import java.util.Properties;

public class ConnectionDb {


    private final String url = "jdbc:postgresql://localhost:5432/ls";
    private String user;
    private String password;
    Connection con;

    ConnectionDb(String u, String p){
        this.user = u;
        this.password = p;
    }

    public int AddTuple(){
        //TODO complete method
        Connection con = getConnection();
        return 0;
    }

    public int ChangeTuple(){
        //TODO complete method
        Connection con = getConnection();
        return 0;
    }

    public int DeleteTuple(){
        //TODO complete method
        Connection con = getConnection();
        return 0;
    }

    public Connection getConnection(){

        try {
            Properties props = new Properties();
            props.setProperty("user",user);
            props.setProperty("password",password);
            con = DriverManager.getConnection(url,props);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }



}
