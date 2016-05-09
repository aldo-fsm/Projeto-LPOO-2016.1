package principais;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class InterfaceRestaurante extends JFrame implements ActionListener {
	private JButton loginButton;
	private JButton cadastroButton;
	private JButton loginOkButton;
	private JTextField campoTextoLogin;
	private JPasswordField campoSenhaLogin;
	private CardLayout cL = new CardLayout();
	private JPanel cards = new JPanel(cL);
	
	public InterfaceRestaurante() {
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		
		JPanel telaInicial = new JPanel(null);
		JPanel telaLogin = new JPanel(null);
		JPanel telaCadastro = new JPanel(null);
		
		cards.add(telaInicial,"tela inicial");
		cards.add(telaLogin,"tela de login");
		cards.add(telaCadastro,"tela de cadastro");
		
		add(cards);
	
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		cadastroButton = new JButton("Cadastro");
		loginButton.setBounds(300, 300, 200, 70);
		cadastroButton.setBounds(300, 400, 200, 70);
		telaInicial.add(loginButton);
		telaInicial.add(cadastroButton);
		
		campoTextoLogin = new JTextField();
		campoSenhaLogin = new JPasswordField();
		cadastroButton = new JButton("Cadastro");
		loginOkButton = new JButton("OK");
		JLabel loginNome = new JLabel("Login :");
		JLabel loginSenha = new JLabel("Senha :");
		campoTextoLogin.setBounds(300, 350, 200, 30);
		campoSenhaLogin.setBounds(300, 400, 200, 30);
		loginNome.setBounds(250, 350, 50, 30);
		loginSenha.setBounds(250, 400, 50, 30);
		loginOkButton.setBounds(350, 450, 100, 30);
		telaLogin.add(campoTextoLogin);
		telaLogin.add(campoSenhaLogin);
		telaLogin.add(loginNome);
		telaLogin.add(loginSenha);
		telaLogin.add(loginOkButton);
		
		
		
		
		cL.show(cards, "tela inicial");
	}

	public static void main(String[] args) {
		new InterfaceRestaurante();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(loginButton)){
			
			cL.show(cards, "tela de login");
			
		}
		if(e.getSource().equals(loginOkButton)){
			
		}
		
		
	}
}
