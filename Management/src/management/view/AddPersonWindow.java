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
	private JButton doneButton;
	
	private StudyMouseAdapter studyMouseAdapter;
	private LanguageMouseAdapter languageMouseAdapter;
	private JobMouseAdapter jobMouseAdapter;
	
	private ManagementWindowApp mwa;
	private JFrame frame;
	/**
	 * Create the frame.
	 */
	public AddPersonWindow(JFrame frame, ManagementWindowApp mwa) {
		
		this.mwa = mwa;
		this.frame = frame;
		
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
		cvLabel.setVisible(false);
		
		//Panel changes after every click on DONE
		{
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));

			lblId = new JLabel("ID");
			panel.add(lblId, BorderLayout.NORTH);

			textField = new JTextField();
			panel.add(textField, BorderLayout.CENTER);
			textField.setColumns(10);

			doneButton = new JButton("DONE");
	
			PersonMouseAdapter personMouseAdapter = new PersonMouseAdapter(this);
			doneButton.addMouseListener(personMouseAdapter);
			panel.add(doneButton, BorderLayout.SOUTH);
			
		}
		
	}
	
	void setTextLabel(String text) {
		lblId.setText(text);
	}
	
	void removeLanguageMouseAdapter() {
		doneButton.removeMouseListener(languageMouseAdapter);
	}
	
	void addLanguageMouseAdapter(LanguageMouseAdapter languageMouseAdapter) {
		this.languageMouseAdapter = languageMouseAdapter;
		doneButton.addMouseListener(this.languageMouseAdapter);
	}
	
	public void changePanel() {
		
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
			cvLabel.setVisible(true);
			lblId.setText("[Study] Name Study");
			break;
			
		case "[Study] Name Study":
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
			
			studyMouseAdapter = new StudyMouseAdapter(this); // mouseAdapter created for knowing if adding another study
			doneButton.addMouseListener(studyMouseAdapter);
			break;
		
		case "[Study] Date of End":
			study.setEndDate((textField.getText()));
			cv.AddStudy(study);
			study = new Study(); // study refreshed, because it's completed and it can be used again also for the same CV
			textField.setText("");
			textField.setToolTipText(null);
			break;
			
		case "Language":
			cv.AddLanguage(textField.getText());
			textField.setText("");
			break;
			
		case "[Job] Name Job":
			cvLabel.setVisible(true);
			job.setNameJob(textField.getText());
			textField.setText("");
			lblId.setText("[Job] At");
			break;
			
		case "[Job] At":
			job.setAt((textField.getText()));
			textField.setText("");
			textField.setToolTipText("yyyy-mm-dd");
			lblId.setText("[Job] Date of Start");
			break;
		
		case "[Job] Date of Start":
			job.setStartDate((textField.getText()));
			textField.setText("");
			textField.setToolTipText("yyyy-mm-dd");
			lblId.setText("[Job] Date of End");
			
			jobMouseAdapter = new JobMouseAdapter(this);	// mouseAdapter created for knowing if adding another job
			doneButton.addMouseListener(jobMouseAdapter);
			break;
		
		case "[Job] Date of End":
			job.setEndDate((textField.getText()));
			cv.AddJob(job);
			job = new Job(); // job refreshed, because it's completed and it can be used again also for the same CV
			textField.setText("");
			textField.setToolTipText(null);
			cvLabel.setVisible(false);
			break;
		
		}
	}
	
	public void addAnotherStudy() {
		doneButton.removeMouseListener(studyMouseAdapter);
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Study");
		continueToInsertDialog.setVisible(true);
	}
	
	public void addAnotherLanguage() {
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Language");
		continueToInsertDialog.setVisible(true);
	}
	
	public void addAnotherJob() {
		doneButton.removeMouseListener(jobMouseAdapter);
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Job");
		continueToInsertDialog.setVisible(true);
	}
	
	public void savePerson() {
		person.setCv(cv); // here sets the curriculum vitae of person before saving person
		mwa.addPerson(person);
		frame.setEnabled(true);
		this.dispose();
	}
	
}