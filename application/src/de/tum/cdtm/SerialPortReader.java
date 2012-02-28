/**
 * SerialPortReader.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;
import gnu.io.*;

import java.io.*;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.TooManyListenersException;


/**
 * @author Yakira C. Bristol
 *
 */
public class SerialPortReader implements SerialPortEventListener{
	CommPort commPort;
	SerialPort serialPort;
	InputStream in;
	String data = "";
	RemoteDao db;
	Enersee ense;
	LocalDao edao;
	//BigInteger t;
	//java.sql.Time t;
	double vc;
	double bc;
	double plone;
	double pltwo;
	double plthree;
	double vone;
	double vtwo;
	double vthree;
	double ione;
	double itwo;
	double ithree;
	int c = 0;
	boolean putData = false;
	boolean islocal = false;
	
	/**
	 * @param datab
	 * @param nrc
	 */
	public SerialPortReader(RemoteDao datab, Enersee nrc){
		super();
		db = datab;
		ense = nrc;
	}
	
	/**
	 * @param dao
	 * @param nrc
	 * @param local
	 */
	public SerialPortReader(LocalDao dao, Enersee nrc, boolean local){
		super();
		edao = dao;
		ense = nrc;
		islocal = local;
	}
	
	/**
	 * @param portName
	 * @return
	 */
	public String connect(String portName){
		String status = "";
		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		} catch (NoSuchPortException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(portIdentifier.isCurrentlyOwned()){
			status = "Error: Port is currently in use. Please check that you are connecting to the correct port.";
		}else {
			try{
				commPort = portIdentifier.open(this.getClass().getName(), 0);
				serialPort = (SerialPort) commPort;
				in = serialPort.getInputStream();
				
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_7, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
				serialPort.setDTR(true);
					
				status = "The port is now open and waiting for data";
			}catch (PortInUseException piu){
				String owner = piu.currentOwner;
				status = "The port is in use by: " + owner + ". Please check that you are connecting to the correct port.";
			} catch (UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TooManyListenersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return status;
	}
	
	public void serialEvent(SerialPortEvent event){
		switch (event.getEventType()) {
	    case SerialPortEvent.BI:
	      System.out.println("SerialPortEvent.BI occurred");
	    case SerialPortEvent.OE:
	      System.out.println("SerialPortEvent.OE occurred");
	    case SerialPortEvent.FE:
	      System.out.println("SerialPortEvent.FE occurred");
	    case SerialPortEvent.PE:
	      System.out.println("SerialPortEvent.PE occurred");
	    case SerialPortEvent.CD:
	      System.out.println("SerialPortEvent.CD occurred");
	    case SerialPortEvent.CTS:
	      System.out.println("SerialPortEvent.CTS occurred");
	    case SerialPortEvent.DSR:
	      System.out.println("SerialPortEvent.DSR occurred");
	    case SerialPortEvent.RI:
	      System.out.println("SerialPortEvent.RI occurred");
	    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	      System.out.println("SerialPortEvent.OUTPUT_BUFFER_EMPTY occurred");
	      break;
	    case SerialPortEvent.DATA_AVAILABLE:
	      //System.out.println("SerialPortEvent.DATA_AVAILABLE occurred");
	      byte[] readBuffer = new byte[20];
	      char[] charbuf = new char[100];
	      int numBytes = 0;
	      //int c = 0;
	      int d = 0;
	      //BufferedReader bread = null;
	      //InputStreamReader isr = null;
	      DataInputStream dis = null;
	      String k = "";
	      char sep = '(';
	      try {
	    	  //bread = new BufferedReader(new InputStreamReader(in));
	    	  dis = new DataInputStream(in);
	    	  DataInput di = (DataInput)dis;
	    	  //isr = new InputStreamReader(in, "UTF8");
	    	  //numBytes = in.read(readBuffer);
	    	  //data = new String(readBuffer, 0, numBytes, "UTF8");
	    	  data = di.readLine();
	    	  //data = bread.readLine();
	    	  //System.out.println(data);
	    	  //System.out.println("End of data");
	    	  //System.out.println(n);
	    	  //System.out.println("End of string");
	    	  
	    	  if(data == null){
	    		  System.out.println("EOF on serial port");
	    		  System.exit(0);
	    	  }
	    	  //c = data.indexOf("1-0:1.8.1*255(");
	    	  //d = data.indexOf("1-0:1.8.0*255(");
	    	  if(data.contains("1-0:1.8.1*255") || data.contains("1-0:1.8.0*255")){
	    		  System.out.println("Forward Counter: " + data);
	    		  String fkwh = data.substring(15, data.indexOf(")"));
	    		  vc = Double.parseDouble(fkwh);
	    		  //System.out.println("fkWh: " + fkwh);
	    		  //System.out.println("fkWh: I got this");
	    	  }
	    	  if(data.contains("1-0:2.8.1*255") || data.contains("1-0:2.8.0*255")){
	    		  System.out.println("Backward Counter: " + data);
	    		  String bkwh = data.substring(15, data.indexOf(")"));
	    		  bc = Double.parseDouble(bkwh);
	    		  //System.out.println("bkWh: " + bkwh);
	    		  //System.out.println("bkWh: I got this");
	    	  }
	    	  setVoltage(data);
	    	  setCurrent(data);
	    	  setPower(data);
	    	  
	    	  if(putData == true){
	    		  //setTime();
	    		  if(islocal){
	    		      	  java.sql.Time t = new java.sql.Time(new java.util.Date().getTime());
	    			  java.sql.Date nd = new java.sql.Date(new java.util.Date().getTime());
	    			  edao.insertData(t, nd, vc, bc, vone, vtwo, vthree, ione, itwo, ithree, plone, pltwo, plthree);
	    			  putData = false;
	    			  if(ense.getDataGraph() != null){
	    				  ense.getDataGraph().updateGraph();
	    			  }
	    		  }else {
	    		      	  java.sql.Time t = new java.sql.Time(new java.util.Date().getTime());
	    		      	  java.sql.Date nd = new java.sql.Date(new java.util.Date().getTime());
	    			  String stat = db.insertData(t, nd, vc, bc, vone, vtwo, vthree, ione, itwo, ithree, plone, pltwo, plthree);
	    			  putData = false;
	    		  }
	    		  //pplone = plone;
	    	  }
	    	  
	        //while (in.available() > 0) {
	          //isr = new InputStreamReader(in, "UTF8");
	          //bread = new BufferedReader(new InputStreamReader(in, "UTF8"));
	          //numBytes = in.read(readBuffer);
	          //c = isr.read(charbuf, 0, charbuf.length);
	          //data = bread.readLine();
	          //System.out.println(numBytes);
	        //}
	        //data = new String(readBuffer, 0, numBytes, "UTF8");
	        //System.out.print(data);
	       // data = new String(charbuf, 0, c);
	        //if(data.matches("1-0:1.8.0*255")|| data.matches("1-0:1.8.0*255")){
	        //	k = data.substring(data.indexOf("1-0:1.8.0*255"), data.indexOf("*k"));
	        //	System.out.print("Substring: " + k + "\n");
	        //}
	        //System.out.print(c);
	        //System.out.println("Number of characters: " + c);
	        //System.out.print(data);
	        //System.out.print("\nSubstring: " + k + "\n");
	      } catch (IOException ioe) {
	        System.out.println("Exception " + ioe);
	      }
	      break;
	      
	    }
	}
	
	/**
	 * @return
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * @return
	 */
	public String closePort(){
		String closed;
		commPort.close();
		closed = "The port is now closed";
		//System.out.println("Port is now closed");
		return closed;
	}
	
	/**
	 * @param name
	 */
	public void setDBTName(String name){
		if(!name.equals("")){
			db.setTableName(name);
		}
	}
	
	/**
	 * @param dname
	 */
	public void setDBName(String dname){
		if(!dname.equals("")){
			db.setDatabaseName(dname);
		}
	}
	
	private void setVoltage(String data){
		if(data.contains("1-0:32.7.0*255")){
  		  System.out.println("L1: " + data);
  		  //System.out.println("L1: I got this");
  		  String oneV = data.substring(15, data.lastIndexOf("*"));
  		  vone = Double.parseDouble(oneV);
  		  //System.out.println("L1W: " + oneW);
  		  ense.getVL1TextField().setText(oneV);
  	  }
  	  if(data.contains("1-0:52.7.0*255")){
  		  System.out.println("L2: " + data);
  		  //System.out.println("L2: I got this");
  		  String twoV = data.substring(15, data.lastIndexOf("*"));
  		  vtwo = Double.parseDouble(twoV);
  		  //System.out.println("L2W: " + twoW);
  		  ense.getVL2TextField().setText(twoV);
  	  }
  	  if(data.contains("1-0:72.7.0*255")){
  		  System.out.println("L3: " + data);
  		  //System.out.println("L3: I got this");
  		  String threeV = data.substring(15, data.lastIndexOf("*"));
  		  vthree = Double.parseDouble(threeV);
  		  //System.out.println("L3W: " + threeW);
  		  ense.getVL3TextField().setText(threeV);
  	  }
	}
	
	private void setCurrent(String data){
		if(data.contains("1-0:31.7.0*255")){
  		  System.out.println("L1: " + data);
  		  //System.out.println("L1: I got this");
  		  String oneA = data.substring(15, data.lastIndexOf("*"));
  		  ione = Double.parseDouble(oneA);
  		  //System.out.println("L1W: " + oneW);
  		  ense.getCL1TextField().setText(oneA);
  	  }
  	  if(data.contains("1-0:51.7.0*255")){
  		  System.out.println("L2: " + data);
  		  //System.out.println("L2: I got this");
  		  String twoA = data.substring(15, data.lastIndexOf("*"));
  		  itwo = Double.parseDouble(twoA);
  		  //System.out.println("L2W: " + twoW);
  		  ense.getCL2TextField().setText(twoA);
  	  }
  	  if(data.contains("1-0:71.7.0*255")){
  		  System.out.println("L3: " + data);
  		  //System.out.println("L3: I got this");
  		  String threeA = data.substring(15, data.lastIndexOf("*"));
  		  ithree = Double.parseDouble(threeA);
  		  //System.out.println("L3W: " + threeW);
  		  ense.getCL3TextField().setText(threeA);
  	  }
	}
	
	private void setPower(String data){
		if(data.contains("1-0:21.7.0*255") || data.contains("1-0:21.7.255*255")){
  		  System.out.println("L1: " + data);
  		  //System.out.println("L1: I got this");
  		  String oneW = data.substring(16, data.lastIndexOf("*"));
  		  plone = Double.parseDouble(oneW);
  		  //System.out.println("L1W: " + oneW);
  		  ense.getPL1TextField().setText(oneW);
  	  }
  	  if(data.contains("1-0:41.7.0*255") || data.contains("1-0:41.7.255*255")){
  		  System.out.println("L2: " + data);
  		  //System.out.println("L2: I got this");
  		  String twoW = data.substring(16, data.lastIndexOf("*"));
  		  pltwo = Double.parseDouble(twoW);
  		  //System.out.println("L2W: " + twoW);
  		  ense.getPL2TextField().setText(twoW);
  	  }
  	  if(data.contains("1-0:61.7.0*255") || data.contains("1-0:61.7.255*255")){
  		  System.out.println("L3: " + data);
  		  //System.out.println("L3: I got this");
  		  String threeW = data.substring(16, data.lastIndexOf("*"));
  		  plthree = Double.parseDouble(threeW);
  		  //System.out.println("L3W: " + threeW);
  		  ense.getPL3TextField().setText(threeW);
  		  putData = true;
  	  }
	}
	private void setTime(){
		//Calendar ttime = Calendar.getInstance();
		//int h = ttime.HOUR;
		//int m = ttime.MINUTE;
		//int s = ttime.SECOND;
		//int ms = ttime.MILLISECOND;
		//int time = (h*(360000000*10))+(m*60000000)+(s*1000000)+(ms*1000);
		//long time = System.currentTimeMillis();
		//t = BigInteger.valueOf(time); 
		//t = new java.sql.Time(new java.util.Date().getTime());
	}

}
