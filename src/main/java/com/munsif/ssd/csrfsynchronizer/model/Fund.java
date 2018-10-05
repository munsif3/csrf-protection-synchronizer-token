package com.munsif.ssd.csrfsynchronizer.model;

public class Fund {

	private String to;
	private String from;
	private String amount;
	
	public Fund(String to, String from, String amount) {
		super();
		this.to = to;
		this.from = from;
		this.amount = amount;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
