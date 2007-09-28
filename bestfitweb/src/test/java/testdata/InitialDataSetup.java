package testdata;

/** 
 * @author jsingh
 *
 */
public class InitialDataSetup {

	public static void main(String args[]){
		TestDataFactory tdf = new TestDataFactory();
		//tdf.persistCustomers();
		//tdf.persistOrders();
		tdf.persistOrderItems();
	}
}
