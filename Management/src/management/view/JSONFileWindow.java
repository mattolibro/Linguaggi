package management.view;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import management.model.Utilities;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JSONFileWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;

	private File file;
	BufferedReader in;

	/**
	 * Create the frame.
	 */
	public JSONFileWindow(File file) {

		this.file = file;

		setTitle("JSON File");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		updateTextJSONFile();
	}

	private void updateTextJSONFile() {

		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;
			int numLines = Utilities.countLines(file.getPath());
			String textFile = "";

			while((line = in.readLine()) != null) {
				count++;

				if (count == numLines-2) {
					textFile +=line+",\n";
				}
				else {
					textFile += line+"\n";
				}
			}
			textArea.setText(textFile);

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
