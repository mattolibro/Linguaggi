package management.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudyMouseAdapter extends MouseAdapter {
	
	private AddPersonWindow addPersonWindow;
	
	
	public StudyMouseAdapter(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		addPersonWindow.addAnotherStudy();
	}
}
