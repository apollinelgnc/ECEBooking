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
        Statement stmt = databaseLink.createStatement();
        ResultSet res=stmt.executeQuery("SELECT * FROM login.user_client");
        while(res.next()){
            System.out.println(res.getInt("User_client"));
            System.out.println(res.getString("name_client"));
            System.out.println(res.getString("idUser_client"));
            System.out.println(res.getString("mdptUse_client"));
        }
        return databaseLink;

    }
}
