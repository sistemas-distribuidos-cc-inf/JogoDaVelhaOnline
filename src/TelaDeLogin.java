import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaDeLogin implements ActionListener {

	public Player player;	//Jogar que ira logar
	public JogoRMI jogo;	//jogo
	private TelaDeLogin telaLogin;
	
	//Elementos para a interface grafica
	private JFrame jf;
	private Container con;
	private TextField name;
	private JButton register, login;
	private JLabel judge;				//juiz
	
	public TelaDeLogin(JogoRMI jogo) {
		
		this.jogo = jogo;
		this.telaLogin = this;
		
		jf = new JFrame();
		
		con = jf.getContentPane();
		con.setLayout(new GridLayout(5, 1));
		
		JLabel empty1 = new JLabel();
		con.add(empty1);
		
		JPanel panel1 = new JPanel();
		JLabel title = new JLabel("Jogo Da Velha");
		title.setFont(new Font("Courier", Font.BOLD, 30));
		panel1.add(title);
		con.add(panel1);
		
		JPanel panel2 = new JPanel();
		JLabel _name = new JLabel("Nome");
		_name.setFont(new Font("Courier", Font.BOLD, 20));
		panel2.add(_name);
		
		name = new TextField(10);
		panel2.add(name);
		
		register = new JButton("Logar");
		
		panel2.add(register);
		register.addActionListener(this); 
		con.add(panel2);
		
	/*
		JPanel panel3 = new JPanel();
		JLabel remind = new JLabel("Jogador já está inserido no banco de dados do servidor!");
		remind.setFont(new Font("Courier", Font.BOLD, 13));
		remind.setForeground(Color.RED);
		panel3.add(remind);
		con.add(panel3);
		*/
		
		JPanel panel4 = new JPanel();
		login = new JButton("Procurar Jogador");
		login.setFont(new Font("Courier", Font.BOLD, 22));
		panel4.add(login);
		
		login.setEnabled(false);
		login.addActionListener(this); 
		
		judge = new JLabel();
		panel4.add(judge);
		con.add(panel4);

		jf.setTitle("jogo da velha");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 400);
		jf.setLocation(400, 200);
		jf.setResizable(true);
		jf.setVisible(true);

		
		player = new Player();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register) {
			try {
				
				if(name.getText().trim().equals("")||!jogo.checkName(name.getText().trim())) {
					name.setText("");
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/wrong.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
				} else { 
					ImageIcon icon = new ImageIcon(getClass().getResource("/img/correct.jpg"));
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			        judge.setIcon(icon);
			        
			        register.setEnabled(false);
			        name.setEnabled(false);
					login.setEnabled(true);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == login) {
			
			jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        	
	        player.setName(name.getText().trim());
	        System.out.println(player.getName());
	        try {
		        
				jogo.setPlayerInfo(player.getName());
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
	        
			
			new Thread(new Runnable() {
	            
	            public void run() {
	    			login.setText("Jogador aguardando oponente...");
	    			login.setEnabled(false);
	    			ImageIcon icon = new ImageIcon(getClass().getResource("/img/wait.png"));
	    			icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	    	        judge.setIcon(icon);
	    	        
	    	        try {
	    	        	
	    				jogo.buscaOponente(player.getName());
	    				
	    				player = jogo.getPlayer(player.getName());
	    				
	    				
	    				System.out.println("ID do jogador：" + player.getId());
	    				System.out.println("Name do jogador：" + player.getName());
	    				System.out.println("ID do inimigo：" + player.getInimigoId());
	    				System.out.println("Tipo：" + player.getTipo());
	    				System.out.println("Flag：" + player.getFlag());
	    	        	
	    				
	    				jf.setVisible(false);
	    				
	    				TelaDoJogo tela = new TelaDoJogo(telaLogin);
	    				tela.iniciarJogo();
	    				
	    			} catch (RemoteException | InterruptedException e1) {
	    				e1.printStackTrace();
	    			}
	            }
	        }).start();
			
		}
	}

}

