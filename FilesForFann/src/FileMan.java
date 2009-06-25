import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class FileMan {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("apr.dat");
		// System.out.println(System.getProperty("user.dir"));
		try {
			BufferedReader in = new BufferedReader(
					new FileReader("maytest.dat"));
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"mayout.dat"));

			String line = new String();
			int cnt = 0;
			while ((line = in.readLine()) != null) {
				String[] arr = line.split("\\,");
				
				StringBuffer buff = new StringBuffer();
				int arrCnt = 0;
				for (String numStr : arr) {
					if (arrCnt > 1 && arrCnt < 6) {
						double num = Double.parseDouble(numStr);
						if (num < 10) {
							num = num / 10;
							num = round (num, 5);
						} else
							num = num / 100000;

						buff.append(num + " ");
					}
					arrCnt++;
				}
				out.write(buff + "\r\n");
				if (cnt > 0 ) {
					out.write(buff + "\r\n");
				}
				cnt++;

			}
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	public static double round(double d, int decimalPlace) {
		// see the Javadoc about why we use a String in the constructor
		// http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

}
