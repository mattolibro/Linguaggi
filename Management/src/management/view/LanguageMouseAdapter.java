package management.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LanguageMouseAdapter extends MouseAdapter {

	private AddPersonWindow addPersonWindow;
	
	
	public LanguageMouseAdapter(AddPersonWindow addPersonWindow) {
		this.addPersonWindow = addPersonWindow;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		addPersonWindow.addAnotherLanguage();
	}
}
