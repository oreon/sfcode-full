package accnts;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

public class SimpleObject {

  private static AccountService accountServiceRmi;

  public void setAccountServiceRmi(AccountService accountService) {
    this.accountServiceRmi = accountService;
  }
  
  public static void main(String[] args) {
	  accountServiceRmi = (AccountService) BeanHelper.getBean("accountServiceRmi");
	  if(accountServiceRmi == null ){
		  System.out.println("RMI Server is not running ");
	  }
	  accountServiceRmi.getAccounts("XXX");
	  System.exit(0);
  }
}