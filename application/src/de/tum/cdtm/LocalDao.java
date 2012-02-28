/**
 * LocalDao.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;




/**
 * @author Yakira C. Bristol
 *
 */
public class LocalDao {
	
	private Connection dbConnection;
	private Properties dbProperties;
	private boolean isConnected;
	private String dbName;
	private String schema = "Enersee";
	private String tableName = "newt";
	private PreparedStatement stmtInsertData;
	private String driverName;
	private String databaseUrl;
	
	/**
	 * 
	 */
	public LocalDao(){
		this("DefaultEDatabase");
	}
	
	/**
	 * @param databaseName
	 */
	public LocalDao(String databaseName){
		this.dbName = databaseName;
		
		setDBSystemDir();
		dbProperties = loadDBProperties();
        //driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        //databaseUrl = "jdbc:derby:" + this.dbName;
		driverName = dbProperties.getProperty("derby.driver");
        loadDatabaseDriver(driverName);
        if(!dbExists()) {
            boolean cd = createDatabase();
            System.out.println("is database created: " + cd);
        }
	}
	
	private boolean dbExists() {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }
	
	private void setDBSystemDir() {
        // decide on the db system directory
        String userHomeDir = System.getProperty("user.home");
        String systemDir = userHomeDir + "/enersee";
        System.setProperty("derby.system.home", systemDir);
        
        // create the db system directory
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }
    
