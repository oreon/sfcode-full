package org.witchcraft.model.datafactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.witchcraft.model.data.location.Country;

/**
 * Creates a list of countries from countries.txt file in resources
 * @author jsingh
 *
 */
public class CountryDataFactory implements DataFactory<Country> {

	public Country createObject() {
		// TODO Auto-generated method stub
		//new PreferencesFactory().
		return null;
		
	}

	public List<Country> createList() {
		InputStream stream = CountryDataFactory.class.getResourceAsStream("/countries.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		List<Country> countries = new ArrayList<Country>();
		
		String line;
		try {
			while((line = reader.readLine()) != null){
				String[] arr = line.split("(  )+");
				countries.add(new Country(arr[0].trim(), arr[1].trim(), Integer.parseInt(arr[3].trim()) )  );
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Countries " + countries.size());
		
		return countries;
	}
	
	public static void main(String args[]){
		
	}

}
