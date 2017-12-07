import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class JogoServidor {
	public static void main(String[] args) {
		
		try {
			
			JogoRMI jogo = new JogoRMIImpl();
			
			LocateRegistry.createRegistry(8888);

			Naming.bind("rmi://localhost:8888/Jogo", jogo);

			System.out.println("Servidor pronto e aguardando jogadores...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
