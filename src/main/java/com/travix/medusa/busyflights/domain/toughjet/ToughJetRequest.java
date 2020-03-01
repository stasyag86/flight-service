package com.travix.medusa.busyflights.domain.toughjet;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ToughJetRequest {

	@Size(min = 3, max = 3)
    private String from;
    
	@Size(min = 3, max = 3)
    private String to;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String outboundDate;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String inboundDate;
    
    private int numberOfAdults;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(final String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(final String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }
}
