package com.travix.medusa.busyflights.enums.suppliers;

public enum Supplier {

	CrazyAir ("CrazyAir"),
	ToughJet ("ToughJet");
	
	private String supplierCode;

	private Supplier(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}
	
}
