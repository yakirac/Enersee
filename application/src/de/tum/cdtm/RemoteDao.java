/**
 * RemoteDao.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Yakira C. Bristol
 *
 */
public class RemoteDao {

    String driverName = "com.mysql.jdbc.Driver";
    // Class.forName(driverName);
    String lrDB = "remote";
    String serverName = "129.187.150.139";
    String db = "Haushalt_B";
    String url = "jdbc:mysql://" + serverName + "/" + db;
    String username = "funkymonkey1193";
    String password = "wakaza10foot";
    String cStatus;
    String table;
    Connection connection;
    Statement stmt;

    /**
     * @param lr
     */
    public RemoteDao(String lr) {
	lrDB = lr;
	try {
	    /*
	     * if(lrDB.equals("local")){ driverName =
	     * "org.apache.derby.jdbc.EmbeddedDriver"; }
	     */
	    Class.forName(driverName);
	} catch (ClassNotFoundException e) {
	    cStatus = "Could not find the class: " + driverName;
	    System.out.println(cStatus);
	    e.printStackTrace();
	}
	// connectDB();
    }

    /**
     * @return
     */
    public String connectDB() {
	/*
	 * if(lrDB.equals("local")){ url = "jdbc:derby:" + db + ";create=true";
	 * try { connection = DriverManager.getConnection(url); cStatus =
	 * "You are now connected to the database"; } catch (SQLException e) {
	 * cStatus = "Error connecting to the database"; e.printStackTrace(); }
	 * }else{
	 */
	try {
	    connection = DriverManager.getConnection(url, username, password);
	    cStatus = "You are now connected to the database";
	} catch (SQLException e) {
	    cStatus = "Error connecting to the database";
	    e.printStackTrace();
	}
	// }
	System.out.println(cStatus);
	return cStatus;
    }

    /**
     * @param t
     * @param vc
     * @param bc
     * @param vlo
     * @param vlt
     * @param vlth
     * @param ilo
     * @param ilt
     * @param ilth
     * @param plo
     * @param plt
     * @param plth
     * @return
     */
    public String insertData(java.sql.Time t, java.sql.Date d, double vc, double bc, double vlo,
	    double vlt, double vlth, double ilo, double ilt, double ilth,
	    double plo, double plt, double plth) {
	BigInteger bi = new BigInteger("234679");
	PreparedStatement stmtInsertData;
	String insertData = "INSERT INTO "+ db + "." + table + "(t, d, vor_stand, rueck_stand, V_L1, V_L2, V_L3, " +
	"I_L1, I_L2, I_L3, P_L1, P_L2, P_L3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// int t = 234679;
	try{
		stmtInsertData = connection.prepareStatement(insertData);
		stmtInsertData.clearParameters();
		//java.sql.Time t = new java.sql.Time(new java.util.Date().getTime());
		stmtInsertData.setTime(1, t);
		stmtInsertData.setDate(2, d);
		stmtInsertData.setDouble(3, vc);
		stmtInsertData.setDouble(4, bc);
		stmtInsertData.setDouble(5, vlo);
		stmtInsertData.setDouble(6, vlt);
		stmtInsertData.setDouble(7, vlth);
		stmtInsertData.setDouble(8, ilo);
		stmtInsertData.setDouble(9, ilt);
		stmtInsertData.setDouble(10, ilth);
		stmtInsertData.setDouble(11, plo);
		stmtInsertData.setDouble(12, plt);
		stmtInsertData.setDouble(13, plth);
		
		stmtInsertData.executeUpdate();

	    cStatus = "We put data in the database";
	} catch (SQLException e) {
	    cStatus = "Error executing the query";
	    e.printStackTrace();
	} finally {
	    if (stmt != null) {
		try {
		    stmt.close();
		} catch (SQLException e) {
		    cStatus = "Error closing the statement";
		    e.printStackTrace();
		}
	    }
	}
	System.out.println(cStatus);
	return cStatus;
    }

