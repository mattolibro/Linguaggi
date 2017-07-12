package management.view.windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import management.data.Data;
import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;
import management.speech.SpeechInput;
import management.view.actionListeners.JobListener;
import management.view.actionListeners.LanguageListener;
import management.view.actionListeners.PersonListener;
import management.view.actionListeners.StudyListener;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingWorker;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPersonWindow extends SpeechTemplateSpeech {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Person person;
	private Address address;
	private CV cv;
	private Study study;
	private Job job;
	
	private AddPersonWindow addPersonWindow = this;
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblId;
	private JLabel cvLabel;
	private JButton doneButton;
	
	private StudyListener studyListener;
	private LanguageListener languageListener;
	private JobListener jobListener;
	
	private JFrame frame;
	private JPanel panel_1;
	private JTextField textField;
	private JLabel recording_label;
	
	/**
	 * Create the frame.
	 */
	/**
	 * @param frame
	 * @param mwa
	 */
	public AddPersonWindow(JFrame frame) {
		
		this.frame = frame;
		
		frame.setEnabled(false);

		/* Initialization of all the objects used */
		person = new Person();
		address = new Address();
		cv = new CV();
		study = new Study();
		job = new Job();
		
		/*----------------------------------------*/

		setTitle("Add Person");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 145);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		cvLabel = new JLabel("CV: ");
		contentPane.add(cvLabel, BorderLayout.NORTH);
		cvLabel.setVisible(false);
		
		// Panel changes after every click on DONE
		{
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));

			lblId = new JLabel("ID");
			panel.add(lblId, BorderLayout.NORTH);

			doneButton = new JButton("DONE");
			PersonListener personListener = new PersonListener(this);
			doneButton.addActionListener(personListener);
			panel.add(doneButton, BorderLayout.SOUTH);
			
			panel_1 = new JPanel();
			panel.add(panel_1, BorderLayout.CENTER);
			
			textField = new JTextField();
			textField.setColumns(10);
			
			recording_label = new JLabel("");
			Font fontRecordingLabel  = new Font(recording_label.getFont().getName(), Font.ITALIC, recording_label.getFont().getSize());
			recording_label.setFont(fontRecordingLabel);
			
			JButton speechRecognitionButton = new JButton("");
			speechRecognitionButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					SwingWorker<String, Void> myWorker= new SwingWorker<String, Void>() {
					    @Override
					    protected String doInBackground() throws Exception {
							SpeechInput speechInput = new SpeechInput(addPersonWindow);
							speechInput.recording();
					        return null;
					    }
					};
					myWorker.execute();
					
				}
			});
			
			// Icon for using the speech recognition
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img/mic.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
			speechRecognitionButton.setIcon(imageIcon);
			
			
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(speechRecognitionButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap(304, Short.MAX_VALUE)
								.addComponent(recording_label)))
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
							.addComponent(speechRecognitionButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addComponent(recording_label))
			);
			panel_1.setLayout(gl_panel_1);
			
			this.getRootPane().setDefaultButton(doneButton);
		}
		
	}

	public void addAnotherJob() {
		doneButton.removeActionListener(jobListener);
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Job");
		continueToInsertDialog.setVisible(true);
	}
	
	public void addAnotherStudy() {
		doneButton.removeActionListener(studyListener);
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Study");
		continueToInsertDialog.setVisible(true);
	}
	
	public void addAnotherLanguage() {
		ContinueToInsertDialog continueToInsertDialog = new ContinueToInsertDialog(this, "Language");
		continueToInsertDialog.setVisible(true);
	}
	
	public void addLanguageListener(LanguageListener languageListener) {
		this.languageListener = languageListener;
		doneButton.addActionListener(this.languageListener);
	}
	
	/*
	 * it changes the panel for each field
	 */
	public void changePanel() {
		
		switch(lblId.getText()) {
		
		case "ID":
			person.setID(textField.getText());
			textField.setText("");
			lblId.setText("FirstName");
			break;
			
		case "FirstName":
			person.setFirstName(textField.getText());
			textField.setText("");
			lblId.setText("LastName");
			break;
			
		case "LastName":
			person.setLastName(textField.getText());
			textField.setText("");
			lblId.setText("Date of Birth");
			textField.setToolTipText("yyyy-mm-dd");
			break;
			
		case "Date of Birth":
	
			person.setDateOfBirth((textField.getText()));
			textField.setText("");
			textField.setToolTipText(null);
			lblId.setText("Street");
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
			
			studyListener = new StudyListener(this); // mouseAdapter created for knowing if adding another study
			doneButton.addActionListener(studyListener);
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
			
			jobListener = new JobListener(this);	// mouseAdapter created for knowing if adding another job
			doneButton.addActionListener(jobListener);
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
	
	public void removeLanguageListener() {
		doneButton.removeActionListener(languageListener);
	}
	
	public void savePerson() {
		person.setCv(cv); // here sets the curriculum vitae of person before saving person
		Data.addPerson(person);
		frame.setEnabled(true);
		this.dispose();
	}
	
	public void setRecording_label(String text) {
		recording_label.setText(text);
	}
	
	public void setTextLabel(String text) {
		lblId.setText(text);
	}
	
	public void setTextField(String text) {
		textField.setText(text);
	}
	
	
}
