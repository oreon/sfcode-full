package com.oreon.cerebrum.web.action.patient;

import java.util.Date;

public class Birth{
		public Birth(Date year, int numBoys, int numGirls) {
			super();
			this.year = year;
			this.boys = numBoys;
			this.girls = numGirls;
		}
		public Date getYear() {
			return year;
		}
		public void setYear(Date year) {
			this.year = year;
		}
		public int getBoys() {
			return boys;
		}
		public void setBoys(int boys) {
			this.boys = boys;
		}
		public int getGirls() {
			return girls;
		}
		public void setGirls(int girls) {
			this.girls = girls;
		}
		Date year;
		int boys;
		int girls;
		
	}