    /**
     * @return
     */
    public ArrayList<Data> getDataEntries() {
	ArrayList<Data> dataset = new ArrayList<Data>();
	String query = "SELECT t, d, vor_stand, rueck_stand, V_L1, V_L2, V_L3, "
		+ "I_L1, I_L2, I_L3, P_L1, P_L2, P_L3 from " + db + "." + table;
	try {
	    stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
		Data singleD = new Data();
		singleD.setT(rs.getTime("t"));
		singleD.setD(rs.getDate("d"));
		singleD.setVc(rs.getDouble("vor_stand"));
		singleD.setBc(rs.getDouble("rueck_stand"));
		singleD.setVone(rs.getDouble("V_L1"));
		singleD.setVtwo(rs.getDouble("V_L2"));
		singleD.setVthree(rs.getDouble("V_L3"));
		singleD.setIone(rs.getDouble("I_L1"));
		singleD.setItwo(rs.getDouble("I_L2"));
		singleD.setIthree(rs.getDouble("I_L3"));
		singleD.setPlone(rs.getDouble("P_L1"));
		singleD.setPltwo(rs.getDouble("P_L2"));
		singleD.setPlthree(rs.getDouble("P_L3"));

		dataset.add(singleD);

	    }
	    cStatus = "We have data from the database";
	} catch (SQLException e) {
	    cStatus = "Error executing the query";
	    e.printStackTrace();
	} finally {
	    if (stmt != null) {
		try {
		    stmt.close();
		} catch (SQLException e) {
		    cStatus = "Error closing the statement";
		    e.printStackTrace();
		}
	    }
	}
	System.out.println(cStatus);
	return dataset;
    }

    /**
     * @param tableName
     */
    public void createNewTable(String tableName) {
	table = tableName;
	String createTable = "CREATE TABLE "
		+ db
		+ "."
		+ tableName
		+ " (t TIME, d DATE, vor_stand DOUBLE NULL, rueck_stand DOUBLE NULL, "
		+ "V_L1 DOUBLE NULL, V_L2 DOUBLE NULL, V_L3 DOUBLE NULL, I_L1 DOUBLE NULL, "
		+ "I_L2 DOUBLE NULL, I_L3 DOUBLE NULL, P_L1 DOUBLE NULL, P_L2 DOUBLE NULL, "
		+ "P_L3 DOUBLE NULL)";

	try {
	    stmt = connection.createStatement();
	    stmt.executeUpdate(createTable);
	} catch (SQLException e) {
	    cStatus = "Could not create the table";
	    e.printStackTrace();
	} finally {
	    if (stmt != null) {
		try {
		    stmt.close();
		} catch (SQLException e) {
		    cStatus = "Error closing the statement";
		    e.printStackTrace();
		}
	    }
	}
	System.out.println(cStatus);
    }

    /**
     * @param dname
     */
    public void createNewDatabase(String dname) {
	db = dname;
	String createDB = "CREATE DATABASE " + db;

	try {
	    stmt = connection.createStatement();
	    stmt.executeUpdate(createDB);
	} catch (SQLException e) {
	    cStatus = "Could not create the database";
	    e.printStackTrace();
	} finally {
	    if (stmt != null) {
		try {
		    stmt.close();
		} catch (SQLException e) {
		    cStatus = "Error closing the statement";
		    e.printStackTrace();
		}
	    }
	}
	System.out.println(cStatus);

    }

    /**
     * @param tname
     */
    public void setTableName(String tname) {
	table = tname;
    }

    /**
     * @return
     */
    public String getTableName() {
	return table;
    }

    /**
     * @param dbname
     */
    public void setDatabaseName(String dbname) {
	db = dbname;
    }

    /**
     * @param sname
     */
    public void setServerName(String sname) {
	serverName = sname;
    }

    /**
     * @param user
     */
    public void setUsername(String user) {
	username = user;
    }

    /**
     * @param pass
     */
    public void setPassword(String pass) {
	password = pass;
    }

    /**
     * @param db
     */
    public void setlrDB(String db) {
	lrDB = db;
    }
}
