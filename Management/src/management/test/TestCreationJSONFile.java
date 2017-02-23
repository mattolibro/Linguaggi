package management.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;

public class TestCreationJSONFile {

	private static BufferedReader in;

	public static void main(String[] args) throws IOException {
		int numLines = countLines("files/grammarSample.json");
		int count = 0;

		List<Study> studies = new ArrayList<Study>();
		studies.add(new Study("Computer Science", "Universita Bologna", LocalDate.parse("2011-09-11"), LocalDate.parse("2014-06-18")));
		studies.add(new Study("Master Software Architecture", "Universita Bologna", LocalDate.parse("2014-09-11"), LocalDate.parse("2017-06-18")));
		List<String> languagesKnown = new ArrayList<String>();
		languagesKnown.add("Italian");
		languagesKnown.add("English");
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("Programmer", "Ubisoft", LocalDate.parse("2017-07-20"), null));
		Person personExpected = new Person("ID-000001", "Pippo", "Baudo", LocalDate.parse("1989-07-31"), new Address("via della vittoria, 21", "Bologna", "40100", "Monopoli"), new CV(studies, languagesKnown, jobs));
		System.out.println(personExpected.toString());
		
		Writer writer = null;

		try {
			writer = new BufferedWriter(new FileWriter("files/grammarSampleNew.json"));

			in = new BufferedReader(new FileReader("files/grammarSample.json"));
			String line;
			while((line = in.readLine()) != null) {
				System.out.println(line);
				count++;
				writer.write(line+"\n");
				if (count == numLines-2) {
					System.out.println(personExpected.toString());
					writer.write(personExpected.toString());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) 
				try { 
					writer.close();
					in.close(); 
				} catch (IOException ignore) {}
		}



	}

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

}
