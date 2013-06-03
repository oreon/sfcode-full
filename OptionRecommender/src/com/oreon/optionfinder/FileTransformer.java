package com.oreon.optionfinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileTransformer {

	static  ArrayList<Option> options = new ArrayList<Option>();
	
	public static void main(String[] args) throws Exception {
		transformFile();
		
		
		
		
	}

	public static void transformFile() throws FileNotFoundException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\t900058\\Downloads\\QuoteData (3).dat"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("trsfrm.dat"));
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			
			String[] arr = currentLine.split(" ");
			
			String option = arr[3];
			
			Double val = 0.0;
			try{
			 val =  Double.parseDouble(arr[2]);
			}catch(NumberFormatException nfe){
				continue;
			}
			
			if(arr[0].equals("13") && val > 145 && val < 175 && option.indexOf("-") < 0 )
				bw.write(currentLine + "\r\n");
		}
		 
		br.close();
		bw.close();
	}
}