    private void loadDatabaseDriver(String dName) {
        // load Derby driver
        try {
            Class.forName(dName);
            System.out.println("database driver is loaded");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
	
	private Properties loadDBProperties() {
        InputStream dbPropInputStream = null;
        dbPropInputStream = LocalDao.class.getResourceAsStream("Configuration.properties");
        dbProperties = new Properties();
        try {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dbProperties;
    }
	
	/**
	 * @param tname
	 * @return
	 */
	public boolean createTable(String tname){
		tableName = tname;
		boolean createdTable = false;
		Statement statement = null;
		
		try {
            statement = dbConnection.createStatement();
            statement.execute(createDataTable);
            createdTable = true;
            System.out.println(tableName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return createdTable;
	}
	
	
    private boolean createDatabase() {
        boolean bCreated = false;
        Connection dbConnection = null;
        
        //String dbUrl = databaseUrl + ";create=true";
        String dbUrl = getDatabaseUrl();
        dbProperties.put("create", "true");
        
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            //bCreated = createTables(dbConnection);
        } catch (SQLException ex) {
        }
        //dbProperties.remove("create");
        return bCreated;
    }
    
    
    /**
     * @return
     */
    public boolean connect() {
        String dbUrl = getDatabaseUrl();
        
        System.out.println("Trying to connect to the database");
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
          
            isConnected = dbConnection != null;
        } catch (SQLException ex) {
        	ex.printStackTrace();
            isConnected = false;
        }
        return isConnected;
    }
    
    private String getHomeDir() {
        return System.getProperty("user.home");
    }
    
    /**
     * 
     */
    public void disconnect() {
        if(isConnected) {
            //String dbUrl = databaseUrl + ";shutdown=true";
        	String dbUrl = getDatabaseUrl();
            dbProperties.put("shutdown", "true");
            try {
                //DriverManager.getConnection(dbUrl, dbProperties);
                //System.out.println("You are now disconncted from the database");
            	dbConnection.close();
            	System.out.println("You are now disconncted from the database");
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
            isConnected = false;
        }
    }
	
	/**
	 * @return
	 */
	public String getDatabaseLocation() {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        return dbLocation;
    }
	
	/**
	 * @return
	 */
	public String getDatabaseUrl(){
		String dbUrl = dbProperties.getProperty("derby.url") + dbName;
        return dbUrl;
		//return databaseUrl;
	}
	
	/**
	 * @param t
	 * @param d
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
	 */
	public void insertData(java.sql.Time t, java.sql.Date d, double vc, double bc, double vlo, double vlt, double vlth, 
			double ilo, double ilt, double ilth, double plo, double plt, double plth){
		//BigInteger bi = new BigInteger("234679");
		PreparedStatement stmtInsertData;
		//int t = 234679;
		//System.out.println("The table name you are trying insert data into is: " + tableName);
		try{
			stmtInsertData = dbConnection.prepareStatement(insertData);
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
			
			System.out.println("We put data in the database");
		}catch (SQLException e){
			System.out.println("Error executing the query");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void getData(){
		Statement queryStatement = null;
        ResultSet results = null;
        
        try{
			queryStatement = dbConnection.createStatement();
			results = queryStatement.executeQuery(getDataEntries);
			double vc = 0;
			while(results.next()){
				vc = results.getDouble("vor_stand");
			}
				System.out.println("Vor stand: " + vc);
			
		}catch (SQLException e){
			System.out.println("Error executing the query");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<Data> getDataEntries(){
    	Statement queryStatement = null;
        ResultSet results = null;
        ArrayList<Data> dataset = new ArrayList<Data>();
        System.out.println("The table name you are trying get data from is: " + tableName);
        try{
			queryStatement = dbConnection.createStatement();
			results = queryStatement.executeQuery(getDataEntries);
			while(results.next()){
				Data singleD = new Data();
				singleD.setT(results.getTime("t"));
				singleD.setD(results.getDate("d"));
				singleD.setVc(results.getDouble("vor_stand"));
				singleD.setBc(results.getDouble("rueck_stand"));
				singleD.setVone(results.getDouble("V_L1"));
				singleD.setVtwo(results.getDouble("V_L2"));
				singleD.setVthree(results.getDouble("V_L3"));
				singleD.setIone(results.getDouble("I_L1"));
				singleD.setItwo(results.getDouble("I_L2"));
				singleD.setIthree(results.getDouble("I_L3"));
				singleD.setPlone(results.getDouble("P_L1"));
				singleD.setPltwo(results.getDouble("P_L2"));
				singleD.setPlthree(results.getDouble("P_L3"));
				
				dataset.add(singleD);
			}
			
		}catch (SQLException e){
			System.out.println("Error executing the query");
			e.printStackTrace();
		}
		
		return dataset;
	}	
	
	/**
	 * @param name
	 */
	public void setTableName(String name){
    	tableName = name;
    	System.out.println("The table name you entered is: " + tableName);
    }
	
	
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDao db = new LocalDao("Camera");
        System.out.println(db.getDatabaseLocation());
        System.out.println(db.getDatabaseUrl());
        boolean c = db.connect();
        System.out.println("is database connected: " + c);
        //boolean tc = db.createTable("newt");
        //System.out.println("is table created: " + tc);
        db.setTableName("cameraData");
        //java.sql.Time nt = new java.sql.Time(new java.util.Date().getTime());
        //java.sql.Date nd = new java.sql.Date(new java.util.Date().getTime());
        //db.insertData(nt, nd, 700, 1.234, 55, 0, 0, 7, 0, 0, 25, 0, 0);
        //db.getData();
        ArrayList<Data> ds = db.getDataEntries();
        if(ds.isEmpty()){
        	System.out.println("It's empty");
        }
        else{
        	System.out.println("It's not empty");
        }
        
        for(int i = 0; i < ds.size(); i++){
        	Data d = ds.get(i);
        	//double vone = d.getVone();
        	//System.out.println("The voltage for d[" + i + "] is: " + vone);
        	java.sql.Time t = d.getT();
        	
        	System.out.println("The time for d[" + i + "] is: " + t.toString());
        }
        db.disconnect();
    }
    
    public String createDataTable = "CREATE TABLE " + tableName + 
	"(t TIME, d DATE, vor_stand DOUBLE, rueck_stand DOUBLE, " +
	"V_L1 DOUBLE, V_L2 DOUBLE, V_L3 DOUBLE, I_L1 DOUBLE, " +
	"I_L2 DOUBLE, I_L3 DOUBLE, P_L1 DOUBLE, P_L2 DOUBLE, " +
	"P_L3 DOUBLE)";

    public String insertData = "INSERT INTO "+ tableName + "(t, d, vor_stand, rueck_stand, V_L1, V_L2, V_L3, " +
	"I_L1, I_L2, I_L3, P_L1, P_L2, P_L3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public String getDataEntries = "SELECT t, d, vor_stand, rueck_stand, V_L1, V_L2, V_L3, " +
	"I_L1, I_L2, I_L3, P_L1, P_L2, P_L3 FROM " + tableName + "";

    
}
