package pt.isel.ls;


import java.sql.*;
import java.util.Properties;

public class App {

    String url = "jdbc:postgresql://localhost:5432/ls";

    public static void main(String[] args) throws SQLException {

        App app = new App();
        final String CMD = "SELECT * FROM students";

        try {


            Properties props = new Properties();
            props.setProperty("user","tiago");
            props.setProperty("password","tiago123");
            Connection con = DriverManager.getConnection(app.url,props);

            if(con != null){
                System.out.println("Connection successfully!");
                //Obter um statement
                Statement stmt = con.createStatement();

                //executar o comando de select
                ResultSet res = stmt.executeQuery(CMD);

                app.printResults(res);

                //fechar o Statement
                stmt.close();
                //fechar a ligação
                con.close();
            }
            else
                System.out.println("Connection failed!");


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void printResults(ResultSet dr) {

        try {
            ResultSetMetaData rsmd=dr.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for (int n = 1; n <= columnsNumber; n++) System.out.printf(" %-10s",rsmd.getColumnName(n));
            System.out.println("");
            while(dr.next())
            {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = dr.getString(i);
                    System.out.printf(" %-10s",columnValue);
                }
                System.out.println("");

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}