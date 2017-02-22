package management.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonMouseAdapter extends MouseAdapter {

	private AddPersonWindow addPersonWindow;
	
	
	public PersonMouseAdapter(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		addPersonWindow.changePanel();
	}
}
