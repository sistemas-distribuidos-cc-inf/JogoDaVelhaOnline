import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteJogo {
	
private JogoRMI jogo;
	
	public ClienteJogo() throws InterruptedException {
		try {
			// Retorna o objeto remotamente
			jogo = (JogoRMI)Naming.lookup("rmi://localhost:8888/Jogo"); 
			new TelaDeLogin(jogo);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	// Cliente entra no jogo
	public static void main(String[] args) throws InterruptedException {
		new ClienteJogo();
	}

}
