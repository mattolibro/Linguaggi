package management.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import management.search.Search;
import management.view.windows.ManagementWindowApp;
import management.view.windows.SearchWindow;

public class SearchListener implements ActionListener {

	private SearchWindow searchWindow;
	private Search search;
	
	public SearchListener(SearchWindow searchWindow) {
		this.searchWindow = searchWindow;
		this.search = new Search(ManagementWindowApp.getPeople());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SwingWorker<String, Void> myWorker= new SwingWorker<String, Void>() {
		    @Override
		    protected String doInBackground() throws Exception {
		    	search.search(searchWindow.getText());
				searchWindow.refreshTextField();
		        return null;
		    }
		};
		myWorker.execute();
		
	}

}
