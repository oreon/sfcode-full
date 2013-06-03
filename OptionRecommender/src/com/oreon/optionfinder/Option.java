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

	private int monthIndex;

	public void setMonthIndex(int monthIndex) {
		this.monthIndex = monthIndex;
	}

	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec", };

	public Option(String name, BigDecimal bid, BigDecimal ask, Integer openInt,
			Integer vol, BigDecimal price, String month, OptionType optionType) {
		super();
		this.name = name;
		this.bid = bid;
		this.ask = ask;
		this.openInt = openInt;
		this.vol = vol;
		this.price = price;
		this.optionType = optionType;
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
		for (int i = 0; i < months.length; i++){
			if (months[i].equalsIgnoreCase(month)) {
				monthIndex = i;
			}
		}
	}

	public String getMonth() {
		return month;
	}

	public int getMonthIndex() {
		return monthIndex;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + month + " " + optionType;
	}

	public String getExpiry() {
		// System.out.println(name.substring(1, 8));
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
	C, P
}
