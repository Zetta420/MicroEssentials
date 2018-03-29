package fr.jak12210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static Connection conn;

    public static void connect(String host, String database, String port, String user, String password){

        if(!isConn()){
            try {

                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                System.out.println("BDD Connectee");
            }
            catch (SQLException e){
                e.printStackTrace();
                System.out.println("BDD Foiree");
            }
        }

    }

    public static void close(){

        if(isConn()){
            try{
                conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public static boolean isConn(){
        try {
            if ((conn == null) || (conn.isClosed()) || (conn.isValid(5))) {
                return false;
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection(){
        return conn;
    }


}
