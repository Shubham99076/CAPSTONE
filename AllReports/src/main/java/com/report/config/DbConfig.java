package com.report.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
	
	private final static String JDBC_URL="jdbc:mysql://localhost:3306/capstonedb";
	private final static String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private final static String DB_USERID="root";
	private final static String DB_PASSWORD="root";


	public static Connection getConnection() throws SQLException, ClassNotFoundException{

		Class.forName(JDBC_DRIVER);
		
		/********For Local Database*********/
	Connection con= DriverManager.getConnection(JDBC_URL,DB_USERID,DB_PASSWORD);
		
		/********For server Database*********/
	//String url = "jdbc:mysql://localhost:3306/inventor_phix";  
		//Connection con = DriverManager.getConnection(url,"inventor_phix","Phixman@12345");
		  
		return con;
	}

	public static void closeConnection(Connection con){
		try {
		if(con!=null && !con.isClosed())
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
