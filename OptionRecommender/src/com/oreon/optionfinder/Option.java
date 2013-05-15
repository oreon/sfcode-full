package com.oreon.optionfinder;

import java.math.BigDecimal;

public class Option {
	String name;
	BigDecimal bid;
	BigDecimal ask;
	Integer openInt;
	Integer vol;
	private BigDecimal price;
	private String month;
	private OptionType optionType;
	
	public Option(String name, BigDecimal bid, BigDecimal ask, Integer openInt,
			Integer vol, BigDecimal price, String month, OptionType optionType) {
		super();
		this.name = name;
		this.bid = bid;
		this.ask = ask;
		this.openInt = openInt;
		this.vol = vol;
		this.price = price;
		this.setMonth(month);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public Integer getOpenInt() {
		return openInt;
	}

	public void setOpenInt(Integer openInt) {
		this.openInt = openInt;
	}

	public Integer getVol() {
		return vol;
	}

	public void setVol(Integer vol) {
		this.vol = vol;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}
	
	public String getExpiry(){
		//System.out.println(name.substring(1, 8));
		return name.substring(1, 8);
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}

	public OptionType getOptionType() {
		return optionType;
	}

}

enum OptionType {
	C,
	P
}
