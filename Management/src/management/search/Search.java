package management.search;

import java.util.StringTokenizer;

import management.view.windows.PeopleFoundWindow;

public class Search {
	
	public static void search(String request) {
		System.out.println("Request: "+ request +" --> ora cerco la risposta confrontando la mappa con le possibili domande. dammi un attimo");
		
		StringTokenizer st = new StringTokenizer(request);
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(request);
		peopleFoundWindow.setVisible(true);
	}
}
