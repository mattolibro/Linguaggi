package management.view.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import management.model.PeopleJsonFactory;
import management.model.Person;
import management.utilities.Utilities;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class ManagementWindowApp {

	private List<Person> people; // people are also in this collection and not just in the JSON file
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
		
		JButton btnNewButton = new JButton("Search");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnJsonFile)
							.addGap(18))
						.addComponent(btnAddPerson))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPeople)
							.addContainerGap())
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addComponent(btnAddPerson)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(13)
					.addComponent(btnJsonFile)
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblPeople)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);

		listPeopleInitialization();
	}

	/* it initializes the collection of people and updates the textArea */
	private void listPeopleInitialization() {
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			JsonReader rdr = Json.createReader(fis);
			JsonObject obj =  rdr.readObject();
			JsonArray results = obj.getJsonArray("People");
			int count = 1;
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				PeopleJsonFactory peopleJSonFactory = new PeopleJsonFactory(result.getJsonObject("Person"));
				people.add(peopleJSonFactory.createPerson());
				textArea.setText(textArea.getText()+"Person "+ count +":\n\n"+people.get(count-1).toString()+"\n");
				count++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void addPerson(Person person) {
		people.add(person);
		textArea.setText(textArea.getText()+"Person "+ people.size()+":\n\n"+person.toString());
		
		/* it updates the JSONFile in a new thread. I just wanna keep this update separately from the main thread */
		SwingWorker<String, Void> myWorker= new SwingWorker<String, Void>() {
		    @Override
		    protected String doInBackground() throws Exception {
		    	updateJSONFile(person); // updates the JSONFile
		        return null;
		    }
		};
		myWorker.execute();
		
	}
	
	private void updateJSONFile(Person person) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;
			int numLines = Utilities.countLines(file.getPath());
			String textFile = "";

			while((line = in.readLine()) != null) {
				count++;

				if (count == numLines-2) {
					textFile +=line+",\n"+ person.toStringJSON();
				}
				else {
					textFile += line+"\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			writer.write(textFile);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) 
				try { 
					in.close(); 

				} catch (IOException ignore) {}
		}
	}
}
