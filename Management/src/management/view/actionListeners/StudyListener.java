package management.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import management.view.windows.AddPersonWindow;

public class StudyListener implements ActionListener {
	
	private AddPersonWindow addPersonWindow;
	
	
	public StudyListener(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addPersonWindow.addAnotherStudy();
	}
}
