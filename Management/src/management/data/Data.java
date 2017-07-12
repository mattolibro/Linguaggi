package management.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.TreeSet;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import management.model.Job;
import management.model.PeopleJsonFactory;
import management.model.Person;
import management.model.Study;
import management.utilities.Utilities;
import management.view.windows.ManagementWindowApp;

public class Data {

	private static File file;
	private static HashMap<String, Person> people; // it's useful having a map for the people who are in the JSONFile, especially for the searching feature

	/**-- These maps are going to be used for the searching. Perhaps all this part needs to be moved somewhere else  --**
	 **-- They are populated by the fields as keys and the IDs of the people as values --------------------------------**/

	private static HashMap<String, TreeSet<String>> studies; // Key: NameStudy, Value: Person's ID
	private static HashMap<String, TreeSet<String>> jobs;	 // Key: NameJob, Value: Person's ID
	
	/**-- Map with all the maps, it stores as key the keyword(String) and as value the map --**/
	private static HashMap<String, HashMap<String, TreeSet<String>>> mapSearches; 

	public static void initialize() {
		/** Maps initialization **/
		people = new HashMap<String, Person>();
		studies = new HashMap<String, TreeSet<String>>();
		jobs = new HashMap<String, TreeSet<String>>();
		
		mapSearches = new HashMap<String, HashMap<String, TreeSet<String>>>();
		mapSearches.put("study", studies);
		mapSearches.put("job", jobs);
		

		file = new File("files/grammarSample.json");

		/* Custom parser for the people grammar */
		InputStream fis = null;
		try {

			fis = new FileInputStream(file);
			JsonReader rdr = Json.createReader(fis);
			JsonObject obj =  rdr.readObject();
			JsonArray results = obj.getJsonArray("People");

			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				PeopleJsonFactory peopleJSonFactory = new PeopleJsonFactory(result.getJsonObject("Person"));
				Person person = peopleJSonFactory.createPerson(); // it gives the actual person parsed from the JSONFile
				people.put(person.getID(), person); // it fills the map with ID and Person
				
				/*In this part all the maps are filled. It's useful for the searching mode */

				for (Study study : person.getCv().getStudies()) { // It iterates all the studies of the person 
					String studyField = study.getNameStudy().toLowerCase();
					if(studies.containsKey(studyField)) {
						studies.get(studyField).add(person.getID());
					}
					else {
						studies.put(studyField, new TreeSet<String>());
						studies.get(studyField).add(person.getID());
					}
				}

				for (Job job : person.getCv().getJobs()) { // It iterates all the jobs of the person 
					String jobField = job.getNameJob().toLowerCase();
					if(jobs.containsKey(jobField)) {
						jobs.get(jobField).add(person.getID());
					}
					else {
						jobs.put(jobField, new TreeSet<String>());
						jobs.get(jobField).add(person.getID());
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void addPerson(Person person) {
		people.put(person.getID(), person);
		updateJSONFile(person); // updates the JSONFile
		ManagementWindowApp.textFieldUpdate(person.toString());
	}

	public static HashMap<String, Person> getPeople() {
		return people;
	}
	
	public static HashMap<String, HashMap<String, TreeSet<String>>> getMapSearches() {
		return mapSearches;
	}

	public static File getFile() {
		return file;
	}
	
	private static void updateJSONFile(Person person) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;
			int numLines = Utilities.countLines(file.getPath());
			String textFile = "";

			while((line = in.readLine()) != null) {
				count++;

				if (count == numLines-2) {
					textFile +=line+",\n"+ person.toStringJSON();
				}
				else {
					textFile += line+"\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			writer.write(textFile);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) 
				try { 
					in.close(); 

				} catch (IOException ignore) {}
		}
	}

}
