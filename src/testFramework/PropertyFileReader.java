package testFramework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyFileReader {
    BufferedReader br = null;
	FileReader fr = null;
	Map<String,String> propertyMap = new HashMap<String, String>();
	public Map<String,String> readPropertyFile(){
	try {	
		final String FILENAME = "src/resources/configuration/properties";
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
		 String propertyName= sCurrentLine.substring(0,sCurrentLine.indexOf(":"));
		 String propertyValue = sCurrentLine.substring(sCurrentLine.indexOf(":") + 1 ,sCurrentLine.length());
         propertyMap .put(propertyName, propertyValue);
      }

} catch (IOException e) {

	e.printStackTrace();

} finally {

	try {

		if (br != null)
			br.close();

		if (fr != null)
			fr.close();

	} catch (IOException ex) {

		ex.printStackTrace();

	}

    }
	
	return propertyMap;
	}
}



