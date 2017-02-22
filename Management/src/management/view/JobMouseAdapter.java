package management.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JobMouseAdapter extends MouseAdapter {
	private AddPersonWindow addPersonWindow;
	
	
	public JobMouseAdapter(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		addPersonWindow.addAnotherJob();
	}
}
