package com.travix.medusa.busyflights.enums.cabinClass;

public enum CabinClass {
	Economy ("E"),
	Business ("B");
	
	private String cabinClassCode;
	
	private CabinClass(String cabinClassCode) {
		this.cabinClassCode = cabinClassCode;
	}

	public String getCabinClassCode() {
		return cabinClassCode;
	}
	
	

}
