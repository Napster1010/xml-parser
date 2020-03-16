package parser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XmlParser {

	public static void main(String[] args) throws Exception {
		String xmlFilesDirectory = "C:\\Users\\Napster\\Downloads\\NGB\\NGB";
		File directory = new File(xmlFilesDirectory);
		int exceptionCount = 0;
		File[] allXmlFiles = directory.listFiles();
		
		for(File xmlFile: allXmlFiles) {
			if(xmlFile.isFile()) {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				XmlParserHandler xmlParserHamdler = new XmlParserHandler();
				try {
					parser.parse(xmlFile, xmlParserHamdler);							
				}catch(SAXException exception) {
					System.out.println(exception.getMessage());
					++exceptionCount;
					continue;
				}
				System.out.println();
			}
		}
		System.out.println("\n\nTotal files found in the directory: " + allXmlFiles.length);
		System.out.println("Number of files successfully parsed: " + (allXmlFiles.length - exceptionCount));
		System.out.println("Number of files which couldn't be parsed: " + exceptionCount);		
	}

}
