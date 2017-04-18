package management.view.mouseAdapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import management.view.windows.AddPersonWindow;

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
