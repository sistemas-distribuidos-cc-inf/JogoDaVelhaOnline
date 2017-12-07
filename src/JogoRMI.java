import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoRMI extends Remote{

	//Verifica se o jogar logado ja existe no servidor
	public boolean checkName(String name) throws RemoteException;
	
	//Setar as informacoes do jogador no servidor
	public boolean setPlayerInfo(String name) throws RemoteException;
	
	//Retorna um jogador do servidor
	public Player getPlayer(String name) throws RemoteException;
	
	//Procura por oponente no servidor
	public void buscaOponente(String name) throws RemoteException, InterruptedException;
	
	//Receber movimento de um jogador para outro jogador, um novo tabuleiro e setado com as novas posicoes
	public int[] recebeMovimento(String name) throws RemoteException, InterruptedException;
	
	//Envia o movimento de um jogador para outro jogador, envia o tabulerio com os novos movimentos
	public void enviaMovimento(String name, int move) throws RemoteException, InterruptedException;
	
	//Realiza as primeiras mudancas antes de comecar um novo jogo
	public void mudaPrimeiro(String name) throws RemoteException;
	
	//Faz o jogador entrar em um novo jogo (Revanche)
	public void enviaNovoJogo(String name) throws RemoteException;
	
	//Realiza a saida dos dois jogadores
	public void unregister(String name) throws RemoteException;
	
	//Verifica se um jogador "existe" (se esta em jogo)
	public boolean checkExist(String name) throws RemoteException;
}
