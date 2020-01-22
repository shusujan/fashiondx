package com.fadx.maven.fasion.Utils;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;		


public class  SQLConnector {	
	
	public String ExecuteQuery (String SQLstatement, String clomunName) throws SQLException, ClassNotFoundException { 
	   	
		 //Load MS SQL JDBC Driver
		 Class.forName("net.sourceforge.jtds.jdbc.Driver");
	
		 //Creating connection to the database
		 Connection con = DriverManager.getConnection(PropertyReader.readDataProperty("dbURL"),
				 PropertyReader.readDataProperty("dbUsername"),PropertyReader.readDataProperty("dbPassword"));
		 if (con != null) {
             System.out.println("Connected to the Database...");
         }
		 
		 Statement st = con.createStatement();
		 
		 //String selectquery = "SELECT * FROM Miicrras..associate_master where AM_Status = 'A'";
		 //Executing the SQL Query and store the results in ResultSet
		 
		 ResultSet rs = st.executeQuery(SQLstatement);
		 //While loop to iterate through all data and print results
		 
		 while (rs.next()) {
		  System.out.println(rs.getString(clomunName));
		  String value = rs.getString(clomunName);
		  return value;
		 }
		 //Closing DB Connection
		
		 con.close();
		return clomunName;
	}
	
	
}