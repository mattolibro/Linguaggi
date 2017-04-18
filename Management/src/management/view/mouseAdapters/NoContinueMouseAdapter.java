package management.view.mouseAdapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import management.view.windows.AddPersonWindow;
import management.view.windows.ContinueToInsertDialog;

public class NoContinueMouseAdapter extends MouseAdapter {
	
	private ContinueToInsertDialog continueToInsertDialog;
	private AddPersonWindow addPersonWindow;
	private String option;
	private LanguageMouseAdapter languageMouseAdapter;

	public NoContinueMouseAdapter(ContinueToInsertDialog continueToInsertDialog, AddPersonWindow addPersonWindow, String option) {
		this.continueToInsertDialog = continueToInsertDialog;
		this.addPersonWindow = addPersonWindow;
		this.option = option;
		this.languageMouseAdapter = new LanguageMouseAdapter(addPersonWindow);
	}
	
public void mouseClicked(MouseEvent e) {
		
		continueToInsertDialog.dispose();
		
		switch(option) {
		
		case "Study":
			addPersonWindow.setTextLabel("Language"); // -> changePanel starts inserting a new language
			addPersonWindow.addLanguageMouseAdapter(languageMouseAdapter);
			break;
			
		case "Language":
			addPersonWindow.removeLanguageMouseAdapter();
			addPersonWindow.setTextLabel("[Job] Name Job"); // -> changePanel starts inserting a new job
			break;
			
		case "Job":
			addPersonWindow.savePerson(); // here person is saved
			break;
		}
		
		addPersonWindow.setEnabled(true);
	}
	
}
