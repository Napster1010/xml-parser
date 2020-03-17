package dto;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class ReadMasterPF {
	@Expose
	private BigDecimal meterPf;
	
	@Expose
	private BigDecimal billingPf;

	public ReadMasterPF(BigDecimal meterPf, BigDecimal billingPf) {
		super();
		this.meterPf = meterPf;
		this.billingPf = billingPf;
	}

	public BigDecimal getMeterPf() {
		return meterPf;
	}

	public BigDecimal getBillingPf() {
		return billingPf;
	}		

}
