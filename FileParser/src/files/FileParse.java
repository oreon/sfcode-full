package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileParse {
	
	public static void main(String[] args) {
		File file = new File("C:\\tmp\\drugcards.txt");
		int cnt = 1;
		
		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(file));
			String line;
			Drug drug = new Drug();
			while((line = br.readLine())  != null){
				line = line.trim();
				if(line.startsWith("#")){
					if(line.contains("Absorption")){
						drug.setAbsorption(br.readLine());
						//System.t.println(cnt++ + " " + drug.getName() + " " +drug.getAbsorption());
					}
					else if (line.contains("Generic_Name")){
						drug.setName(br.readLine());
						System.out.println(cnt++ + " " + drug.getName() + " " +drug.getAbsorption());
					}else if (line.contains("Drug_Type")){
						//drug.setName(br.readLine());
						//System.out.println(cnt++ + " " + drug.getName() + " " +drug.getAbsorption());
					}
				}
			}
			br.close();
			
			//System.out.println(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
