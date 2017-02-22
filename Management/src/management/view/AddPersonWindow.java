package management.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AddPersonWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private Person person;
	private Address address;
	private CV cv;
	private Study study;
	private Job job;
	
	private JPanel panel;
	private JLabel lblId;
	private JTextField textField;
	private JLabel cvLabel;
	private JButton btnNewButton;
	StudyMouseAdapter studyMouseAdapter;
	

	/**
	 * Create the frame.
	 */
	public AddPersonWindow(JFrame frame) {
		frame.setEnabled(false);

		person = new Person();
		address = new Address();
		cv = new CV();
		study = new Study();
		job = new Job();

		setTitle("Add Person");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 130);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		cvLabel = new JLabel("CV: ");
		contentPane.add(cvLabel, BorderLayout.NORTH);
		cvLabel.setVisible(true);
		
		//Panel changes after every click on DONE
		{
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));

			lblId = new JLabel("[Study] Date of Start");
			panel.add(lblId, BorderLayout.NORTH);

			textField = new JTextField();
			panel.add(textField, BorderLayout.CENTER);
			textField.setColumns(10);

			btnNewButton = new JButton("DONE");
	
			PersonMouseAdapter personMouseAdapter = new PersonMouseAdapter(this);
			btnNewButton.addMouseListener(personMouseAdapter);
			panel.add(btnNewButton, BorderLayout.SOUTH);
			
		}
		
	}
	
	void setTextLabel(String text) {
		lblId.setText(text);
	}
	
	public void changePanel() {
		
		System.out.println("label is "+lblId.getText());
		
		switch(lblId.getText()) {
		
		case "ID":
			person.setID(textField.getText());
			textField.setText("");
			lblId.setText("FirstName");
			System.out.println(person.getID());
			break;
			
		case "FirstName":
			person.setFirstName(textField.getText());
			textField.setText("");
			lblId.setText("LastName");
			System.out.println(person.getFirstName());
			break;
			
		case "LastName":
			person.setLastName(textField.getText());
			textField.setText("");
			lblId.setText("Date of Birth");
			textField.setToolTipText("yyyy-mm-dd");
			System.out.println(person.getLastName());
			break;
			
		case "Date of Birth":
			person.setDateOfBirth((textField.getText()));
			textField.setText("");
			textField.setToolTipText(null);
			lblId.setText("Street");
			System.out.println(person.getDateOfBirth().toString());
			break;
			
		case "Street":
			address.setStreet(textField.getText());
			textField.setText("");
			lblId.setText("City");
			break;
			
		case "City":
			address.setCity(textField.getText());
			textField.setText("");
			lblId.setText("Postal Code");
			break;
			
		case "Postal Code":
			address.setPostalCode(textField.getText());
			textField.setText("");
			lblId.setText("Country");
			break;
			
		case "Country":
			address.setCountry(textField.getText());
			textField.setText("");
			person.setAddress(address);
			address = new Address(); // address refreshed, because it's completed and it can be used for another person
			cvLabel.setVisible(true);
			lblId.setText("[Study] Name Study");
			break;
			
		case "[Study] Name Study":
			System.out.println("It works");
			study.setNameStudy((textField.getText()));
			textField.setText("");
			lblId.setText("[Study] At");
			break;
			
		case "[Study] At":
			study.setAt((textField.getText()));
			textField.setText("");
			textField.setToolTipText("yyyy-mm-dd");
			lblId.setText("[Study] Date of Start");
			break;
		
		case "[Study] Date of Start":
			study.setStartDate((textField.getText()));
			textField.setText("");
			textField.setToolTipText("yyyy-mm-dd");
			lblId.setText("[Study] Date of End");
			studyMouseAdapter = new StudyMouseAdapter(this);
			btnNewButton.addMouseListener(studyMouseAdapter);
			break;
		
		case "[Study] Date of End":
			study.setEndDate((textField.getText()));
			cv.AddStudy(study);
			study = new Study(); // study refreshed, because it's completed and it can be used again also for the same CV
			textField.setText("");
			textField.setToolTipText(null);
			break;
		}
	}
	
	public void addAnotherStudy() {
		btnNewButton.removeMouseListener(studyMouseAdapter);
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Study");
		continueToInsertDialog.setVisible(true);
	}
	
}
