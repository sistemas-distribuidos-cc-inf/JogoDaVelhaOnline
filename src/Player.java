import java.io.Serializable;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 2180605647710675847L;

	private int id;				//id necessario para identificar jogador no servidor
	private String name;		//Nome do jogador no servidor
	private int tipo;			//Se o jogador usa o simbolo O ou X
	private int inimigoID;		//id do inimigo
	private int tabuleiro[];	//tabuleiro do jogo
	private boolean flag;		//situacao do jogador (turno)
	private boolean newGame;	//saber se o jogador esta em um novo jogo
	private int score;			//placar do jogador
	private boolean exist;		//saber se o jogador existe ou nao
	
	public Player() {
		super();
	}
	
	public boolean getExist() {
		return exist;
	}
	
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean getNewGame() {
		return newGame;
	}
	
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public int[] getTabuleiro() {
		return tabuleiro;
	}
	
	public void setTabuleiro(int tabuleiro[]) {
		this.tabuleiro = tabuleiro;
	}
	
	public int getInimigoId() {
		return inimigoID;
	}
	
	public void setInimigoId(int inimigoID) {
		this.inimigoID = inimigoID;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo= tipo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
