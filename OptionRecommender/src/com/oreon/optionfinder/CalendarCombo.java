package com.oreon.optionfinder;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalendarCombo extends Combo {

	public CalendarCombo(Option buy, Option sell, BigDecimal price) {
		super(buy, sell, price);

	}

	@Override
	public String toString() {
		BigDecimal ratio = new BigDecimal(0);
		try {
			ratio = getBuy().getBid().divide(getSell().getAsk(), RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("Error dividing " + getBuy().getBid() + " " + getSell().getAsk()  );
		}
		// TODO Auto-generated method stub
		return super.toString() + " ratio " + ratio;
	}

}
