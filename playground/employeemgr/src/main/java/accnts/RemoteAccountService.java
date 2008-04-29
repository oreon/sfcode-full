package accnts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteAccountService extends Remote {

  public void insertAccount(Account account) throws RemoteException;
  
  public List getAccounts(String name) throws RemoteException;
}