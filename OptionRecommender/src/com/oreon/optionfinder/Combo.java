package com.oreon.optionfinder;

import java.math.BigDecimal;
import java.util.Comparator;

public class Combo {
	protected Option buy;
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
		return buy + " " + sell + " " + price.setScale(2, BigDecimal.ROUND_UP);
	}
	public BigDecimal getRatio() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Comparator<Combo> getComparator() {
		return new ComboComapartor();
	}
	
	class ComboComapartor implements Comparator<Combo> {

		@Override
		public int compare(Combo c1, Combo c2) {
			int result = c2.getPrice().compareTo(c1.getPrice());
			if (result == 0)
				return c2.getBuy().getPrice().compareTo(c1.getBuy().getPrice());
			return result;
		}

	}
	
	
}



