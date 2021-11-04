/**
 * @auhtor 1972004 - Yehezkiel Christian
 */
package com.example.squiddemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection (){
        Connection connection =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SquidDB",
                    "root",
                    "");
        } catch (ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }

        return connection;
    }
}
