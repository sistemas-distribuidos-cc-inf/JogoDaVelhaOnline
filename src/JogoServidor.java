import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class JogoServidor {
	public static void main(String[] args) {
		
		try {
			
			JogoRMI jogo = new JogoRMIImpl();
			
			LocateRegistry.createRegistry(8888);

			Naming.bind("rmi://localhost:8888/Jogo", jogo);

			System.out.println("Servidor executando");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Aguardando novos jogadores...");
	}
}
