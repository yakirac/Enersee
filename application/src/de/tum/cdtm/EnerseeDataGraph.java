/**
 * EnerseeDataGraph.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;



import java.util.ArrayList;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import net.sourceforge.chart2d.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * @author Yakira C. Bristol
 *
 */
public class EnerseeDataGraph extends javax.swing.JFrame {
	
	public RemoteDao db;
	public LocalDao edao;
	public String whatDatabase;
	private ArrayList<Data> dataS;
	private LBChart2D chart2D;
	private Dataset dataset;
	private javax.swing.JFrame thisFrame;

	/**
	* Auto-generated main method to display this JFrame
	*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EnerseeDataGraph inst = new EnerseeDataGraph();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	/**
	 * Default constructor
	 */
	/**
	 * 
	 */
	public EnerseeDataGraph() {
		super();
		thisFrame = this;
		initGUI();
	}
	
	/**
	 * Constructor for remote database
	 * @param d remote mysql database object
	 */
	/**
	 * @param d
	 */
	public EnerseeDataGraph(RemoteDao d){
		super();
		db = d;
		whatDatabase = "remote";
		thisFrame = this;
		initGUI();
	}
	
	/**
	 * Constructor for remote database
	 * @param ed local derby database object
	 */
	/**
	 * @param ed
	 */
	public EnerseeDataGraph(LocalDao ed){
		super();
		edao = ed;
		whatDatabase = "local";
		thisFrame = this;
		initGUI();
	}
	
