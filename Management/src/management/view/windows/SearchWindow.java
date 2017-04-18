package management.view.windows;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import management.speech.SpeechInput;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class SearchWindow extends SpeechTemplateSpeech {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchWindow searchWindow = this;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnOk;
	private JButton speechRecognitionButton;
	private JLabel recording_label;


	/**
	 * Create the frame.
	 */
	public SearchWindow() {
		setTitle("Search Person");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 440, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Question:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		speechRecognitionButton = new JButton("");
		speechRecognitionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingWorker<String, Void> myWorker= new SwingWorker<String, Void>() {
				    @Override
				    protected String doInBackground() throws Exception {
						SpeechInput speechInput = new SpeechInput(searchWindow);
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
		
		recording_label = new JLabel("");
		Font fontRecordingLabel  = new Font(recording_label.getFont().getName(), Font.ITALIC, recording_label.getFont().getSize());
		recording_label.setFont(fontRecordingLabel);
		
		btnOk = new JButton("OK");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(recording_label)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(speechRecognitionButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(speechRecognitionButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addComponent(btnOk))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(recording_label)
							.addContainerGap())))
		);
		contentPane.setLayout(gl_contentPane);
	}


	@Override
	public void setRecording_label(String text) {
		recording_label.setText(text);
	}

	@Override
	public void setTextField(String text) {
		textField.setText(text);
	}
}
