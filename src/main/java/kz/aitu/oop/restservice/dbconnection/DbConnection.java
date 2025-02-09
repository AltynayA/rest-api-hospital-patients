package kz.aitu.oop.restservice.dbconnection;
import java.sql.*;
public class DbConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:potgresql://localhost:5432/postgres";
        String user = "altynaiayazbayeva";
        String password = "444";
        return DriverManager.getConnection(url, user, password);
    }
//    when repository is created, data pull is needed
}

