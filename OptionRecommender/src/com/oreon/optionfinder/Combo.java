package com.oreon.optionfinder;

import java.math.BigDecimal;

public class Combo {
	private Option buy;
	private Option sell;
	
	public Combo(Option buy, Option sell, BigDecimal price) {
		super();
		this.buy = buy;
		this.sell = sell;
		this.price = price;
		price.setScale(2, BigDecimal.ROUND_UP);
	}
	public Option getBuy() {
		return buy;
	}
	public void setBuy(Option buy) {
		this.buy = buy;
	}
	public Option getSell() {
		return sell;
	}
	public void setSell(Option sell) {
		this.sell = sell;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	private BigDecimal price;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return buy.getName() + " " + sell.getName() + " " + price.setScale(2, BigDecimal.ROUND_UP);
	}
}
