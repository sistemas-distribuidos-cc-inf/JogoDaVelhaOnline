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
	
	
}
