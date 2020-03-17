package dto;

public class ParsedXmlData {
	private String meterNumber;
	
	private String meterMakeCode;
	
	private String readingDate;
	
	private String b3Value;
	
	private String b5Value;
	
	private String b9Value;

	public String getMeterNumber() {
		return meterNumber;
	}

	public String getMeterMakeCode() {
		return meterMakeCode;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public String getB3Value() {
		return b3Value;
	}

	public String getB5Value() {
		return b5Value;
	}

	public String getB9Value() {
		return b9Value;
	}

	public ParsedXmlData(String meterNumber, String meterMakeCode, String readingDate, String b3Value, String b5Value,
			String b9Value) {
		super();
		this.meterNumber = meterNumber;
		this.meterMakeCode = meterMakeCode;
		this.readingDate = readingDate;
		this.b3Value = b3Value;
		this.b5Value = b5Value;
		this.b9Value = b9Value;
	}	
}
