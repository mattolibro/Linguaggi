package management.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.*;

import management.model.PeopleJsonFactory;
import management.model.Person;

import javax.json.*;

public class TestJSON {

	@Test
	public void testJSONFormat() {
		File file = new File("files/grammarSample.txt");
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			JsonReader rdr = Json.createReader(fis);
			JsonObject obj =  rdr.readObject();
			JsonArray results = obj.getJsonArray("People");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				//System.out.println(result.getJsonObject("Person").getString("ID"));
				PeopleJsonFactory people = new PeopleJsonFactory(result.getJsonObject("Person"));
				Person p = people.createPerson();
				System.out.println(p.toString());

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
}
