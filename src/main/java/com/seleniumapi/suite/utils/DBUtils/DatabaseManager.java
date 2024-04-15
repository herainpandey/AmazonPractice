package com.seleniumapi.suite.utils.DBUtils;

import com.seleniumapi.suite.Constants.Constants;

import java.sql.*;

public class DatabaseManager {
private static Connection conn = null;

private static final DatabaseManager instance = new DatabaseManager();
private DatabaseManager(){

}

public static DatabaseManager getInstance(){
    return instance;
}

private DatabaseManager getDBConnection() throws SQLException {

    conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER_NAME, Constants.DB_PASSWORD);

    if(!conn.isClosed()){
        System.out.println("We have successfully connected to database");
    }
    return this;
}

public ResultSet getData(String sql) throws SQLException {
    instance.getDBConnection();

    Statement st = conn.createStatement();

    ResultSet result = st.executeQuery(sql);
    return  result;
}

}
