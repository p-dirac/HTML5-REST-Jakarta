package server.tools.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private    static final String dburl = "jdbc:mysql://localhost:3306/simplemodels";	   
	private    static final String dbuser = "root";
	private    static final String dbpass = "zpplfTrff#6%";
	//
	// if autoCommit is false, connection.commit must be called after every transaction,
    // or call connection.rollback() in case of error
	private    static final boolean autoCommit = false;
	   
	   
    public static Connection openDbConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(dburl, dbuser, dbpass);
            System.out.println("openDbConnection, connection: " + connection);
            //
            // if autocommit is false, connection.commit must be called after every transaction,
            // or call connection.rollback() in case of error
            connection.setAutoCommit(autoCommit);
            //
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return connection;
    }

}
