package com.ibm.csi.npm.dvreportmodel.db;

import java.io.File;
import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import com.ibm.db2.jcc.DB2SimpleDataSource;

public class DbHandler {
	private static OracleConnectionPoolDataSource oracleDataSource = null;
	private static DB2SimpleDataSource db2DataSource = null;
	private static DataSource dataSource = null;
	private static String port = null;
	private static String host = null;
	private static String schema = null;
	private static String user = null;
	private static String passwd = null;
	private static String dbtype = null;
	
	
	public static Connection getConnection() {
		if (oracleDataSource == null && db2DataSource == null)
			dataSource = initDataSource();
		Connection con = null;
				
		if (oracleDataSource != null || db2DataSource != null) {
			try {
				if (dataSource != null) {
					con = dataSource.getConnection();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	private static DataSource initDataSource() {	
		try {
			oracleDataSource = new OracleConnectionPoolDataSource();
			DB2SimpleDataSource db2DataSource = null;
			
			File confFile = new File("conf/config.ini");
						
			Scanner scnr = new Scanner(confFile);
			
			while(scnr.hasNextLine()){
				 String line = scnr.nextLine();
		           if(line.contains("=")) {
		        	   String temp[]=line.split("=");
		        	   if (temp[0].equalsIgnoreCase("dbPort")) {
		        			try{port=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("dbName")) {
							try{schema=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("dbServerIP")) {
							try{host=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("dbUserName")) {
							try{user=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("dbUserPassword")) {
							try{passwd=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("dbProductName")) {
							try{dbtype=temp[1];}catch(Exception e){}
						}
		           }
			 	}
			
			dbtype = dbtype.toUpperCase();
			
			if(dbtype.contains("DB2")) {
				db2DataSource = new DB2SimpleDataSource();
				db2DataSource.setDatabaseName(schema);
				db2DataSource.setDescription("TNPM DB2 DataView Database");
				db2DataSource.setUser(user);
				db2DataSource.setPassword(passwd);
				db2DataSource.setServerName(host);
				db2DataSource.setPortNumber(Integer.parseInt(port));
				db2DataSource.setDriverType(4);
				System.out.println("========================");
				System.out.println("==Connected to IBM DB2==");
				System.out.println("========================");
				return (DataSource) db2DataSource;
			}else if (dbtype.contains("ORACLE")) {
				oracleDataSource.setURL("jdbc:oracle:thin:@" + host + ":" + port + ":"
						+ schema);
				oracleDataSource.setUser(user);
				oracleDataSource.setPassword(passwd);
				Properties prop = new java.util.Properties();
				prop.setProperty("useFetchSizeWithLongColumn ", "true");
				oracleDataSource.setConnectionProperties(prop);
				System.out.println("========================");
				System.out.println("=Connected to Oracle DB=");
				System.out.println("========================");
				return (DataSource) oracleDataSource;
				
			}
				
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if(dbtype.contains("DB2")) {
			return (DataSource) db2DataSource;
			}
		else if (dbtype.contains("ORACLE")) {
			return (DataSource) oracleDataSource;
			}
		else {
			return (DataSource) null;
			}

	}

}
