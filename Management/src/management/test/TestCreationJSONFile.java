package management.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;
import management.utilities.Utilities;

public class TestCreationJSONFile {

	private static BufferedReader in;

	public static void main(String[] args) throws IOException {

		/*----Person data to write in JSON file already created------*/
		List<Study> studies = new ArrayList<Study>();
		studies.add(new Study("Computer Science", "Universita Bologna", LocalDate.parse("2011-09-11"), LocalDate.parse("2014-06-18")));
		studies.add(new Study("Master Software Architecture", "Universita Bologna", LocalDate.parse("2014-09-11"), LocalDate.parse("2017-06-18")));
		List<String> languagesKnown = new ArrayList<String>();
		languagesKnown.add("Italian");
		languagesKnown.add("English");
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("Programmer", "Ubisoft", LocalDate.parse("2017-07-20"), null));
		Person personExpected = new Person("ID-000001", "Pippo", "Baudo", LocalDate.parse("1989-07-31"), new Address("via della vittoria, 21", "Bologna", "40100", "Monopoli"), new CV(studies, languagesKnown, jobs));

		int numLines = Utilities.countLines("files/grammarSample.json");
		int count = 0;

		Writer writer = null;

		/*
		 *The file created is JSON valid  
		 *
		 * try {

			writer = new BufferedWriter(new FileWriter("files/grammarSampleNew.json"));
			writer.write(personExpected.toStringJSON());
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) 
				try { 
					writer.close();
				} catch (IOException ignore) {}
		}*/

		try {
			writer = new BufferedWriter(new FileWriter("files/grammarSampleNew.json"));

			in = new BufferedReader(new FileReader("files/grammarSample.json"));
			String line;
			while((line = in.readLine()) != null) {
				count++;

				if (count == numLines-2) {
					writer.write(line+",\n");
					System.out.println(personExpected.toStringJSON());
					writer.write(personExpected.toStringJSON());
				}
				else {
					System.out.println(line);
					writer.write(line+"\n");
				}
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) 
				try { 
					writer.close();
					in.close(); 
					File file1 = new File("files/grammarSample.json");
					file1.delete();
					File file2 = new File("files/grammarSampleNew.json");
					file2.renameTo(file1);
				} catch (IOException ignore) {}
		}



	}

}
