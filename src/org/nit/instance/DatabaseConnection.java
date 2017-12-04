package org.nit.instance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	private Connection con;
	private static DatabaseConnection dbc;
	
	private DatabaseConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs430_pharmacy?zeroDateTimeBehavior=convertToNull","root","Manchesterutd1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection Failed: "+ e);
			}
			System.out.println("Connection Established");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public static DatabaseConnection getDatabaseConnection(){
		if(dbc == null){
			dbc = new DatabaseConnection();
		}
		return dbc;
	}
	
	public Connection getConnection(){
		return con;
	}
}
