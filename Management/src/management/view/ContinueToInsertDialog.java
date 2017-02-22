package management.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ContinueToInsertDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblDoYouWant;

	/**
	 * Create the dialog.
	 */
	public ContinueToInsertDialog(AddPersonWindow addPersonWindow, String option) {

		addPersonWindow.setEnabled(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 105);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblDoYouWant = new JLabel("Do you want to add another "+option+"?");
		contentPanel.add(lblDoYouWant);

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ContinueMouseAdapter continueMouseAdapter = new ContinueMouseAdapter(this, addPersonWindow, option);
		JButton yesButton = new JButton("YES");
		yesButton.addMouseListener(continueMouseAdapter);
		buttonPane.add(yesButton);

		NoContinueMouseAdapter noContinueMouseAdapter = new NoContinueMouseAdapter(this, addPersonWindow, option);
		JButton noButton = new JButton("NO");
		noButton.addMouseListener(noContinueMouseAdapter);
		buttonPane.add(noButton);
	}

}
