package org.nit.view;
import org.nit.view.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.nit.instance.DatabaseConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.ListModel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class mainFrame extends JFrame {

	private static Connection con;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField patientdobField;
	private JTextField patientnameField;
	private JTextField patientSSNField;
	private JTextField patientNameField;
	private JTextField patientaddress_Field;
	private JTextField patientdobTxtField;
	private JTextField phySSN_Field;
	private JTextField doctorSSNField;
	private JTextField doctornameField;
	private JTextField doctorspecialityField;
	private JTextField doctoryrsField;
	private JTextField preidField;
	private JTextField prepatientSSNField;
	private JTextField predateField;
	private JTextField prephySSNField;
	private JTextField prestatusField;
	private JTextField prequantityField;
	private JTextField preTradeNameField;
	private JTextField prePharCoNameField;
	private JTable prescriptionTable;
	private JTextField contractPharIDField;
	private JTextField contractSupervisorField;
	private JTextField contractPharmaCoField;
	private JTextField contractStartDateField;
	private JTextField contractEndDateField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
					DatabaseConnection dcb = DatabaseConnection.getDatabaseConnection();
					con = dcb.getConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 1428);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Patients Name");
		label.setBounds(26, 62, 82, 16);
		panel_2.add(label);
		
		patientnameField = new JTextField();
		patientnameField.setBounds(113, 59, 116, 22);
		patientnameField.setColumns(10);
		panel_2.add(patientnameField);
		
		JLabel lblNewLabel = new JLabel("Patients DOB");
		lblNewLabel.setBounds(26, 84, 82, 16);
		panel_2.add(lblNewLabel);
		
	
	
		
		patientdobField = new JTextField();
		patientdobField.setBounds(113, 81, 116, 22);
		panel_2.add(patientdobField);
		patientdobField.setColumns(10);
		panel_2.add(patientdobField);
		
		
		JLabel lblSearchPatients = new JLabel("SEARCH PATIENT");
		lblSearchPatients.setBounds(27, 34, 124, 16);
		panel_2.add(lblSearchPatients);
		
		JLabel lblInsertPatients = new JLabel("INSERT PATIENTS");
		lblInsertPatients.setBounds(26, 158, 116, 16);
		panel_2.add(lblInsertPatients);
		
		DefaultListModel listModel = new DefaultListModel();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 58, 559, 62);
		panel_2.add(scrollPane);
		
		JList patient_list = new JList(listModel);
		scrollPane.setViewportView(patient_list);
	
		
		JButton showPatientsBtn = new JButton("List All Patients");
		showPatientsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.removeAllElements();
				try {
					int rowIndex = 0;
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery("SELECT * FROM pri_phy_patient LEFT JOIN prescription ON pri_phy_patient.ssn=prescription.ssn");
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					String data = "Patient Info : ";
					
					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel.addElement(data);          
						data = "Patient Info : ";   
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});

		
		showPatientsBtn.setBounds(315, 130, 163, 25);
		panel_2.add(showPatientsBtn);
		
		JButton btnSearchPatients = new JButton("Search Patients");
		btnSearchPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rowIndex = 0;
					String patient_name = patientnameField.getText();
					String patient_DOB = patientdobField.getText();
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery("SELECT * FROM pri_phy_patient LEFT JOIN prescription ON pri_phy_patient.ssn=prescription.ssn WHERE name=\""+patient_name+"\" AND birth_date=\""+patient_DOB+"\"");
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					String data = "Patient Info : ";
					
					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel.addElement(data);          
						data = "Patient Info : ";   
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnSearchPatients.setBounds(113, 130, 121, 25);
		panel_2.add(btnSearchPatients);
		
		patientSSNField = new JTextField();
		patientSSNField.setBounds(113, 193, 116, 22);
		panel_2.add(patientSSNField);
		patientSSNField.setColumns(10);
		
		patientNameField = new JTextField();
		patientNameField.setBounds(113, 218, 116, 22);
		panel_2.add(patientNameField);
		patientNameField.setColumns(10);
		
		JLabel lblPatientName = new JLabel("Patient SSN");
		lblPatientName.setBounds(26, 196, 82, 16);
		panel_2.add(lblPatientName);
		
		JLabel label_1 = new JLabel("Patient Name");
		label_1.setBounds(26, 221, 82, 16);
		panel_2.add(label_1);
		
		patientaddress_Field = new JTextField();
		patientaddress_Field.setColumns(10);
		patientaddress_Field.setBounds(363, 218, 116, 22);
		panel_2.add(patientaddress_Field);
		
		patientdobTxtField = new JTextField();
		patientdobTxtField.setColumns(10);
		patientdobTxtField.setBounds(363, 193, 116, 22);
		panel_2.add(patientdobTxtField);
		
		JLabel lblPatientBirthday = new JLabel("Patient Birthday");
		lblPatientBirthday.setBounds(259, 196, 99, 16);
		panel_2.add(lblPatientBirthday);
		
		JLabel lblPatientAddress = new JLabel("Patient Address");
		lblPatientAddress.setBounds(259, 221, 99, 16);
		panel_2.add(lblPatientAddress);
		
		phySSN_Field = new JTextField();
		phySSN_Field.setColumns(10);
		phySSN_Field.setBounds(629, 193, 116, 22);
		panel_2.add(phySSN_Field);

		
		JLabel lblPhysicianSsn = new JLabel("Physician SSN");
		lblPhysicianSsn.setBounds(528, 196, 89, 16);
		panel_2.add(lblPhysicianSsn);
		
		JLabel lblIfApplicable = new JLabel("If Available");
		lblIfApplicable.setBounds(535, 209, 82, 16);
		panel_2.add(lblIfApplicable);
		
		JButton btnCreatePatient = new JButton("Create Patient");
		btnCreatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int rowIndex = 0;
					String patient_name = patientNameField.getText();
					String patient_DOB = patientdobTxtField.getText();
					String patient_address = patientaddress_Field.getText();
					String patient_SSN = patientSSNField.getText();
					String physician_SSN = phySSN_Field.getText();	
			
					
					Statement smt = con.createStatement();
					smt.execute("INSERT INTO pri_phy_patient(ssn,name,birth_date,address,phy_ssn) VALUES(\""+patient_SSN+"\",\""+patient_name+"\",\""+patient_DOB+"\",\""+patient_address+"\",\""+physician_SSN+"\")");
					String data = "Patient Info Created : ";
					JOptionPane.showMessageDialog(null,data);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		
		btnCreatePatient.setBounds(528, 239, 129, 25);
		panel_2.add(btnCreatePatient);
		
		JLabel lblInsertDoctor = new JLabel("INSERT DOCTOR");
		lblInsertDoctor.setBounds(26, 282, 116, 16);
		panel_2.add(lblInsertDoctor);
		
		JLabel lblDoctorSsn = new JLabel("Doctor SSN");
		lblDoctorSsn.setBounds(26, 320, 82, 16);
		panel_2.add(lblDoctorSsn);
		
		doctorSSNField = new JTextField();
		doctorSSNField.setColumns(10);
		doctorSSNField.setBounds(113, 317, 116, 22);
		panel_2.add(doctorSSNField);
		
		JLabel lblDoctorName = new JLabel("Doctor Name");
		lblDoctorName.setBounds(26, 345, 82, 16);
		panel_2.add(lblDoctorName);
		
		doctornameField = new JTextField();
		doctornameField.setColumns(10);
		doctornameField.setBounds(113, 342, 116, 22);
		panel_2.add(doctornameField);
		
		JLabel lblSpeciality = new JLabel("Speciality");
		lblSpeciality.setBounds(259, 320, 99, 16);
		panel_2.add(lblSpeciality);
		
		doctorspecialityField = new JTextField();
		doctorspecialityField.setColumns(10);
		doctorspecialityField.setBounds(363, 317, 116, 22);
		panel_2.add(doctorspecialityField);
		
		JLabel lblYearsOfExperience = new JLabel("Years of Experience");
		lblYearsOfExperience.setBounds(241, 345, 117, 16);
		panel_2.add(lblYearsOfExperience);
		
		doctoryrsField = new JTextField();
		doctoryrsField.setColumns(10);
		doctoryrsField.setBounds(363, 342, 116, 22);
		panel_2.add(doctoryrsField);
		
		JButton btnCreateDoctor = new JButton("Create Doctor");
		btnCreateDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rowIndex = 0;
					String doctor_name = doctornameField.getText();
					int yearsofexperience = Integer.parseInt(doctoryrsField.getText());
					String doctor_SSN = doctorSSNField.getText();
					String specialty = doctorspecialityField.getText();
					

					Statement smt = con.createStatement();
					smt.execute("INSERT INTO doctor(ssn,name,specialty,yearsOfExperience) VALUES(\""+doctor_SSN+"\",\""+doctor_name+"\",\""+specialty+"\",\""+yearsofexperience+"\")");
					String data = "Patient Info Created : ";
					JOptionPane.showMessageDialog(null,data);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			
			}
		});
		btnCreateDoctor.setBounds(528, 341, 129, 25);
		panel_2.add(btnCreateDoctor);
		
		JLabel lblInsertPrescription = new JLabel("INSERT PRESCRIPTION");
		lblInsertPrescription.setBounds(26, 386, 139, 16);
		panel_2.add(lblInsertPrescription);
		
		JLabel lblPrescriptionId = new JLabel("Prescription ID");
		lblPrescriptionId.setBounds(12, 424, 96, 16);
		panel_2.add(lblPrescriptionId);
		
		preidField = new JTextField();
		preidField.setColumns(10);
		preidField.setBounds(113, 421, 116, 22);
		panel_2.add(preidField);
		
		JLabel lblPatientSsn = new JLabel("Patient SSN");
		lblPatientSsn.setBounds(259, 424, 99, 16);
		panel_2.add(lblPatientSsn);
		
		prepatientSSNField = new JTextField();
		prepatientSSNField.setColumns(10);
		prepatientSSNField.setBounds(363, 421, 116, 22);
		panel_2.add(prepatientSSNField);
		
		predateField = new JTextField();
		predateField.setColumns(10);
		predateField.setBounds(629, 421, 116, 22);
		panel_2.add(predateField);
		
		prephySSNField = new JTextField();
		prephySSNField.setColumns(10);
		prephySSNField.setBounds(363, 446, 116, 22);
		panel_2.add(prephySSNField);
		
		JLabel label_5 = new JLabel("Patient Address");
		label_5.setBounds(259, 449, 99, 16);
		panel_2.add(label_5);
		
		prestatusField = new JTextField();
		prestatusField.setColumns(10);
		prestatusField.setBounds(113, 446, 116, 22);
		panel_2.add(prestatusField);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(26, 449, 82, 16);
		panel_2.add(lblStatus);
		
		JLabel lblYyyymmdd = new JLabel("yyyy-mm-dd");
		lblYyyymmdd.setBounds(535, 437, 82, 16);
		panel_2.add(lblYyyymmdd);
		
		JLabel lblPrescriptionDate = new JLabel("Prescription Date");
		lblPrescriptionDate.setBounds(511, 424, 106, 16);
		panel_2.add(lblPrescriptionDate);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(525, 469, 99, 16);
		panel_2.add(lblQuantity);
		
		prequantityField = new JTextField();
		prequantityField.setColumns(10);
		prequantityField.setBounds(629, 466, 116, 22);
		panel_2.add(prequantityField);
		
		JButton btnCreatePrescription = new JButton("Create Prescription");
		btnCreatePrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int pre_id = Integer.parseInt(preidField.getText());
					String status = prestatusField.getText();
					String patient_ssn = prepatientSSNField.getText();
					String phy_ssn = prephySSNField.getText();
					String pre_date = predateField.getText();
					int quantity = Integer.parseInt(prequantityField.getText());
					String trade_name = preTradeNameField.getText();
					String phar_co_name = prePharCoNameField.getText();

					Statement smt = con.createStatement();
					smt.execute("INSERT INTO prescription(pre_id,status,ssn,phy_ssn,pre_date,quantity,trade_name,pharm_co_name) "
							+ "VALUES(\""+pre_id+"\",\""+status+"\",\""+patient_ssn+"\",\""+phy_ssn+"\",\""+pre_date+"\",\""+quantity+"\",\""+trade_name+"\",\""+phar_co_name+"\")");
					String data = "Patient Info Created : ";
					JOptionPane.showMessageDialog(null,data);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}

			}
		});
		btnCreatePrescription.setBounds(505, 531, 152, 25);
		panel_2.add(btnCreatePrescription);
		
		JLabel lblTradeName = new JLabel("Trade Name");
		lblTradeName.setBounds(26, 478, 82, 16);
		panel_2.add(lblTradeName);
		
		preTradeNameField = new JTextField();
		preTradeNameField.setColumns(10);
		preTradeNameField.setBounds(113, 475, 116, 22);
		panel_2.add(preTradeNameField);
		
		JLabel lblPharmacoName = new JLabel("Pharma_Co Name");
		lblPharmacoName.setBounds(259, 475, 116, 16);
		panel_2.add(lblPharmacoName);
		
		prePharCoNameField = new JTextField();
		prePharCoNameField.setColumns(10);
		prePharCoNameField.setBounds(363, 475, 116, 22);
		panel_2.add(prePharCoNameField);
		
		JButton btnDeleteDoctor = new JButton("Delete Doctor");
		btnDeleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String doctor_ssn = doctorSSNField.getText();
					
					
					Statement smt = con.createStatement();
					smt.execute("DELETE FROM doctor WHERE ssn=\""+doctor_ssn+"\"");
					String data = "Doctor Info Deleted : ";
					JOptionPane.showMessageDialog(null,data);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnDeleteDoctor.setBounds(665, 341, 116, 25);
		panel_2.add(btnDeleteDoctor);
		
		JButton btnDeletePatient = new JButton("Delete Patient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String patient_ssn = patientSSNField.getText();
					
					
					Statement smt = con.createStatement();
					smt.execute("DELETE FROM pri_phy_patient WHERE ssn=\""+patient_ssn+"\"");
					String data = "Patient Info Deleted : ";
					JOptionPane.showMessageDialog(null,data);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}

			}
		});
		btnDeletePatient.setBounds(665, 239, 116, 25);
		panel_2.add(btnDeletePatient);
		
		JButton btnDeletePrescription = new JButton("Delete Prescription");
		btnDeletePrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			try {
				String pre_id = preidField.getText();
				
				Statement smt = con.createStatement();
				smt.execute("DELETE FROM prescription WHERE pre_id=\""+pre_id+"\"");
				String data = "Prescription Deleted :";
				JOptionPane.showMessageDialog(null, data);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			}
			
		});
		btnDeletePrescription.setBounds(665, 531, 139, 25);
		panel_2.add(btnDeletePrescription);
		
		JLabel lblNewLabel_1 = new JLabel("YET TO BE PROCESSED");
		lblNewLabel_1.setBounds(26, 571, 139, 16);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblCompletedToday = new JLabel("COMPLETED TODAY");
		lblCompletedToday.setBounds(274, 571, 139, 16);
		panel_2.add(lblCompletedToday);
		
		JLabel lblReadyToGo = new JLabel("READY TO GO");
		lblReadyToGo.setBounds(528, 571, 139, 16);
		panel_2.add(lblReadyToGo);
		
		JLabel lblPrescriptionList = new JLabel("PRESCRIPTION LIST");
		lblPrescriptionList.setBounds(26, 545, 203, 16);
		panel_2.add(lblPrescriptionList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 585, 236, 160);
		panel_2.add(scrollPane_1);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(277, 585, 233, 160);
		panel_2.add(scrollPane_2);
		
		
	
		
		
		
		
		//
		DefaultListModel listModel2 = new DefaultListModel();
		DefaultListModel listModel3 = new DefaultListModel();
		DefaultListModel listModel4 = new DefaultListModel();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(528, 591, 236, 154);
		panel_2.add(scrollPane_3);
		
		JList readyToGoList = new JList(listModel2);
		scrollPane_3.setViewportView(readyToGoList);
		
		JList yetToProcessList = new JList(listModel3);
		scrollPane_1.setViewportView(yetToProcessList);
		
		JList completedTodayList = new JList(listModel4);
		scrollPane_2.setViewportView(completedTodayList);
		
		
		JButton btnShowPrescriptionList = new JButton("Show Prescription List");
		btnShowPrescriptionList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rowIndex = 0;
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery("SELECT * FROM prescription WHERE status=\"ready-to-go\"");
			
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();

					
					String data = "Prescription Info : ";
					
					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel2.addElement(data);          
						data = "Patient Info : ";   
					}
					
					rs = smt.executeQuery("SELECT * FROM prescription WHERE status=\"yet-to-go\"");
					rsmd = rs.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel3.addElement(data);          
						data = "Prescription Info : ";   
					}
					
					rs = smt.executeQuery("SELECT * FROM prescription WHERE status=\"completed\"");
					rsmd = rs.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel4.addElement(data);          
						data = "Prescription Info : ";   
					}

					
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnShowPrescriptionList.setBounds(153, 541, 191, 25);
		panel_2.add(btnShowPrescriptionList);
		
		JLabel lblDrugInformation = new JLabel("DRUG INFORMATION");
		lblDrugInformation.setBounds(26, 772, 139, 16);
		panel_2.add(lblDrugInformation);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(26, 806, 236, 147);
		panel_2.add(scrollPane_4);
		

		
		JLabel lblAllAvailableDrugs = new JLabel("All Available Drugs");
		scrollPane_4.setColumnHeaderView(lblAllAvailableDrugs);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(274, 801, 236, 152);
		panel_2.add(scrollPane_5);
		
		
		
		
		
		JLabel lblSoldByPharmacy = new JLabel("Sold by Pharmacy");
		scrollPane_5.setColumnHeaderView(lblSoldByPharmacy);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(527, 802, 237, 151);
		panel_2.add(scrollPane_6);
		
		DefaultListModel listModel5 = new DefaultListModel();
		DefaultListModel listModel6 = new DefaultListModel();
		DefaultListModel listModel7 = new DefaultListModel();
		DefaultListModel listModel_A = new DefaultListModel();
		
		JList soldByPharmacyList = new JList(listModel6);
		scrollPane_5.setViewportView(soldByPharmacyList);
		
		JList expensiveDrugsList = new JList(listModel7);
		scrollPane_6.setViewportView(expensiveDrugsList);
		
		JList allDrugsList = new JList(listModel5);
		scrollPane_4.setViewportView(allDrugsList);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(795, 801, 237, 151);
		panel_2.add(scrollPane_7);
		
		
		JList multiplePharPresList = new JList(listModel_A);
		scrollPane_7.setViewportView(multiplePharPresList);
		
		JLabel lblMostExpensiveDrugs = new JLabel("Most Expensive Drugs");
		scrollPane_6.setColumnHeaderView(lblMostExpensiveDrugs);
		
		JButton btnShowDrugList = new JButton("Show Drug List");
		btnShowDrugList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					int rowIndex = 0;
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery("SELECT * FROM make_drug");
			
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();

					
					String data = "Drug Info : ";
					
					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel5.addElement(data);          
						data = "Drug Info : ";   
					}
					
					rs = smt.executeQuery("SELECT * FROM sell");
					rsmd = rs.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel6.addElement(data);          
						data = "Drug Info : ";   
					}
					
					rs = smt.executeQuery("SELECT * FROM sell ORDER BY price");
					rsmd = rs.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel7.addElement(data);          
						data = "Prescription Info : ";   
					}
					
					rs = smt.executeQuery("SELECT m.pharm_co_name, p.phone, s.pharm_co_name,q.phone , m.trade_name FROM make_drug m JOIN sell s ON "
							+ "s.trade_name=m.trade_name AND s.pharm_co_name<>m.pharm_co_name "
							+ "JOIN pharm_co p ON m.pharm_co_name=p.name "
							+ "JOIN pharm_co q ON s.pharm_co_name=q.name");
					rsmd = rs.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel_A.addElement(data);          
						data = "Prescription Info : ";   
					}

					
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}

			}
			
		});
		btnShowDrugList.setBounds(153, 768, 191, 25);
		panel_2.add(btnShowDrugList);
		
	
		
		
		JLabel lblNewLabel_2 = new JLabel("Pharmacy Selling Same Drugs");
		scrollPane_7.setColumnHeaderView(lblNewLabel_2);
		
		
		JTextArea contractTextField = new JTextArea();
		contractTextField.setBounds(920, 532, 167, 62);
		panel_2.add(contractTextField);
		

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(816, 213, 271, 195);
		panel_2.add(scrollPane_8);
		
		
		JLabel lblContractInfo = new JLabel("CONTRACT INFO");
		lblContractInfo.setBounds(836, 179, 116, 16);
		panel_2.add(lblContractInfo);
		

		
		JLabel lblAvailableContracts = new JLabel("Available Contracts");
		scrollPane_8.setColumnHeaderView(lblAvailableContracts);
		
		DefaultListModel listModel8 = new DefaultListModel();
		JList contractList = new JList(listModel8);
		scrollPane_8.setViewportView(contractList);
		
		JButton btnShowContracts = new JButton("Show Contracts");
		btnShowContracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rowIndex = 0;
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery("SELECT * FROM contract");
			
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();

					
					String data = "Contract Info : ";
					
					while(rs.next()){
						for(int i = 1; i<= columnsNumber;i++){
							if (i > 1) System.out.print(",  ");
					           String columnValue = rs.getString(i);	
					           System.out.println("   " + rsmd.getColumnName(i)+ ": "+columnValue);
					           data +="   " + rsmd.getColumnName(i)+ ": "+columnValue;
					           
						}
						listModel8.addElement(data);          
						data = "Drug Info : ";   
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}

			}
		});
		btnShowContracts.setBounds(945, 179, 143, 25);
		panel_2.add(btnShowContracts);
		
		JLabel price = new JLabel("pharm_id");
		price.setBounds(816, 427, 99, 16);
		panel_2.add(price);
		
		contractPharIDField = new JTextField();
		contractPharIDField.setColumns(10);
		contractPharIDField.setBounds(920, 424, 116, 22);
		panel_2.add(contractPharIDField);
		
		JLabel lblSupervisor = new JLabel("Supervisor");
		lblSupervisor.setBounds(816, 474, 99, 16);
		panel_2.add(lblSupervisor);
		
		contractSupervisorField = new JTextField();
		contractSupervisorField.setColumns(10);
		contractSupervisorField.setBounds(920, 471, 116, 22);
		panel_2.add(contractSupervisorField);
		
		JLabel label_4 = new JLabel("Pharma_Co Name");
		label_4.setBounds(816, 500, 116, 16);
		panel_2.add(label_4);
		
		contractPharmaCoField = new JTextField();
		contractPharmaCoField.setColumns(10);
		contractPharmaCoField.setBounds(920, 500, 116, 22);
		panel_2.add(contractPharmaCoField);
		
		JLabel lblContractText = new JLabel("Contract Text");
		lblContractText.setBounds(816, 534, 99, 16);
		panel_2.add(lblContractText);
		
		
	
		
		
		
		JButton btnCreateContract = new JButton("Create Contract");
		btnCreateContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pharm_id = contractPharIDField.getText();
					String text = contractTextField.getText();
					String supervisor = contractSupervisorField.getText();
					String pharm_co_name = contractPharmaCoField.getText();
					String start_date = contractStartDateField.getText();
					String end_date = contractEndDateField.getText();
					
					Statement smt = con.createStatement();
					smt.execute("INSERT INTO contract(pharm_id,start_date,end_date,text,supervisor,pharm_co_name) "
							+ "VALUES (\""+pharm_id+"\",\""+start_date+"\",\""+end_date+"\",\""+text+"\",\""+supervisor+"\",\""+pharm_co_name+"\")");
					String data = "Contract Created :";
					JOptionPane.showMessageDialog(null, data);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnCreateContract.setBounds(816, 662, 136, 25);
		panel_2.add(btnCreateContract);
		
		JButton btnDeleteContract = new JButton("Delete Contract");
		btnDeleteContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pharm_id = contractPharIDField.getText();
					
					Statement smt = con.createStatement();
					smt.execute("DELETE FROM contract WHERE pharm_id=\""+pharm_id+"\"");
					String data = "Contract Deleted :";
					JOptionPane.showMessageDialog(null, data);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnDeleteContract.setBounds(964, 662, 123, 25);
		panel_2.add(btnDeleteContract);
		
		JButton updateContractBtn = new JButton("Update Contract");
		updateContractBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pharm_id = contractPharIDField.getText();
					String text = contractTextField.getText();
					String supervisor = contractSupervisorField.getText();
					String pharm_co_name = contractPharmaCoField.getText();
					String start_date = contractStartDateField.getText();
					String end_date = contractEndDateField.getText();
					
					Statement smt = con.createStatement();
					smt.execute("UPDATE contract SET start_date=\""+start_date+"\",end_date=\""+end_date+"\",text=\""+text+"\",supervisor=\""+supervisor+"\",pharm_co_name=\""+pharm_co_name+"\" WHERE pharm_id=\""+pharm_id+"\""); 
							
					String data = "Contract Updated :";
					JOptionPane.showMessageDialog(null, data);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		updateContractBtn.setBounds(816, 700, 136, 25);
		panel_2.add(updateContractBtn);
		
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(816, 449, 99, 16);
		panel_2.add(lblStartDate);
		
		contractStartDateField = new JTextField();
		contractStartDateField.setColumns(10);
		contractStartDateField.setBounds(920, 446, 116, 22);
		panel_2.add(contractStartDateField);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(816, 610, 99, 16);
		panel_2.add(lblEndDate);
		
		contractEndDateField = new JTextField();
		contractEndDateField.setColumns(10);
		contractEndDateField.setBounds(920, 607, 116, 22);
		panel_2.add(contractEndDateField);
		
		//PRESCRIPTION LIST QUERIES
		
		
		
		
		
		


			
		
		
	}
}
