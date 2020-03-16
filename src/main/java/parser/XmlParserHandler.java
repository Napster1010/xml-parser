package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserHandler extends DefaultHandler {

	private boolean insideD3, insideD300;
	private List<String> b3ParamCodes, b9ParamCodes, b5ParamCodes;
	private boolean b3Read, b5Read, b9Read, insideD1;
	private String meterNumber, meterMakeCode, readingDate, b3Value, b5Value, b9Value;

	// stack to keep track of tags
	private Stack<String> elementStack = new Stack<>();

	public void startDocument() throws SAXException {
		this.insideD1 = false;
		this.insideD3 = false;
		this.insideD300 = false;
		this.b3Read = false;
		this.b5Read = false;
		this.b9Read = false;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elementStack.push(qName);

		if ("D1".equals(qName))
			insideD1 = true;
		if ("D3".equals(qName))
			insideD3 = true;
		if ("D3-00".equals(qName))
			insideD300 = true;

		// read b3, b5 or b9 tags
		if (insideD3 && insideD300) {
			if ("B3".equals(qName) && b3ParamCodes.contains(attributes.getValue("PARAMCODE")) && !this.b3Read) {
				this.b3Value = attributes.getValue("VALUE");
				if(StringUtils.isBlank(this.b3Value))
					throw new SAXException("B3 value is null/blank");

				System.out.println(attributes.getValue("PARAMCODE") + ": " + this.b3Value);
				this.b3Read = true;
			} else if ("B5".equals(qName) && b5ParamCodes.contains(attributes.getValue("PARAMCODE")) && !this.b5Read) {
				this.b5Value = attributes.getValue("VALUE");
				if(StringUtils.isBlank(this.b5Value))
					throw new SAXException("B5 value is null/blank");

				System.out.println(attributes.getValue("PARAMCODE") + ": " + this.b5Value);
				this.b5Read = true;
			} else if ("B9".equals(qName) && b9ParamCodes.contains(attributes.getValue("PARAMCODE")) && !this.b9Read) {
				this.b9Value = attributes.getValue("VALUE");
				if(StringUtils.isBlank(this.b9Value))
					throw new SAXException("B9 value is null/blank");

				System.out.println(attributes.getValue("PARAMCODE") + ": " + this.b9Value);
				this.b9Read = true;
			}
		}
		
		if(insideD1 && "G22".equals(qName)) {
			this.meterMakeCode = attributes.getValue("CODE");
			if(StringUtils.isBlank(this.meterMakeCode))
				throw new SAXException("Meter make code is null/blank");
			System.out.println("Meter make code: " + this.meterMakeCode);			
		}
		
		if("D3-00".equals(qName) ) {
			String dateAttributeValue = attributes.getValue("DATETIME");
			if(StringUtils.isBlank(dateAttributeValue))
				throw new SAXException("Date field value is null/blank");
			
			this.readingDate = dateAttributeValue.split(" ", 2)[0];
			System.out.println("Reading Date: " + this.readingDate);
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("D1".equals(qName))
			insideD1 = false;
		if ("D3".equals(qName))
			insideD3 = false;
		if ("D3-00".equals(qName))
			insideD300 = false;

		elementStack.pop();
	}

	public void characters(char[] ch, int start, int length) throws SAXException{
		String value = new String(ch, start, length);

		if(insideD1) {
			if("G1".equals(currentElement())) {
				if(StringUtils.isBlank(value))
					throw new SAXException("Meter number is null/blank");					

				System.out.println("Meter number: " + value);
				this.meterNumber = value;
			}
		}
	}
	
	public void endDocument() throws SAXException{
	}

	private String currentElement() {
		return this.elementStack.peek();
	}

	public XmlParserHandler() {
		this.b3ParamCodes = new ArrayList<>();
		this.b5ParamCodes = new ArrayList<>();
		this.b9ParamCodes = new ArrayList<>();
		addB3ParamCodes(b3ParamCodes);
		addb5ParamCodes(b5ParamCodes);
		addb9ParamCodes(b9ParamCodes);
	}

	private void addB3ParamCodes(List<String> b3ParamCodes) {
		b3ParamCodes.add("P7-1-5-1-0");
		b3ParamCodes.add("P7-1-5-2-0");
		b3ParamCodes.add("P7-1-13-1-0");
		b3ParamCodes.add("P7-1-13-2-0");
		b3ParamCodes.add("P7-1-18-0-0");
		b3ParamCodes.add("P7-1-18-1-0");
		b3ParamCodes.add("P7-1-18-2-0");
	}

	private void addb5ParamCodes(List<String> b5ParamCodes) {
		b5ParamCodes.add("P7-1-18-0-0");
		b5ParamCodes.add("P7-4-5-1-0");
		b5ParamCodes.add("P7-4-5-2-0");
		b5ParamCodes.add("P7-4-13-2-0");
		b5ParamCodes.add("P7-4-18-1-0");
		b5ParamCodes.add("P7-4-18-2-0");
	}

	private void addb9ParamCodes(List<String> b9ParamCodes) {
		b9ParamCodes.add("P4-4-4-0-0");
		b9ParamCodes.add("P4-4-4-1-0");
	}
}