	private void initGUI() {
		dataS = new ArrayList<Data>();

		try {
			
			if(whatDatabase.equals("remote")){
				dataS = db.getDataEntries();
			}else if(whatDatabase.equals("local")){
				dataS = edao.getDataEntries();
			}
			
			//<-- Begin Chart2D configuration -->

		    //Configure object properties
		    Object2DProperties object2DProps = new Object2DProperties();
		    object2DProps.setObjectTitleText ("Enersee Device Data Graph");

		    //Configure chart properties
		    Chart2DProperties chart2DProps = new Chart2DProperties();

		    //Configure legend properties
		    LegendProperties legendProps = new LegendProperties();
		    String[] legendLabels = {"Power"};
		    legendProps.setLegendLabelsTexts (legendLabels);

		    //Configure graph chart properties
		    GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
		    String[] labelsAxisLabels =
		      {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00",
		       "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", 
		       "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", 
		       "21:00", "22:00", "23:00"};
		    graphChart2DProps.setLabelsAxisLabelsTexts (labelsAxisLabels);
		    graphChart2DProps.setLabelsAxisTitleText ("Time");
		    graphChart2DProps.setNumbersAxisTitleText ("Power");
		    graphChart2DProps.setChartDatasetCustomizeGreatestValue (true);
		    graphChart2DProps.setChartDatasetCustomGreatestValue (1000);
		    graphChart2DProps.setLabelsAxisTicksAlignment (graphChart2DProps.CENTERED);
		    

		    //Configure graph properties
		    GraphProperties graphProps = new GraphProperties();
		    graphProps.setGraphBarsExistence (false);
		    graphProps.setGraphLinesExistence (true);
		    graphProps.setGraphOutlineComponentsExistence (true);
		    graphProps.setGraphAllowComponentAlignment (true);

		    //Configure dataset
		    dataset = new Dataset (1, 24, 1);
		    /*dataset.set (0,  0, 0, 100f);  //1990
		    dataset.set (0,  1, 0, 200f);  //1991
		    dataset.set (0,  2, 0, 300f);  //1992
		    dataset.set (0,  3, 0, 400f);  //1993
		    dataset.set (0,  4, 0, 100f);  //1994
		    dataset.set (0,  5, 0, 200f);  //1995
		    dataset.set (0,  6, 0, 100f);  //1996
		    dataset.set (0,  7, 0,   0f);  //1997
		    dataset.set (0,  8, 0, 100f);  //1998
		    dataset.set (0,  9, 0, 100f);  //1999
		    dataset.set (0, 10, 0, 200f);  //2000
		    dataset.set (0, 11, 0, 300f);  //2001
		    dataset.set (0,  12, 0, 100f);  //1990
		    dataset.set (0,  13, 0, 200f);  //1991
		    dataset.set (0,  14, 0, 300f);  //1992
		    dataset.set (0,  15, 0, 400f);  //1993
		    dataset.set (0,  16, 0, 100f);  //1994
		    dataset.set (0,  17, 0, 200f);  //1995
		    dataset.set (0,  18, 0, 100f);  //1996
		    dataset.set (0,  19, 0,   0f);  //1997
		    dataset.set (0,  20, 0, 100f);  //1998
		    dataset.set (0,  21, 0, 100f);  //1999
		    dataset.set (0, 22, 0, 200f);  //2000
		    dataset.set (0, 23, 0, 300f);  //2001
*/
		    for(int i = 0; i < dataS.size(); i++){
		    	Data d = dataS.get(i);
		    	java.sql.Time t = d.getT();
		    	int cate = checkTime(t);
		    	Double power = d.getPlone();
		    	float fpower = power.floatValue();
		    	dataset.set(0, cate, 0, fpower);
		    }
		    
		    //Configure graph component colors
		    MultiColorsProperties multiColorsProps = new MultiColorsProperties();

		    //Configure chart
		    chart2D = new LBChart2D();
		    chart2D.setObject2DProperties (object2DProps);
		    chart2D.setChart2DProperties (chart2DProps);
		    chart2D.setLegendProperties (legendProps);
		    chart2D.setGraphChart2DProperties (graphChart2DProps);
		    chart2D.addGraphProperties (graphProps);
		    chart2D.addDataset (dataset);
		    chart2D.addMultiColorsProperties (multiColorsProps);

		    //Optional validation:  Prints debug messages if invalid only.
		    if (!chart2D.validate (false)) chart2D.validate (true);


		    //<-- End Chart2D configuration -->
			this.getContentPane().add(chart2D);
			this.setTitle("Enersee");
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(600, 400);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	 * @param theTime
	 * @return
	 */
	public int checkTime(java.sql.Time theTime){
		int tnumber = 0;
		//String time = theTime.toString();
		if(theTime.compareTo(java.sql.Time.valueOf("00:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("01:00:00")) <= 0){
			tnumber = 0;
			//System.out.println("In 0-1");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("01:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("02:00:00")) <= 0){
			tnumber = 1;
			//System.out.println("In 1-2");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("02:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("03:00:00")) <= 0){
			tnumber = 2;
			//System.out.println("In 2-3");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("03:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("04:00:00")) <= 0){
			tnumber = 3;
			//System.out.println("In 3-4");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("04:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("05:00:00")) <= 0){
			tnumber = 4;
			//System.out.println("In 4-5");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("05:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("06:00:00")) <= 0){
			tnumber = 5;
			//System.out.println("In 5-6");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("06:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("07:00:00")) <= 0){
			tnumber = 6;
			//System.out.println("In 6-7");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("07:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("08:00:00")) <= 0){
			tnumber = 7;
			//System.out.println("In 7-8");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("08:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("09:00:00")) <= 0){
			tnumber = 8;
			//System.out.println("In 8-9");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("09:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("10:00:00")) <= 0){
			tnumber = 9;
			//System.out.println("In 9-10");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("10:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("11:00:00")) <= 0){
			tnumber = 10;
			//System.out.println("In 10-11");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("11:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("12:00:00")) <= 0){
			tnumber = 11;
			//System.out.println("In 11-12");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("12:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("13:00:00")) <= 0){
			tnumber = 12;
			//System.out.println("In 12-13");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("13:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("14:00:00")) <= 0){
			tnumber = 13;
			//System.out.println("In 13-14");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("14:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("15:00:00")) <= 0){
			tnumber = 14;
			//System.out.println("In 14-15");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("15:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("16:00:00")) <= 0){
			tnumber = 15;
			//System.out.println("In 15-16");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("16:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("17:00:00")) <= 0){
			tnumber = 16;
			//System.out.println("In 16-17");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("17:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("18:00:00")) <= 0){
			tnumber = 17;
			//System.out.println("In 17-18");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("18:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("19:00:00")) <= 0){
			tnumber = 18;
			//System.out.println("In 18-19");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("19:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("20:00:00")) <= 0){
			tnumber = 19;
			//System.out.println("In 19-20");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("20:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("21:00:00")) <= 0){
			tnumber = 20;
			//System.out.println("In 20-21");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("21:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("22:00:00")) <= 0){
			tnumber = 21;
			//System.out.println("In 21-22");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("22:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("23:00:00")) <= 0){
			tnumber = 22;
			//System.out.println("In 22-23");
		}
		if(theTime.compareTo(java.sql.Time.valueOf("23:00:00")) >= 0 && theTime.compareTo(java.sql.Time.valueOf("00:00:00")) <= 0){
			tnumber = 23;
			//System.out.println("In 23-0");
		}
		
		return tnumber;
	}
	
	/**
	 * 
	 */
	public void updateGraph(){
		chart2D.removeDataset(dataset);
		dataS.clear();
		dataset = new Dataset (1, 24, 1);
		if(whatDatabase.equals("remote")){
			dataS = db.getDataEntries();
		}else if(whatDatabase.equals("local")){
			dataS = edao.getDataEntries();
		}
		for(int i = 0; i < dataS.size(); i++){
	    	Data d = dataS.get(i);
	    	java.sql.Time t = d.getT();
	    	int cate = checkTime(t);
	    	Double power = d.getPlone();
	    	float fpower = power.floatValue();
	    	dataset.set(0, cate, 0, fpower);
	    }
		chart2D.addDataset(dataset);
		
		thisFrame.validate();
		thisFrame.repaint();
	}

}
