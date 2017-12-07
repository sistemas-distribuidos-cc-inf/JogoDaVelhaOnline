import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TelaDoJogo implements ActionListener {
	
	private int tabuleiro[];
	
	//Elementos da interface Grafica
	private TelaDeLogin telaLogin;
	private JFrame jf;
	private Container con;
	private JButton b[];
	private JLabel _name, name;
	private JLabel _score, myScore, hisScore, vs;
	private JLabel _first, first;
	private JButton newGame;
	private JButton exit;
	private JLabel dtv;
	private JLabel _yourTurn, yourTurn;
	private JLabel win;
	
	public TelaDoJogo(TelaDeLogin telaLogin) throws RemoteException, InterruptedException {
		
		this.telaLogin = telaLogin;
		
		jf = new JFrame();
		
		
		con = jf.getContentPane();
		con.setLayout(null);
		
		_name = new JLabel("Nome：");
		_name.setFont(new Font("Courier", Font.BOLD, 18));
		_name.setForeground(Color.RED);
		_name.setBounds(400, 21, 80, 50);
		jf.add(_name);
		
		name = new JLabel(telaLogin.player.getName());
		name.setFont(new Font("Courier", Font.BOLD, 18));
		name.setBounds(470, 21, 120, 50);
		jf.add(name);
		
		_score = new JLabel("Placar：");
		_score.setForeground(Color.RED);
		_score.setFont(new Font("Courier", Font.BOLD, 18));
		_score.setBounds(400, 60, 120, 50);
		jf.add(_score);
		
		myScore = new JLabel("0");
		myScore.setFont(new Font("Courier", Font.BOLD, 30));
		myScore.setBounds(430, 100, 120, 50);
		jf.add(myScore);
		
		vs = new JLabel(":");
		vs.setFont(new Font("Courier", Font.BOLD, 30));
		vs.setBounds(470, 100, 50, 50);
		jf.add(vs);
		
		hisScore = new JLabel("0");
		hisScore.setFont(new Font("Courier", Font.BOLD, 30));
		hisScore.setBounds(510, 100, 120, 50);
		jf.add(hisScore);
		
		// Mostrar X ou O
		first = new JLabel();
		first.setBounds(450, 180, 200, 50);
		jf.add(first);
				
		newGame = new JButton("Revanche");
		newGame.setFont(new Font("Courier", Font.BOLD, 20));
		newGame.setBounds(390, 240, 180, 30);
		newGame.setEnabled(false);
		newGame.addActionListener(this);
		jf.add(newGame);
				
		exit = new JButton("Sair");
		exit.setFont(new Font("Courier", Font.BOLD, 20));
		exit.setBounds(430, 290, 90, 30);
		exit.addActionListener(this);
		jf.add(exit);
		
		b = new JButton[10];
		for(int i = 1; i <= 9; i++) {
			b[i] = new JButton();
		}
		
		b[1].setBounds(20, 20, 100, 100);
		
		b[1].setBorderPainted(false);
		b[1].setBackground(Color.WHITE);
		jf.add(b[1]);
		
		b[2].setBounds(130, 20, 100, 100);
		b[2].setBorderPainted(false);
		b[2].setBackground(Color.WHITE);
		jf.add(b[2]);
		
		b[3].setBounds(240, 20, 100, 100);
		b[3].setBorderPainted(false);
		b[3].setBackground(Color.WHITE);
		jf.add(b[3]);
		
		b[4].setBounds(20, 130, 100, 100);
		b[4].setBorderPainted(false);
		b[4].setBackground(Color.WHITE);
		jf.add(b[4]);
		
		b[5].setBounds(130, 130, 100, 100);
		b[5].setBorderPainted(false);
		b[5].setBackground(Color.WHITE);
		jf.add(b[5]);
		
		b[6].setBounds(240, 130, 100, 100);
		b[6].setBorderPainted(false);
		b[6].setBackground(Color.WHITE);
		jf.add(b[6]);
		
		b[7].setBounds(20, 240, 100, 100);
		b[7].setBorderPainted(false);
		b[7].setBackground(Color.WHITE);
		jf.add(b[7]);
		
		b[8].setBounds(130, 240, 100, 100);
		b[8].setBorderPainted(false);
		b[8].setBackground(Color.WHITE);
		jf.add(b[8]);
		
		b[9].setBounds(240, 240, 100, 100);
		b[9].setBorderPainted(false);
		b[9].setBackground(Color.WHITE);
		jf.add(b[9]);
		
		// Monitor de eventos
		for(int i = 1; i <= 9; i++) {
			b[i].addActionListener(this);
		}
		
		// Mostrar que jogador esta bloqueado ou autorizado para jogar, dependendo do turno
		_yourTurn = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/lightning.png"));
		icon.setImage(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        _yourTurn.setIcon(icon);
        _yourTurn.setBounds(26, 342, 40, 40);
		jf.add(_yourTurn);
		
		yourTurn = new JLabel();
		yourTurn.setFont(new Font("Courier", Font.BOLD, 18));
		yourTurn.setForeground(Color.RED);
		yourTurn.setBounds(50, 340, 120, 50);
		jf.add(yourTurn);
		
		win = new JLabel();
		win.setFont(new Font("Courier", Font.BOLD, 18));
		win.setForeground(Color.RED);
		win.setBounds(240, 340, 120, 50);
		jf.add(win);
		
		jf.setTitle("Partida");
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(false);
		jf.setVisible(true);
		
		// Usuario fecha a interface antes que o metodo seja implementado
		jf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					// fechar usuario
					telaLogin.jogo.unregister(telaLogin.player.getName());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	// Verifica se o jogador existe
	public void checkIsExist() throws RemoteException {
		if(!telaLogin.jogo.checkExist(telaLogin.player.getName())) {
			System.exit(0);
		}
	}
	
	public void iniciarJogo() throws RemoteException, InterruptedException {
		// Inicializa o jogo na interface
		tabuleiro = new int[10];
		for(int i = 1; i <= 9; i++) tabuleiro[i] = -1;
		for(int i = 1; i <= 9; i++) mudar(b[i]);
		win.setText("");
		newGame.setEnabled(false);
		
		while(true) {
			setNotClickable(); // Bloqueio de click
			checkIsExist();
			telaLogin.player = telaLogin.jogo.getPlayer(telaLogin.player.getName()); // Atualiza jogador
			// Inicialização
			if(telaLogin.player.getFlag()) yourTurn.setText("É o seu turno");
			else yourTurn.setText("Aguarde o turno do outro jogador");
			
			// Inicializa formas e insere
			if(telaLogin.player.getTipo() == 1) {
				ImageIcon icon = new ImageIcon(getClass().getResource("/img/o.png"));
				icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		        first.setIcon(icon);
		        first.setBackground(Color.WHITE);
			} else {
				ImageIcon icon = new ImageIcon(getClass().getResource("/img/x.png"));
				icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		        first.setIcon(icon);
		        first.setBackground(Color.WHITE);
			}
			
			checkIsExist();
			if(telaLogin.player.getFlag()) {
				yourTurn.setText("É o seu turno");
				checkIsExist();
				// Antes de entrar no turno
				telaLogin.player.setTabuleiro(telaLogin.jogo.recebeMovimento(telaLogin.player.getName()));
				tabuleiro = telaLogin.player.getTabuleiro();
				
				int _tabuleiro[] = new int[10];
				for(int i = 1; i <= 9; i++) _tabuleiro[i] = tabuleiro[i];
				
				refresh(); // Atualiza
				
				checkIsExist();
				// Verifica derrota
				if(checkIsLose(telaLogin.player.getTabuleiro(), telaLogin.player.getTipo())) {
					// Perdeu
					newGame.setEnabled(true);
					win.setText("Você perdeu!");
					yourTurn.setText("Jogo finalizado!");
					// Travar
					setNotClickable();
					// Definir pontuação
					int score = Integer.parseInt(hisScore.getText());
					score++;
					hisScore.setText(score + "");
					return ;
				}
				checkIsExist();
				// Determinar sorteio	
				if(checkIsDraw(telaLogin.player.getTabuleiro())) {
					newGame.setEnabled(true);
					win.setText("Desenhe");
					yourTurn.setText("Jogo finalizado!");
					// Travar
					setNotClickable();
					return ;
				}
				
				
				while(true) {
					checkIsExist();
					
					telaLogin.player = telaLogin.jogo.getPlayer(telaLogin.player.getName()); //Atualiza
					if(telaLogin.player.getFlag()) {
						
						//Verifica se pressionou o botão
						setClickable(); 
						int move = 1;
						// clicar em apenas um botão
						while(true) {
							checkIsExist();
							boolean flag = false;
							for(move = 1; move <= 9; move++) {
								if(_tabuleiro[move] != tabuleiro[move]) {
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						setNotClickable(); // Travar

						yourTurn.setText("Vire um ao outro");

						checkIsExist();
						if(checkOK(move)) {
							// Verificar vitoria ou derrota, fazer o julgamento antes de enviar o objeto
							boolean flag = checkIsWin(telaLogin.player.getTabuleiro(), telaLogin.player.getTipo(), move); 
							tabuleiro = telaLogin.jogo.getPlayer(telaLogin.player.getName()).getTabuleiro();
							if(flag) {
								newGame.setEnabled(true);
								win.setText("Você Ganhou!");
								// Atualiza pontuação
								int score = Integer.parseInt(myScore.getText());
								score++;
								myScore.setText(score + "");
								yourTurn.setText("Jogo finalizado!");
								checkIsExist();
								telaLogin.jogo.enviaMovimento(telaLogin.player.getName(), move);
								// Travar
								setNotClickable();
								return ;
							}
							// Determinar sorteio
							if(checkIsDrawMove(tabuleiro, move)) {
								newGame.setEnabled(true);
								win.setText("Desenhe");
								yourTurn.setText("Jogo finalizado!");
								checkIsExist();
								telaLogin.jogo.enviaMovimento(telaLogin.player.getName(), move);
								// Travar
								setNotClickable();
								return ;
							}
							checkIsExist();
							telaLogin.jogo.enviaMovimento(telaLogin.player.getName(), move);
							
							break;
						}
					}
				}
			}
		}
		
	}
	
	public void mudar(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/white.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}
	public void mudarO(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/o.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}
	public void mudarX(JButton button) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/x.png"));
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBackground(Color.WHITE);
	}
	
	// Atualiza dados do jogo
	public void refresh() {
		for(int i = 1; i <= 9; i++) {
			if(tabuleiro[i] == 1) {
				mudarO(b[i]);
			}else if(tabuleiro[i] == 2) {
				mudarX(b[i]);
			}
			for(int j = 1;j <= 9; j++) {
				if(tabuleiro[j] == -1) {
					b[j].setEnabled(true);
				} else {
					b[j].setEnabled(false);
				}
			}
		}
	}
	
	// Travamento e destravamento
	public void setClickable() {
		for(int i = 1;i <= 9; i++) {
			if(tabuleiro[i] == -1) {
				b[i].setEnabled(true);
			} else {
				b[i].setEnabled(false);
			}
		}
	}
	public void setNotClickable() {
		for(int i = 1; i <= 9; i++) {
			b[i].setEnabled(false);
		}
	}
	
	public boolean checkOK(int move) {
		if(telaLogin.player.getTabuleiro()[move] == -1) return true;
		else return false;
	}
	
	// Verifica sorteio
	public boolean checkIsDrawMove(int chess[], int move) {
		for(int i = 1; i <= 9; i++) {
			if(i == move) continue;
			if(chess[i] == -1) return false;
		}
		return true;
	}
	public boolean checkIsDraw(int chess[]) {
		for(int i = 1; i <= 9; i++) {
			if(chess[i] == -1) return false;
		}
		return true;
	}
	
	//Verifica vitoria
	public boolean checkIsWin(int chess[], int type, int move) {
		chess[move] = type; 
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[5]==type&&chess[9]==type)||
		   (chess[3]==type&&chess[5]==type&&chess[7]==type))
			return true;
		else return false;
	}
	
	// Verifica derrota
	public boolean checkIsLose(int chess[], int type) {
		if(type == 1) type = 2;
		else type = 1;
		if((chess[1]==type&&chess[2]==type&&chess[3]==type)||
		   (chess[4]==type&&chess[5]==type&&chess[6]==type)||
		   (chess[7]==type&&chess[8]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[4]==type&&chess[7]==type)||
		   (chess[2]==type&&chess[5]==type&&chess[8]==type)||
		   (chess[3]==type&&chess[6]==type&&chess[9]==type)||
		   (chess[1]==type&&chess[5]==type&&chess[9]==type)||
		   (chess[3]==type&&chess[5]==type&&chess[7]==type))
			return true;
		else return false;
	}
	
	// Monitoramento do jogo
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b[1]) {
				// 1 0 e 2 X
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[1]);
				} else {
					mudarX(b[1]);
				}
				b[1].setEnabled(false);
				tabuleiro[1] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[2]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[2]);
				} else {
					mudarX(b[2]);
				}
				b[2].setEnabled(false);
				tabuleiro[2] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[3]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[3]);
				} else {
					mudarX(b[3]);
				}
				b[3].setEnabled(false);
				tabuleiro[3] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[4]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[4]);
				} else {
					mudarX(b[4]);
				}
				b[4].setEnabled(false);
				tabuleiro[4] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[5]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[5]);
				} else {
					mudarX(b[5]);
				}
				b[5].setEnabled(false);
				tabuleiro[5] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[6]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[6]);
				} else {
					mudarX(b[6]);
				}
				b[6].setEnabled(false);
				tabuleiro[6] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[7]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[7]);
				} else {
					mudarX(b[7]);
				}
				b[7].setEnabled(false);
				tabuleiro[7] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[8]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[8]);
				} else {
					mudarX(b[8]);
				}
				b[8].setEnabled(false);
				tabuleiro[8] = telaLogin.player.getTipo();
			} else if(e.getSource() == b[9]) {
				if(telaLogin.player.getTipo() == 1) {
					mudarO(b[9]);
				} else {
					mudarX(b[9]);
				}
				b[9].setEnabled(false);
				tabuleiro[9] = telaLogin.player.getTipo();
			} else if(e.getSource() == newGame) {
				newGame.setEnabled(false);
				// Precisa inicializar uma thread
				new Thread(new Runnable(){
					@Override
					public void run() {
						
						try {
							checkIsExist();
							telaLogin.jogo.enviaNovoJogo(telaLogin.player.getName());
							checkIsExist();
							//Troca de turnos
							telaLogin.jogo.mudaPrimeiro(telaLogin.player.getName());
							iniciarJogo();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			} else if(e.getSource() == exit) {
				try {
					telaLogin.jogo.unregister(telaLogin.player.getName());
					System.exit(0);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
}
