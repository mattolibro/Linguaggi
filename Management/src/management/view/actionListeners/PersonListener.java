package management.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import management.view.windows.AddPersonWindow;

public class PersonListener implements ActionListener {

	private AddPersonWindow addPersonWindow;
	
	
	public PersonListener(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		addPersonWindow.changePanel();
	}
}
