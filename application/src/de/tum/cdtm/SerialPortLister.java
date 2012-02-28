/**
 * SerialPortLister.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;
import java.util.ArrayList;

import gnu.io.*;


/**
 * @author Yakira C. Bristol
 *
 */
public class SerialPortLister { 
	
	/**
	 * 
	 */
	public static void listPorts(){
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		while(portEnum.hasMoreElements()){
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<String> getPorts(){
		ArrayList<String> serialPorts = new ArrayList<String>();
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		while(portEnum.hasMoreElements()){
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			if(portIdentifier.getPortType()== CommPortIdentifier.PORT_SERIAL){
				serialPorts.add(portIdentifier.getName());
			}
			//System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
		}
		return serialPorts;
	}
	
	static String getPortTypeName(int portType){
		switch(portType){
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL:
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485:
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL:
			return "Serial";
		default:
			return "unknown type";
		}
	}

}

