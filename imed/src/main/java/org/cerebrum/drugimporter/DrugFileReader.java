package org.cerebrum.drugimporter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class DrugFileReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String fileName = "C:\\devtools\\drugcards.txt";
		String outPutFile = "c:\\dev\\data\\outputFile.txt";
		String dataOutPutFile = "c:\\dev\\data\\dataOutputFile.txt";
		final String recordDelim = ",#$#@";
		final String fieldDelim = ",#$@";
		final String noData = "Not Available";
		final String noFieldData = "\t";
		
		String  assembleField;  
		String drugRecord = "";

		BufferedWriter bw;
		BufferedWriter bwData;
		BufferedReader br;

		String line;
		String[] Tags = { "#BEGIN_DRUGCARD", "#END_DRUGCARD", "Drug_Type",
				"ATC_Codes", "Brand_Names", "Description", "Generic_Name",
				"Half_Life", "Pharmacology", "Drug_Target_1_General_Function" };

		try {
			bw = new BufferedWriter(new FileWriter(outPutFile));
			bwData = new BufferedWriter(new FileWriter(dataOutPutFile));
			br = new BufferedReader(new FileReader(fileName));
			
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") && containsTag(line, Tags)) {
					System.out.println(line);
					bw.write(line);
					bw.newLine();

					if (line.startsWith(Tags[0])) {   // start of a drug record
						drugRecord = line.substring(line.indexOf(" ")) +fieldDelim;
					} else if (line.startsWith(Tags[1])) {  // end of a drug record
						bwData.write(drugRecord + recordDelim);
						bwData.newLine();
						drugRecord = null;
					} else
					{
						assembleField = "" ; 
						do {
							line = br.readLine();
							System.out.println(line);
							bw.write(line);
							bw.newLine();
							assembleField += line;

						} while (line != null && line.trim().length() != 0);
						
						if (assembleField.contains(noData))
							drugRecord += noFieldData+fieldDelim;
						else
							drugRecord += assembleField+fieldDelim;
						
						
					}
				}
			}
			bwData.close();
			bw.close();
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File [" + fileName + "] Not found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static boolean containsTag(String line, String[] Tags) {
		boolean result = false;
		for (int i = 0; i < Tags.length; i++) {
			result = result || line.contains(Tags[i]);

		}
		return result;

	}

}
