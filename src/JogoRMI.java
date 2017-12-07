import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoRMI extends Remote{

	public boolean checkName(String name) throws RemoteException;
	public boolean setPlayerInfo(String name) throws RemoteException;
	public Player getPlayer(String name) throws RemoteException;
	public void buscaOponente(String name) throws RemoteException, InterruptedException;
	public int[] recebeMovimento(String name) throws RemoteException, InterruptedException;
	public void enviaMovimento(String name, int move) throws RemoteException, InterruptedException;
	public void mudaPrimeiro(String name) throws RemoteException;
	public void enviaNovoJogo(String name) throws RemoteException;
	public void unregister(String name) throws RemoteException;
	public boolean checkExist(String name) throws RemoteException;
}
