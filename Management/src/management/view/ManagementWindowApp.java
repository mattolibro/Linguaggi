package management.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import management.model.Person;

public class ManagementWindowApp {

	private List<Person> people;
	
	private JFrame frame;
	private ManagementWindowApp mwa = this;

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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Management App");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				AddPersonWindow addPersonWindow = new AddPersonWindow(frame, mwa);
				addPersonWindow.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnAddPerson = new GridBagConstraints();
		gbc_btnAddPerson.gridx = 5;
		gbc_btnAddPerson.gridy = 4;
		frame.getContentPane().add(btnAddPerson, gbc_btnAddPerson);
		
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}

}
