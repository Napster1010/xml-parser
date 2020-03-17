package dto;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class Read {
	@Expose
	private String consumerNo;
	
	@Expose
	private String meterIdentifier;
	
	@Expose
	private String readingDate;
	
	@Expose
	private String readingType;
	
	@Expose
	private String meterStatus;
	
	@Expose
	private String replacementFlag;
	
	@Expose
	private String source;
	
	@Expose
	private BigDecimal reading;
	
	@Expose
	private BigDecimal difference;
	
	@Expose
	private BigDecimal mf;
	
	@Expose
	private BigDecimal consumption;
	
	@Expose
	private BigDecimal assessment;
	
	@Expose
	private BigDecimal propogatedAssessment;
	
	@Expose
	private BigDecimal totalConsumption;
	
	@Expose
	private boolean usedOnBill;
	
	@Expose
	private ReadMasterKW readMasterKW;
	
	@Expose
	private ReadMasterPF readMasterPF;

	public Read(String consumerNo, String meterIdentifier, String readingDate, String readingType, String meterStatus,
			String replacementFlag, String source, BigDecimal reading, BigDecimal difference, BigDecimal mf,
			BigDecimal consumption, BigDecimal assessment, BigDecimal propogatedAssessment, BigDecimal totalConsumption,
			boolean usedOnBill, ReadMasterKW readMasterKW, ReadMasterPF readMasterPF) {
		super();
		this.consumerNo = consumerNo;
		this.meterIdentifier = meterIdentifier;
		this.readingDate = readingDate;
		this.readingType = readingType;
		this.meterStatus = meterStatus;
		this.replacementFlag = replacementFlag;
		this.source = source;
		this.reading = reading;
		this.difference = difference;
		this.mf = mf;
		this.consumption = consumption;
		this.assessment = assessment;
		this.propogatedAssessment = propogatedAssessment;
		this.totalConsumption = totalConsumption;
		this.usedOnBill = usedOnBill;
		this.readMasterKW = readMasterKW;
		this.readMasterPF = readMasterPF;
	}

	public String getConsumerNo() {
		return consumerNo;
	}

	public String getMeterIdentifier() {
		return meterIdentifier;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public String getReadingType() {
		return readingType;
	}

	public String getMeterStatus() {
		return meterStatus;
	}

	public String getReplacementFlag() {
		return replacementFlag;
	}

	public String getSource() {
		return source;
	}

	public BigDecimal getReading() {
		return reading;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public BigDecimal getMf() {
		return mf;
	}

	public BigDecimal getConsumption() {
		return consumption;
	}

	public BigDecimal getAssessment() {
		return assessment;
	}

	public BigDecimal getPropogatedAssessment() {
		return propogatedAssessment;
	}

	public BigDecimal getTotalConsumption() {
		return totalConsumption;
	}

	public boolean isUsedOnBill() {
		return usedOnBill;
	}

	public ReadMasterKW getReadMasterKW() {
		return readMasterKW;
	}

	public ReadMasterPF getReadMasterPF() {
		return readMasterPF;
	}	

}
