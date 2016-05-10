package principais;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import entidades.Cliente;
import entidades.Gerente;

public class InterfaceCliente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField campoCadastroNome = new JTextField();
	private CardLayout cL = new CardLayout();
	private JPanel cards = new JPanel(cL);
	private JButton logoutButton;
	private JButton OkButtonLogin = new JButton("OK");
	private JButton OkButtonCadastro = new JButton("OK");
	private JButton opcaoLogin = new JButton("Fazer Login");
	private JButton opcaoCadastro = new JButton("Cadastrar");
	private JTextField campoLogin = new JTextField();
	private JPasswordField campoSenhaLogin = new JPasswordField();
	private JTextField campoCadastroLogin = new JTextField();
	private JPasswordField campoCadastroSenha = new JPasswordField();
	private Gerente gerente;

	public static void main(String[] args) {
		Gerente novo = new Gerente();
		InterfaceCliente telaCliente = new InterfaceCliente(novo);
		telaCliente.janelas();
	}

	public InterfaceCliente(Gerente gerente) {
		this.gerente = gerente;
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(OkButtonLogin)) {
			int i = 0;
			Cliente[] listaDeClientes = gerente.repositorioC().getCliente();
			while (i < gerente.repositorioC().getNumeroClientes()) {
				if (listaDeClientes[i].getNome().equals(campoLogin.getText())
						&& listaDeClientes[i].getSenha().equals(campoSenhaLogin.getText())) {
					cL.show(cards, "Tela Inicial");
					break;
				}
				i++;
			}
		}
		if (e.getSource().equals(OkButtonCadastro)) {
			cL.show(cards, "Tela Inicial");
			gerente.adicionarCliente(new Cliente(campoCadastroLogin.getText(), campoCadastroNome.getText(),
					campoCadastroSenha.getText()));
		}
		if (e.getSource().equals(logoutButton)) {
			cL.show(cards, "Tela Inicial");
		}
		if (e.getSource().equals(opcaoCadastro)) {
			cL.show(cards, "Tela De Cadastro");
		}
		if (e.getSource().equals(opcaoLogin)) {
			if (gerente.repositorioC().getNumeroClientes() != 0) {
				cL.show(cards, "Tela De Login");
			}
		}
	}

	public void janelas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		add(cards);
		
		JPanel telaLogin = new JPanel(null);
		JLabel login = new JLabel("Login :");
		JLabel senha = new JLabel("Senha :");
		JLabel loginCadastro = new JLabel("Login :");
		JLabel senhaCadastro = new JLabel("Senha :");
		JLabel nomeCadastro = new JLabel("nome: ");
		JLabel listaDeClientes = new JLabel(gerente.listarRestaurantes());
		
		JPanel telaInicial = new JPanel(null);
		JPanel telaCadastro = new JPanel(null);
		JPanel telaPedir = new JPanel(null);
		
		opcaoCadastro.setBounds(290, 420, 220, 50);
		OkButtonLogin.setBounds(350, 450, 100, 30);
		OkButtonCadastro.setBounds(350, 450, 100, 30);
		opcaoLogin.setBounds(290, 350, 220, 50);
		campoCadastroNome.setBounds(300, 300, 200, 30);
		campoLogin.setBounds(300, 350, 200, 30);
		campoSenhaLogin.setBounds(300, 400, 200, 30);
		campoCadastroLogin.setBounds(300, 350, 200, 30);
		campoCadastroSenha.setBounds(300, 400, 200, 30);		
		login.setBounds(250, 350, 50, 30);
		senha.setBounds(250, 400, 50, 30);
		senhaCadastro.setBounds(250, 400, 50, 30);
		nomeCadastro.setBounds(250, 300, 50, 30);
		loginCadastro.setBounds(250, 350, 50, 30);
		senhaCadastro.setBounds(250, 400, 50, 30);
		
		cards.add(telaInicial, "Tela Inicial");
		cards.add(telaCadastro, "Tela De Cadastro");
		cards.add(telaLogin, "Tela De Login");
		cards.add(telaPedir, "Efetuar Pedido");
		telaLogin.add(campoLogin);
		telaLogin.add(campoSenhaLogin);
		telaLogin.add(login);
		telaLogin.add(senha);
		telaLogin.add(OkButtonLogin);
		telaInicial.add(opcaoCadastro);
		telaInicial.add(opcaoLogin);
		telaCadastro.add(campoCadastroNome);
		telaCadastro.add(campoCadastroLogin);
		telaCadastro.add(campoCadastroSenha);
		telaCadastro.add(OkButtonCadastro);
		telaCadastro.add(nomeCadastro);
		telaCadastro.add(loginCadastro);
		telaCadastro.add(senhaCadastro);
		telaPedir.add(listaDeClientes);
		
		OkButtonLogin.addActionListener(this);
		OkButtonCadastro.addActionListener(this);
		opcaoCadastro.addActionListener(this);
		opcaoLogin.addActionListener(this);
	}
}
