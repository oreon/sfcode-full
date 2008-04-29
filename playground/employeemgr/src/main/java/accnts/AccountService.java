package accnts;

import java.util.List;

public interface AccountService {

  public void insertAccount(Account account);
  
  public List getAccounts(String name);
}