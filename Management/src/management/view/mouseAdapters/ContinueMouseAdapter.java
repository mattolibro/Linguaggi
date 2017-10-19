package management.view.mouseAdapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import management.view.windows.AddPersonWindow;
import management.view.windows.ContinueToInsertDialog;

public class ContinueMouseAdapter extends MouseAdapter {

	private ContinueToInsertDialog continueToInsertDialog;
	private AddPersonWindow addPersonWindow;
	private String option;

	public ContinueMouseAdapter(ContinueToInsertDialog continueToInsertDialog, AddPersonWindow addPersonWindow, String option) {
		this.continueToInsertDialog = continueToInsertDialog;
		this.addPersonWindow = addPersonWindow;
		this.option = option;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		continueToInsertDialog.dispose();
		
		switch(option) {
		case "Study":
			addPersonWindow.setTextLabel("[Study] Name Study"); // -> changePanel starts inserting a new study
			break;
		case "Language":
			addPersonWindow.setTextLabel("Language"); // -> changePanel starts inserting a new language
			break;
		case "Job":
			addPersonWindow.setTextLabel("[Job] Name Job"); // -> changePanel starts inserting a new job
			break;
		}
		
		addPersonWindow.setEnabled(true);
		addPersonWindow.setVisible(true);
	}
}
