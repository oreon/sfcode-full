package com.oreon.optionfinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OptionFinder {

	static ArrayList<Option> options = new ArrayList<Option>();

	static final int PUT_OFFSET = 10;
	static final int CALL_OFFSET = 0;

	public static void main(String[] args) throws Exception {
		FileTransformer.transformFile();
		createOptions(CALL_OFFSET);
		createOptions(PUT_OFFSET);

		//verticalFinder();
		calendarFinder();

	}

	private static void calendarFinder() {
		 ArrayList<Combo> combos = new ArrayList<Combo>();
	
		for (int i = 0; i < options.size(); i++) {
			// if(i > options.size() - 3 )break;
			Option current = options.get(i);
			Option nextMonth = findOptionByStrikeAndMonth(current.getPrice() , current.getMonthIndex() + 1, current.getOptionType());
			
			if (nextMonth != null){
				CalendarCombo combo = new CalendarCombo(current, nextMonth, current.getBid().subtract(nextMonth.getAsk()) );
				combos.add(combo);
			}
			
		}
		
		sortAndPrint(combos);
	}

	private static Option findOptionByStrikeAndMonth(BigDecimal price,
			int month, OptionType optionType) {

		for (Option option : options) {
			if (option.getMonthIndex() == month
					&& price.equals(option.getPrice())
					&& option.getOptionType() == optionType)
				return option;
		}
		return null;
	}

	private static void verticalFinder() {

		 ArrayList<Combo> combos = new ArrayList<Combo>();

		for (int i = 0; i < options.size(); i++) {
			// if(i > options.size() - 3 )break;
			Option current = options.get(i);
			// System.out.println(current.getName() + " " );

			for (int j = 1; j < 6; j++) {
				try {

					Option next = options.get(i + j);
					if (next.getExpiry().equals(current.getExpiry())) {

						Combo combo = new Combo(current, next, current.getBid()
								.subtract(next.getAsk()).divide(
										new BigDecimal(j)));
						combos.add(combo);

						if (current.getOptionType() == OptionType.P) {

						}
					}
				} catch (Exception ioe) {
					System.out.print(ioe.getMessage());
				}
			}

		}

		sortAndPrint(combos);
	}

	private static void sortAndPrint(ArrayList<Combo> combos) {
		Collections.sort(combos, new ComboComapartor());

		for (Combo combo : combos) {
			System.out.println(combo);
		}
	}

	private static void createOptions(int offset) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("trsfrm.dat"));

		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			String[] arr = currentLine.split(" |\t|,");
			
			Option option =   null;
			
			try {
			option = new Option(arr[3 + offset], new BigDecimal(
					arr[6 + offset]), new BigDecimal(arr[7 + offset]), Integer
					.parseInt(arr[8 + offset]), Integer
					.parseInt(arr[9 + offset]),
					new BigDecimal(arr[2 + offset]), arr[1],
					offset == CALL_OFFSET ? OptionType.C : OptionType.P);
			}catch(Exception e){
				System.out.print(e.getMessage());
				continue;
			}

			if (!option.getName().startsWith("(SPY13"))
				continue;

			// TODO comparison with F should be exact month
			if (offset == CALL_OFFSET
					&& option.getPrice().compareTo(new BigDecimal(171)) > 0
					/*&& option.getMonth().equalsIgnoreCase("Jun")
					 */)
				options.add(option);

			if (offset == PUT_OFFSET
					&& option.getPrice().compareTo(new BigDecimal(155)) < 0
					/*&& option.getMonth().equalsIgnoreCase("Jun")
					 */)
				options.add(option);

		}

		br.close();

	}

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

/*
 * options.add(new Option("165" , new BigDecimal(.90) , new BigDecimal(0.87) )
 * ); options.add(new Option("166" , new BigDecimal(.80) , new BigDecimal(0.77)
 * ) ); options.add(new Option("167" , new BigDecimal(.71) , new
 * BigDecimal(0.69) ) ); options.add(new Option("168" , new BigDecimal(.60) ,
 * new BigDecimal(0.58) ) ); options.add(new Option("169" , new BigDecimal(.53)
 * , new BigDecimal(0.51) ) ); options.add(new Option("170" , new
 * BigDecimal(.46) , new BigDecimal(0.41) ) );
 */
