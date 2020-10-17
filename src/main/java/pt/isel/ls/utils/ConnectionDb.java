package pt.isel.ls.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;

public class ConnectionDb {

    private String url = "jdbc:postgresql://";
    private String user;
    private String password;
    private String host;
    private String database;
    Connection con;

    public ConnectionDb() {

        this.user = System.getenv("LS_DB_USER");
        if (user == null || user.length() == 0) {
            System.out.println("Set LS_DB_USER for connection to database");
            System.exit(1);
        }

        this.password = System.getenv("LS_DB_PASSWD");
        if (password == null || password.length() == 0) {
            System.out.println("Set LS_DB_PASSWD for connection to database");
            System.exit(1);
        }

        this.host = System.getenv("LS_DB_HOST");
        if (host == null || host.length() == 0) {
            System.out.println("Set LS_DB_HOST for connection to database");
            System.exit(1);
        }
        url += host;

        this.database = System.getenv("LS_DB_DATABASE");
        if (database == null || database.length() == 0) {
            System.out.println("Set LS_DB_DATABASE for connection to database");
            System.exit(1);
        }
        url += "/" + database;
    }

    public boolean consultQuery(String query) {
        boolean rs = false;

        try {
            //Obter um statement
            Statement stmt = con.createStatement();

            //executar o comando de select
            ResultSet res = stmt.executeQuery(query);

            rs = res.next();

            //fechar o Statement
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public int workTuple(String query) {
        int retVal = 0;
        try {
            //Obter um statement para o comando parametrizado
            PreparedStatement pstmt = con.prepareStatement(query);

            //executar o comando de inserção
            retVal = pstmt.executeUpdate();

            //fechar o Statement
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return retVal;
    }


    public Connection getConnection() {

        con = null;

        try {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setURL(url);
            con = ds.getConnection(user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return con;
    }
}
