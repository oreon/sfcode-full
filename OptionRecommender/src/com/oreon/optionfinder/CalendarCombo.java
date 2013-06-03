package com.oreon.optionfinder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class CalendarCombo extends Combo {

	public CalendarCombo(Option buy, Option sell, BigDecimal price) {
		super(buy, sell, price);

	}

	@Override
	public Comparator<Combo> getComparator() {
		// TODO Auto-generated method stub
		return new CalendarComapartor();
	}

	@Override
	public BigDecimal getRatio() {
		BigDecimal ratio = getBuy().getBid().divide(getSell().getAsk(),
				RoundingMode.HALF_UP);
		return ratio;
	}

	@Override
	public String toString() {

		return super.toString() + " ratio " + getRatio();
	}

	class CalendarComapartor implements Comparator<Combo> {

		@Override
		public int compare(Combo c1, Combo c2) {
			int result = c2.getBuy().getOptionType().compareTo(
					c1.getBuy().getOptionType());
			
			if (result == 0) {
				result = c2.getBuy().getName().compareTo(c1.getBuy().getName());
			}else{
				return result;
			}
			
			if (result == 0) {
				result = c2.getRatio().compareTo(c1.getRatio());
			}else{
				return result;
			}
			if (result == 0)
				result = c2.getBuy().getPrice().compareTo(
						c1.getBuy().getPrice());
			return result;
		}

	}

}
