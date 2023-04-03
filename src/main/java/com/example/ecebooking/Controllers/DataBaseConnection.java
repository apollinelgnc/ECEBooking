package com.example.ecebooking.Controllers;

import java.sql.*;

public class DataBaseConnection {
    public Connection databaseLink;
    public Connection getConnection() throws SQLException {
        String databaseName="login";
        String databaseUser="root";
        String databasePassword="0802";
        String url="jdbc:mysql://localhost/"+databaseName;
        try{
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;

    }
}
