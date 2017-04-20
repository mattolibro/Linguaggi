package management.view.windows;

import java.awt.BorderLayout;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import management.model.Person;

public class PeopleFoundWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public PeopleFoundWindow(String title, TreeSet<Person> peopleFound) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(title);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		/*-----Inserting people found in the textarea-----*/
		if(!peopleFound.isEmpty()){
			for(Person personFound : peopleFound){
				textArea.setText(textArea.getText()+"\n"+personFound.toString());
			}
		}
		else {
			textArea.setText("Not people found!");
		}
	}

}
