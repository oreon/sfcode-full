package org.witchcraft.model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.witchcraft.model.support.errorhandling.CriticalException;

/**
 * Utility class for various file related operations
 * 
 * @author jsingh
 * 
 */
public class FileUtils {

	public static List<String> getLinesFromFile(String fileName) {
		BufferedReader reader = getBufferedReaderForFile(fileName);
		
		List<String> lines = new ArrayList<String>();

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new CriticalException("File " + fileName
					+ " not found ", e);
		}

		return lines;
	}

	public static String getFileDataAsString(String fileName) {
		BufferedReader reader = getBufferedReaderForFile(fileName);
		StringBuffer buffer = new StringBuffer();
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new CriticalException("File " + fileName
					+ " not found ", e);
		}

		return buffer.toString();
	}

	private static BufferedReader getBufferedReaderForFile(String fileName) {
		
		InputStream stream = FileUtils.class.getResourceAsStream(fileName);
		
		if(stream == null)
		throw new CriticalException("File :" + fileName
				+ " not found in the class path");
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		return reader;
	}

}
