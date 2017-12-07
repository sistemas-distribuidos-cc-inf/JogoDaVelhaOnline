import java.io.Serializable;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 2180605647710675847L;

	private int id;				//id necessario para identificar jogador no servidor
	private String name;		//Nome do jogador no servidor
	private int tipo;			//Se o jogador usa o simbolo O ou X
	private int inimigoID;		//id do inimigo
	private int tabuleiro[];	//tabuleiro do jogo
	private int flag;			//situacao do jogador
	private boolean newGame;	//saber se o jogador esta em um novo jogo
	private int score;			//placar do jogador
	private boolean exist;		//saber se o jogador existe ou nao
	
	public Player() {
		super();
	}
}
