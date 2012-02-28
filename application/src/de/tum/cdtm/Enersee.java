/**
 * Enersee.java
 * 
 * Yakira C. Bristol
 */
package de.tum.cdtm;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class Enersee extends javax.swing.JFrame {
	private JPanel SettingsPanel;
	private JLabel spLabel;
	private JComboBox portsComboBox;
	private JTextField newTableTextField;
	private JTextField vL3TextField;
	private JLabel cL1Label;
	private JLabel cL3Label;
	private JLabel cL2Label;
	private JLabel vL3Label;
	private JLabel vL2Label;
	private JLabel vL1Label;
	private JTextField vL2TextField;
	private JTextField vL1TextField;
	private JLabel pLabel;
	private JTextField cL1TextField;
	private JButton graphButton;
	private JLabel statusLabel;
	private JTextField newDBTextField;
	private JTextField databaseTextField;
	private JLabel databaseLabel;
	private JLabel newDBLabel;
	private JCheckBox remoteCheckBox;
	private JCheckBox localCheckBox;
	private JLabel jLabel1;
	private JLabel pL3Label;
	private JLabel pL1Label;
	private JTextField pL1TextField;
	private JTextField pL2TextField;
	private JTextField pL3TextField;
	private JTextField cL3TextField;
	private JTextField cL2TextField;
	private JLabel cLabel;
	private JLabel vLabel;
	private JTextField tableNameTextField;
	private JTextField singleEnTextField;
	private JLabel singleEntireLabel;
	private JLabel nameOfTable;
	private JLabel newTableLabel;
	private JButton closeButton;
	private JButton stopButton;
	private JButton startButton;
	private JPanel DataPanel;
	private JTextField username;
	private JTextField password;
	
	private SerialPortReader spr;
	private RemoteDao db;
	//private DatabaseInfo dbi;
	private LocalDao edao;
	private boolean local = false;
	private EnerseeDataGraph edg;
	Enersee eClass;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Enersee inst = new Enersee();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	/**
	 * 
	 */
	public Enersee() {
		super();
		initGUI();
		eClass = this;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GridLayout thisLayout = new GridLayout(2, 5);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			thisLayout.setColumns(5);
			thisLayout.setRows(2);
			getContentPane().setLayout(thisLayout);
			this.setTitle("Enersee");
			{
				SettingsPanel = new JPanel();
				getContentPane().add(getSettingsPanel());
				GridBagLayout MeterPanelLayout = new GridBagLayout();
				MeterPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				MeterPanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7};
				MeterPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				MeterPanelLayout.columnWidths = new int[] {212, 225, 12, 7};
				SettingsPanel.setLayout(MeterPanelLayout);
				SettingsPanel.setPreferredSize(new java.awt.Dimension(384, 140));
				{
					spLabel = new JLabel();
					SettingsPanel.add(spLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					spLabel.setText("Serial Ports:");
					spLabel.setPreferredSize(new java.awt.Dimension(96, 28));
				}
				{
					ComboBoxModel portsComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "Select a Port"});
					portsComboBox = new JComboBox();
					SettingsPanel.add(portsComboBox, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					portsComboBox.setModel(portsComboBoxModel);
					portsComboBox.setPreferredSize(new java.awt.Dimension(236, 28));
					portsComboBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String selected = (String) portsComboBox.getSelectedItem();
							if(selected == "Select a Port"){
								getStartButton().setEnabled(false);
								getStopButton().setEnabled(false);
							}else{
								getStartButton().setEnabled(true);
							}
						}
					});
					SerialPortLister pl = new SerialPortLister();
					ArrayList<String> portNames = pl.getPorts();
					for(int i = 0; i < portNames.size(); i++){
						String port = portNames.get(i);
						portsComboBox.addItem(port);
					}
				}
				{
					startButton = new JButton();
					SettingsPanel.add(getStartButton(), new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					startButton.setText("Start");
					startButton.setEnabled(false);
					startButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							getStopButton().setEnabled(true);
							graphButton.setEnabled(true);
							SerialPortReader pr = null;
							//db = new RemoteDao();
							if(localCheckBox.isSelected()){
								/*if(!dbi.getSnameTextField().getText().equals("") && !dbi.getUnameTextField().getText().equals("") && !dbi.getPassPasswordField().getPassword().equals("")){
									
									String stat = db.connectDB();
									//System.out.println(stat);
								}else {
									JOptionPane.showMessageDialog(eClass, "There is some information missing about your local database\n Please make sure all fields are filled in", "Enersee", JOptionPane.WARNING_MESSAGE);
									localCheckBox.setSelected(false);
								}*/
								//db = new RemoteDao("local");
								//db.setDatabaseName(databaseTextField.getText());
								//String stat = db.connectDB();
								edao = new LocalDao(databaseTextField.getText());
								boolean connected = edao.connect();
								if(connected == true){
									statusLabel.setText("You are now connected to the local database");
									local = true;
									pr = new SerialPortReader(edao, eClass, local);
								}else {
									statusLabel.setText("Something went wrong when connecting to the local database. Please check connections");
								}
								
							}else if(!localCheckBox.isSelected() && !remoteCheckBox.isSelected()){
								JOptionPane.showMessageDialog(eClass, "Please select the local or remote database option", "Enersee", JOptionPane.WARNING_MESSAGE);
							}else {
								db = new RemoteDao("remote");
								String cstat = db.connectDB();
								statusLabel.setText(cstat);
								pr = new SerialPortReader(db, eClass);
								//System.out.println(cstat);
							}
							//pr = new SerialPortReader(db, eClass);
							spr = pr;
							try {
								//String status = pr.connect((String)getPortsComboBox().getSelectedItem());
								//System.out.println(status + "\n");
								//String portData = pr.getData();
								//System.out.println(portData);
								if(newDBTextField.getText().equals("y")){
									if(!databaseTextField.getText().equals("")){
										if(!local){
											db.createNewDatabase(databaseTextField.getText());
										}
									}else {
										JOptionPane.showMessageDialog(eClass, "Please type in a name for your database", "Enersee", JOptionPane.WARNING_MESSAGE);
									}
								}else if(newDBTextField.getText().equals("n")){
									if(!databaseTextField.getText().equals("")){
										if(!local){
											pr.setDBName(databaseTextField.getText());
										}
									}else {
										JOptionPane.showMessageDialog(eClass, "Please type in a name for your database", "Enersee", JOptionPane.WARNING_MESSAGE);
									}
								}
								else{ 
									JOptionPane.showMessageDialog(eClass, "Please type y or n to create a new database and the name of the database to use in the " +
											"appropriate fields", "Enersee", JOptionPane.WARNING_MESSAGE);
								}
								if(newTableTextField.getText().equals("y")){
									if(!tableNameTextField.getText().equals("")){
										if(local == true){
											edao.createTable(tableNameTextField.getText());
										}else {
											db.createNewTable(tableNameTextField.getText());
										}
										
									}else {
										JOptionPane.showMessageDialog(eClass, "Please type in a name for your table", "Enersee", JOptionPane.WARNING_MESSAGE);
									}
								}else if(newTableTextField.getText().equals("n")){
									if(!tableNameTextField.getText().equals("")){
										if(local == true){
											edao.setTableName(tableNameTextField.getText());
										}else {
											pr.setDBTName(tableNameTextField.getText());
										}
									}else {
										JOptionPane.showMessageDialog(eClass, "Please type in a name for your table", "Enersee", JOptionPane.WARNING_MESSAGE);
									}
								}
								else{ 
									JOptionPane.showMessageDialog(eClass, "Please type y or n to create a new table and the name of the table to use in the " +
											"appropriate fields", "Enersee", JOptionPane.WARNING_MESSAGE);
								}
		
								
								if(!tableNameTextField.getText().equals("") && !databaseTextField.getText().equals("") && !newTableTextField.getText().equals("") && !newDBTextField.getText().equals("") && (localCheckBox.isSelected() || remoteCheckBox.isSelected())){
									/*if(local){ 
										pr = new SerialPortReader(edao, eClass, local);
									}else {
										pr = new SerialPortReader(db, eClass);
									}*/
									String status = pr.connect((String)getPortsComboBox().getSelectedItem());
									spr = pr;
									//System.out.println(status + "\n");
									//statusLabel.setText(status);
								}
								
								
							} catch (Exception e) {
								e.printStackTrace();
								//System.out.println("Problem with connecting to: " + (String)getPortsComboBox().getSelectedItem() + "\n");
								statusLabel.setText("Problem with connecting to: " + (String)getPortsComboBox().getSelectedItem());
							}
						}
					});
				}
				{
					stopButton = new JButton();
					SettingsPanel.add(getStopButton(), new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					stopButton.setText("Stop");
					stopButton.setEnabled(false);
					stopButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String closedStatus = spr.closePort();
							statusLabel.setText(closedStatus);
						}
					});
				}
				{
					closeButton = new JButton();
					SettingsPanel.add(closeButton, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					closeButton.setText("Close");
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.exit(EXIT_ON_CLOSE);
						}
					});
				}
				{
					newTableLabel = new JLabel();
					SettingsPanel.add(newTableLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					newTableLabel.setText("Create new table?");
				}
				{
					nameOfTable = new JLabel();
					SettingsPanel.add(nameOfTable, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					nameOfTable.setText("Type name of new or existing table:");
				}
				{
					singleEntireLabel = new JLabel();
					SettingsPanel.add(singleEntireLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					singleEntireLabel.setText("Measure single device or entire run?");
				}
				{
					newTableTextField = new JTextField();
					SettingsPanel.add(newTableTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					newTableTextField.setText("(y/n)");
					newTableTextField.setPreferredSize(new java.awt.Dimension(62, 23));
					newTableTextField.setSize(23, 23);
				}
				{
					tableNameTextField = new JTextField();
					SettingsPanel.add(tableNameTextField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					tableNameTextField.setPreferredSize(new java.awt.Dimension(62, 23));
					tableNameTextField.setSize(23, 23);
				}
				{
					singleEnTextField = new JTextField();
					SettingsPanel.add(singleEnTextField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					singleEnTextField.setText("(s/r)");
					singleEnTextField.setPreferredSize(new java.awt.Dimension(62, 23));
					singleEnTextField.setSize(23, 23);
				}
				{
					localCheckBox = new JCheckBox();
					SettingsPanel.add(localCheckBox, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					localCheckBox.setText("local database");
					localCheckBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("localCheckBox.actionPerformed, event="+evt);
							if(localCheckBox.isSelected()){
								remoteCheckBox.setEnabled(false);
								//graphButton.setEnabled(true);
								//dbi = new DatabaseInfo();
								//dbi.setLocationRelativeTo(eClass);
								//dbi.setVisible(true);
								local = true;
							}else {
								remoteCheckBox.setEnabled(true);
							}
						}
					});
				}
				{
					remoteCheckBox = new JCheckBox();
					SettingsPanel.add(remoteCheckBox, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					remoteCheckBox.setText("remote database");
					remoteCheckBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//System.out.println("remoteCheckBox.actionPerformed, event="+evt);
							if(remoteCheckBox.isSelected()){
								localCheckBox.setEnabled(false);
								local = false;
							}else {
								localCheckBox.setEnabled(true);
							}
						}
					});
				}
				{
					newDBLabel = new JLabel();
					SettingsPanel.add(newDBLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					newDBLabel.setText("Create new database?");
				}
				{
					databaseLabel = new JLabel();
					SettingsPanel.add(databaseLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					databaseLabel.setText("Type name of new or existing database:");
				}
				{
					databaseTextField = new JTextField();
					SettingsPanel.add(getDatabaseTextField(), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					newDBTextField = new JTextField();
					SettingsPanel.add(getNewDBTextField(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					newDBTextField.setText("(y/n)");
				}
				{
					graphButton = new JButton();
					SettingsPanel.add(graphButton, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					graphButton.setText("Graph Data");
					graphButton.setEnabled(false);
					graphButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if(local){
								edg = new EnerseeDataGraph(edao);
							}else{
								edg = new EnerseeDataGraph(db);
							}
							edg.setLocationRelativeTo(eClass);
							edg.setVisible(true);
						}
					});
				}
			}
			{
				DataPanel = new JPanel();
				getContentPane().add(getDataPanel());
				GridBagLayout DataPanelLayout = new GridBagLayout();
				DataPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				DataPanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7};
				DataPanelLayout.columnWeights = new double[] {0.0, 0.1, 0.0, 0.1, 0.0, 0.1};
				DataPanelLayout.columnWidths = new int[] {55, 7, 102, 7, 91, 7};
				DataPanel.setLayout(DataPanelLayout);
				{
					vLabel = new JLabel();
					DataPanel.add(vLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					vLabel.setText("Voltage:");
				}
				{
					cLabel = new JLabel();
					DataPanel.add(cLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					cLabel.setText("Current:");
				}
				{
					pLabel = new JLabel();
					DataPanel.add(getPLabel(), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					pLabel.setText("Power:");
				}
				{
					vL1TextField = new JTextField();
					DataPanel.add(getVL1TextField(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					vL2TextField = new JTextField();
					DataPanel.add(getVL2TextField(), new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					vL3TextField = new JTextField();
					DataPanel.add(getVL3TextField(), new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					vL1Label = new JLabel();
					DataPanel.add(getVL1Label(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					vL1Label.setText("L1:");
				}
				{
					vL2Label = new JLabel();
					DataPanel.add(getVL2Label(), new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					vL2Label.setText("L2:");
				}
				{
					vL3Label = new JLabel();
					DataPanel.add(getVL3Label(), new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					vL3Label.setText("L3:");
				}
				{
					cL1Label = new JLabel();
					DataPanel.add(getCL1Label(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					cL1Label.setText("L1:");
				}
				{
					cL2Label = new JLabel();
					DataPanel.add(getCL2Label(), new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					cL2Label.setText("L2:");
				}
				{
					cL3Label = new JLabel();
					DataPanel.add(getCL3Label(), new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					cL3Label.setText("L3:");
				}
				{
					cL1TextField = new JTextField();
					DataPanel.add(getCL1TextField(), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					cL2TextField = new JTextField();
					DataPanel.add(getCL2TextField(), new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					cL3TextField = new JTextField();
					DataPanel.add(getCL3TextField(), new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					pL3TextField = new JTextField();
					DataPanel.add(getPL3TextField(), new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					pL2TextField = new JTextField();
					DataPanel.add(getPL2TextField(), new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					pL1TextField = new JTextField();
					DataPanel.add(getPL1TextField(), new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					pL1Label = new JLabel();
					DataPanel.add(getPL1Label(), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					pL1Label.setText("L1:");
				}
				{
					pL3Label = new JLabel();
					DataPanel.add(getPL3Label(), new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					pL3Label.setText("L2:");
				}
				{
					jLabel1 = new JLabel();
					DataPanel.add(getJLabel1(), new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
					jLabel1.setText("L3:");
				}
				{
					statusLabel = new JLabel();
					DataPanel.add(statusLabel, new GridBagConstraints(0, 6, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
					statusLabel.setText("Status: ");
				}
			}
			pack();
			this.setSize(600, 450);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public JPanel getSettingsPanel() {
		return SettingsPanel;
	}
	
	/**
	 * @return
	 */
	public JPanel getDataPanel() {
		return DataPanel;
	}
	
	/**
	 * @return
	 */
	public JLabel getSpLabel() {
		return spLabel;
	}
	
	/**
	 * @return
	 */
	public JComboBox getPortsComboBox() {
		return portsComboBox;
	}
	
	/**
	 * @return
	 */
	public JButton getStartButton() {
		return startButton;
	}
	
	/**
	 * @return
	 */
	public JButton getStopButton() {
		return stopButton;
	}
	
	/**
	 * @return
	 */
	public JLabel getNewTableLabel() {
		return newTableLabel;
	}
	
	/**
	 * @return
	 */
	public JLabel getNameOfTable() {
		return nameOfTable;
	}
	
	/**
	 * @return
	 */
	public JLabel getSingleEntireLabel() {
		return singleEntireLabel;
	}
	
	/**
	 * @return
	 */
	public JTextField getNewTableTextField() {
		return newTableTextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getTableNameTextField() {
		return tableNameTextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getSingleEnTextField() {
		return singleEnTextField;
	}
	
	/**
	 * @return
	 */
	public JLabel getVLabel() {
		return vLabel;
	}
	
	/**
	 * @return
	 */
	public JLabel getCLabel() {
		return cLabel;
	}
	
	/**
	 * @return
	 */
	public JLabel getPLabel() {
		return pLabel;
	}
	
	/**
	 * @return
	 */
	public JTextField getVL1TextField() {
		return vL1TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getVL2TextField() {
		return vL2TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getVL3TextField() {
		return vL3TextField;
	}
	
	/**
	 * @return
	 */
	public JLabel getVL1Label() {
		return vL1Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getVL2Label() {
		return vL2Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getVL3Label() {
		return vL3Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getCL1Label() {
		return cL1Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getCL2Label() {
		return cL2Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getCL3Label() {
		return cL3Label;
	}
	
	/**
	 * @return
	 */
	public JTextField getCL1TextField() {
		return cL1TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getCL2TextField() {
		return cL2TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getCL3TextField() {
		return cL3TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getPL3TextField() {
		return pL3TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getPL2TextField() {
		return pL2TextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getPL1TextField() {
		return pL1TextField;
	}
	
	/**
	 * @return
	 */
	public JLabel getPL1Label() {
		return pL1Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getPL3Label() {
		return pL3Label;
	}
	
	/**
	 * @return
	 */
	public JLabel getJLabel1() {
		return jLabel1;
	}
	
	/**
	 * @return
	 */
	public JTextField getDatabaseTextField() {
		return databaseTextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getNewDBTextField() {
		return newDBTextField;
	}
	
	/**
	 * @return
	 */
	public JTextField getUsernameTextField(){
		return username;
	}
	
	/**
	 * @return
	 */
	public JTextField getPassTextField(){
		return password;
	}
	
	/**
	 * @return
	 */
	public JCheckBox getLocalCheckBox(){
		return localCheckBox;
	}
	
	/**
	 * @return
	 */
	public JCheckBox getRemoteCheckBox(){
		return remoteCheckBox;
	}
	
	/**
	 * @return
	 */
	public EnerseeDataGraph getDataGraph(){
		return edg;
	}

}
