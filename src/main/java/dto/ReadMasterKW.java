package dto;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class ReadMasterKW {
	@Expose
	private BigDecimal meterMd;
	
	@Expose
	private BigDecimal multipliedMd;
	
	@Expose
	private BigDecimal billingDemand;

	public ReadMasterKW(BigDecimal meterMd, BigDecimal multipliedMd, BigDecimal billingDemand) {
		super();
		this.meterMd = meterMd;
		this.multipliedMd = multipliedMd;
		this.billingDemand = billingDemand;
	}

	public BigDecimal getMeterMd() {
		return meterMd;
	}

	public BigDecimal getMultipliedMd() {
		return multipliedMd;
	}

	public BigDecimal getBillingDemand() {
		return billingDemand;
	}		

}
