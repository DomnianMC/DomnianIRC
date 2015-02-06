package com.willies952002.WSCore;

import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author William Surgeon (willies952002)
 * @since 1.0dev
 */
public class SQLHandler {

	String dbHost;
	String dbURL;
	String dbName;
	String dbUser;
	String dbPass;
	Connection conn;
	Statement stmnt;
	ResultSet result;
	
	public SQLHandler(String host, String database, String user, String pass) throws Exception {
		dbURL = "jdbc:mysql://" + host + "/" + database;
		dbHost = host;
		dbName = database;
		dbUser = user;
		dbPass = pass;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
	}
	
	public SQLHandler(Connection connection) {
		conn = connection;
	}
	
	public String getHost() {
		return dbHost;
	}
	
	public String getDBName() {
		return dbName;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public Statement getStatement() {
		return stmnt;
	}
	
	public ResultSet getResultSet() {
		return result;
	}
	
	public String getURLasString() {
		return dbURL;
	}
	
	public URL getURL() throws Exception {
		return new URL(getURLasString());
	}
	
	public URI getURLasURI() throws Exception {
		return getURL().toURI();
	}

	/** To get the returned values from the query, use SQLHandler.getResultSet() */
	public boolean query(String sql) throws Exception {
		stmnt = conn.createStatement();
		result = stmnt.executeQuery(sql);
		if ( result != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void close() throws Exception {
		if ( conn != null ) {
			conn.close();
		}
		if ( stmnt != null ) {
			stmnt.close();
		}
		if ( result != null ) {
			result.close();
		}
		conn = null;
		stmnt = null;
		result = null;
	}
	
	@SuppressWarnings("unused")
	private void createTables() {
		try {
			query("CREATE TABLE IF NOT EXISTS `playerBans` ( `banned` varchar(40) NOT NULL, `bannedUUID` varchar(16) NOT NULL, `banner` varchar(16) NOT NULL, `bannerUUID` varchar(40) NOT NULL, `reason` varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			query("CREATE TABLE IF NOT EXISTS `staffApp` ( `fname` varchar(50) NOT NULL, `lname` varchar(50) NOT NULL, `email` varchar(100) NOT NULL, `skype` varchar(50) NOT NULL, `mcuser` varchar(16) NOT NULL, `bday` varchar(10) NOT NULL, `pos` varchar(20) NOT NULL, `bio` varchar(5000) NOT NULL, `hobby` varchar(5000) NOT NULL, UNIQUE KEY `mcuser` (`mcuser`), UNIQUE KEY `mcuser_2` (`mcuser`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			query("CREATE TABLE IF NOT EXISTS `staffPanel` ( `status` varchar(10) NOT NULL, `uuid` varchar(36) NOT NULL, `username` varchar(16) NOT NULL, `passwd` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL, `rank` int(11) NOT NULL, UNIQUE KEY `uuid` (`uuid`), UNIQUE KEY `uuid_2` (`uuid`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
