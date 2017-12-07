import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class JogoRMIImpl extends UnicastRemoteObject implements JogoRMI{

	private static final long serialVersionUID = -1374211975249548843L;
	
	private LinkedList<Player> players;				//lista de jogadores no servidor
	private int playerID;							//id do jogador no servidor
	
	protected JogoRMIImpl() throws RemoteException {
		super();
		players = new LinkedList<Player>();
		playerID = 0;
	}
	
	public boolean checkName(String name) {
		int size = players.size();
		for(int i = 0; i < size; i++) {
			if(players.get(i).getName().equals(name)) {
				return false;
			}
		}
		System.out.println("Jogador registrado：" + name);
		return true;
	}
	
	public boolean setPlayerInfo(String name) throws RemoteException {
		Player player = new Player();
		player.setName(name);
		player.setId(++playerID);
		player.setTipo(-1);
		player.setInimigoId(-1);	//a principio sem oponente
		player.setFlag(false);
		int chess[] = new int[10];
		for(int i = 1; i <= 9; i++) chess[i] = -1;
		player.setTabuleiro(chess);
		player.setNewGame(false);
		player.setScore(0);
		player.setExist(true);
		players.add(player);
		return true;
	}
	
	//Retorna posicao do jogador na lista de jogadores do servidor
	public int getIndex(String name) {
		int tam = players.size();
		for(int i = 0; i < tam; i++) {
			if(players.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	//Retorna o jogador com base no seu ID
	public int getIndexById(int id) {
		int tam = players.size();
		for(int i = 0; i < tam; i++) {
			if(players.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public Player getPlayer(String name) throws RemoteException {
		return players.get(getIndex(name));
	}
	
	public void buscaOponente(String name) throws RemoteException, InterruptedException {
		while(true) {
			int index = getIndex(name);	//coleta o index do jogador na lista
			
			if(players.get(index).getInimigoId() != -1) return;	//se ja existir um oponente nao faz nada e sai da funcao(ja existe outro jogador que fez a busca)
			
			int tam = players.size();	//Coleta o tamanho da lista de jogadores
			
			Thread.sleep(500);	//A thread que solicitou busca deve dormir para poupar processamento
			
			for(int i = 0; i < tam; i++) {
				//Caso o jogador achado seja o proprio jogador continuar procurando
				if(i == index) {
					continue;
				} else if(players.get(i).getInimigoId() == -1) {
					// Jogador vai combinar com o oponente e ID do oponente sera setado
					players.get(index).setInimigoId(players.get(i).getId());
					players.get(index).setTipo(2);
					players.get(index).setFlag(false);
					players.get(i).setInimigoId(players.get(index).getId());
					players.get(i).setTipo(1);
					players.get(i).setFlag(true);
//					
					System.out.println("Partida：" + players.get(i).getName() + " Vs " + players.get(index).getName());
					return ;
				}
			}
		}
	}
	
	
	public int[] recebeMovimento(String name) throws RemoteException, InterruptedException {
		int index = getIndex(name);
		while(true) {
			if(players.get(index).getFlag()) {
				return players.get(index).getTabuleiro();
			}
		}
	}
	
	
	public void enviaMovimento(String name, int movimento) throws RemoteException, InterruptedException {
		int index = getIndex(name);
		while(true) {
			if(players.get(index).getFlag()) {
				int chess[] = players.get(index).getTabuleiro();
				chess[movimento] = players.get(index).getTipo();
				players.get(index).setTabuleiro(chess);
				int enemy = getIndexById(players.get(index).getInimigoId());
				players.get(enemy).setTabuleiro(chess);
				// Muda o turno
				players.get(index).setFlag(false);
				players.get(enemy).setFlag(true);
				break;
			}
		}
	}
	
	
	public void enviaNovoJogo(String name) throws RemoteException {
		int index = getIndex(name);
		players.get(index).setNewGame(true);
	}
	
	
	public void mudaPrimeiro(String name) throws RemoteException {
		while(true) {
			int index = getIndex(name);
			int enemyIndex = getIndexById(players.get(index).getInimigoId());
			//Aguardar que outro jogar inicie o jogo
			if(players.get(enemyIndex).getNewGame()) {
				players.get(index).setFlag(false);
				players.get(enemyIndex).setFlag(true);
					
				// Inicializa tabuleiro
				int _chess[] = new int[10];
				for(int i = 1; i <= 9; i++) {
					_chess[i] = -1;
				}
				players.get(index).setTabuleiro(_chess);
				players.get(enemyIndex).setTabuleiro(_chess);
				
				return ;
			}
		}
	}
	
	
	public void unregister(String name) throws RemoteException {
		int index = getIndex(name);
		players.get(index).setExist(false);
		if(players.get(index).getInimigoId() != -1) {
			int enemyIndex = getIndexById(players.get(index).getInimigoId());
			players.get(enemyIndex).setExist(false);
		}
	}
	
	
	public boolean checkExist(String name) throws RemoteException {
		int index = getIndex(name);
		if(players.get(index).getExist()) {
			return true;
		} else {
			players.remove(index);
			return false;
		}
	}
	
}
