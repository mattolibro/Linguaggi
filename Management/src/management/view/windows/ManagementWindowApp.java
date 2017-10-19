package management.view.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import management.data.Data;


public class ManagementWindowApp {

	private JFrame frame;
	private static JTextArea textArea;

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

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Management App");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				AddPersonWindow addPersonWindow = new AddPersonWindow(frame);
				addPersonWindow.setVisible(true);
				addPersonWindow.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setEnabled(true);
						e.getWindow().dispose();
						frame.setVisible(true);
					}
				});
			}
		});

		JButton btnJsonFile = new JButton("Show JSON file");
		btnJsonFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JSONFileWindow jsonFileWindow = new JSONFileWindow(Data.getFile());
				jsonFileWindow.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setEnabled(true);
						e.getWindow().dispose();
					}
				});
				jsonFileWindow.setVisible(true);	
				frame.setEnabled(false);
			}
		});

		JButton searchButton = new JButton("Search Person");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingWorker<String, Void> myWorker= new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() throws Exception {
						SearchWindow searchWindow = new SearchWindow();
						searchWindow.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								frame.setEnabled(true);
								e.getWindow().dispose();
								frame.setVisible(true);
							}
						});
						searchWindow.setVisible(true);
						frame.setEnabled(false);
						return null;
					}
				};
				myWorker.execute();

			}
		});

		JLabel lblPeople = new JLabel("People");		

		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane (textArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnJsonFile)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnAddPerson, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(searchButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPeople)
							.addContainerGap())
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addComponent(btnAddPerson)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchButton)
					.addGap(13)
					.addComponent(btnJsonFile)
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblPeople)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);

		Data.initialize();
		textFieldInitialization();
	}

	/** It initializes the map of people (it fetches the data from the JSONfile), all the maps for the different fields and updates the textArea **/

	private void textFieldInitialization() {

		int count = 1;
		for(String id : Data.getPeople().keySet()) {
			textArea.setText(textArea.getText()+"Person "+ count +":\n\n"+Data.getPeople().get(id).toString()+"\n");
			count++;
		}
	}
	
	public static void textFieldUpdate(String person) {
		textArea.setText(textArea.getText()+"Person "+ Data.getPeople().size() + ":\n\n"+ person+"\n");
	}
	
}
