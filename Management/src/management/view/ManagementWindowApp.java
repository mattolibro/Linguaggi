package management.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class ManagementWindowApp {

	private List<Person> people;
	File file;

	private JFrame frame;
	private ManagementWindowApp mwa = this;

	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementWindowApp window = new ManagementWindowApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagementWindowApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		people = new ArrayList<Person>();

		file = new File("files/grammarSample.json");

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Management App");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				AddPersonWindow addPersonWindow = new AddPersonWindow(frame, mwa);
				addPersonWindow.setVisible(true);
				addPersonWindow.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setEnabled(true);
						e.getWindow().dispose();
					}
				});
			}
		});

		JButton btnJsonFile = new JButton("Show JSON file");
		btnJsonFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame jsonFileFrame = new JSONFileWindow(file);
				jsonFileFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setEnabled(true);
						e.getWindow().dispose();
					}
				});
				jsonFileFrame.setVisible(true);	
				frame.setEnabled(false);
			}
		});


		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane (textArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JLabel lblPeople = new JLabel("People");		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddPerson)
								.addComponent(btnJsonFile))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblPeople)
										.addContainerGap())
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(65)
						.addComponent(btnAddPerson)
						.addGap(18)
						.addComponent(btnJsonFile)
						.addContainerGap(142, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblPeople)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
				);
		frame.getContentPane().setLayout(groupLayout);

		/*---Sample person--------*/
		List<Study> studies = new ArrayList<Study>();
		studies.add(new Study("Computer Science", "Universita Bologna", LocalDate.parse("2011-09-11"), LocalDate.parse("2014-06-18")));
		studies.add(new Study("Master Software Architecture", "Universita Bologna", LocalDate.parse("2014-09-11"), LocalDate.parse("2017-06-18")));
		List<String> languagesKnown = new ArrayList<String>();
		languagesKnown.add("Italian");
		languagesKnown.add("English");
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("Programmer", "Ubisoft", LocalDate.parse("2017-07-20"), null));
		Person personExpected = new Person("ID-000001", "Pippo", "Baudo", LocalDate.parse("1989-07-31"), new Address("via della vittoria, 21", "Bologna", "40100", "Monopoli"), new CV(studies, languagesKnown, jobs));

		people.add(personExpected);

		textArea.setText("Person "+ people.size()+":\n\n"+personExpected.toString());
		/*------------------------------------------------*/
	}

	public void addPerson(Person person) {
		people.add(person);
		textArea.setText(textArea.getText()+"Person "+ people.size()+":\n\n"+person.toString());
	}

	/*private void updateJSONFile() {
		try {
			writer = new BufferedWriter(new FileWriter("files/grammarSampleNew.json"));

			in = new BufferedReader(new FileReader("files/grammarSample.json"));
			String line;
			while((line = in.readLine()) != null) {
				count++;

				if (count == numLines-2) {
					writer.write(line+",\n");
					System.out.println(personExpected.toStringJSON());
					writer.write(personExpected.toStringJSON());
				}
				else {
					System.out.println(line);
					writer.write(line+"\n");
				}
			}



		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) 
				try { 
					writer.close();
					in.close(); 
					File file1 = new File("files/grammarSample.json");
					file1.delete();
					File file2 = new File("files/grammarSampleNew.json");
					file2.renameTo(file1);
				} catch (IOException ignore) {}
		}

	}*/
}
