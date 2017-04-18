package management.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import management.view.windows.AddPersonWindow;

public class JobListener implements ActionListener {
	private AddPersonWindow addPersonWindow;
	
	
	public JobListener(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addPersonWindow.addAnotherJob();
	}
}
