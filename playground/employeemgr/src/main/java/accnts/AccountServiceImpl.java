package accnts;

import java.util.ArrayList;
import java.util.List;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

public class AccountServiceImpl implements AccountService {

  public void insertAccount(Account acc) {
    // do something...
  }
  
  public List getAccounts(String name) {
    System.out.println("get accounts called for " + name);  
    return new ArrayList<String>();
  }
  
  public static void main(String[] args) {
	  BeanHelper.getBean("accountService");
	  while(true){
		  try {
			Thread.sleep(10000);
			System.out.println("RMI Server waiting ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }
}