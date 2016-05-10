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
	private JButton logoutButton = new JButton("Logout");
	private JButton OkButtonLogin = new JButton("OK");
	private JButton OkButtonCadastro = new JButton("OK");
	private JButton opcaoLogin = new JButton("Fazer Login");
	private JButton opcaoCadastro = new JButton("Cadastrar");
	private JButton adicionarItem = new JButton("Adicionar Item Ao Carrinho");
	private JButton removerItem = new JButton("Remover Item Do Carrinho");
	private JButton efetuarPedido = new JButton("Efetuar Pedido");
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

	public void janelaLogin() {
		JPanel telaLogin = new JPanel(null);
		cards.add(telaLogin, "Tela De Login");
		campoLogin.setBounds(300, 350, 200, 30);
		telaLogin.add(campoLogin);
		campoSenhaLogin.setBounds(300, 400, 200, 30);
		telaLogin.add(campoSenhaLogin);
		JLabel login = new JLabel("Login :");
		login.setBounds(250, 350, 50, 30);
		telaLogin.add(login);
		JLabel senha = new JLabel("Senha :");
		senha.setBounds(250, 400, 50, 30);
		telaLogin.add(senha);
		OkButtonLogin.setBounds(350, 450, 100, 30);
		OkButtonLogin.addActionListener(this);
		telaLogin.add(OkButtonLogin);
	}

	public void janelaPedido() {
		JPanel telaPedir = new JPanel(null);
		cards.add(telaPedir, "Efetuar Pedido");
		JLabel listaDeRestaurantes = new JLabel(gerente.listarRestaurantes());
		listaDeRestaurantes.setBounds(250, 50, 50, 30);
		telaPedir.add(listaDeRestaurantes);
		logoutButton.setBounds(300, 500, 200, 30);
		logoutButton.addActionListener(this);
		telaPedir.add(logoutButton);
		efetuarPedido.setBounds(300, 200, 200, 40);
		efetuarPedido.addActionListener(this);
		telaPedir.add(efetuarPedido);
		adicionarItem.setBounds(300, 250, 200, 40);
		adicionarItem.addActionListener(this);
		telaPedir.add(adicionarItem);
		removerItem.setBounds(300, 300, 200, 40);
		removerItem.addActionListener(this);
		telaPedir.add(removerItem);
	}

	public void janelaCadastro() {
		JPanel telaCadastro = new JPanel(null);
		cards.add(telaCadastro, "Tela De Cadastro");
		campoCadastroNome.setBounds(300, 300, 200, 30);
		telaCadastro.add(campoCadastroNome);
		campoCadastroLogin.setBounds(300, 350, 200, 30);
		telaCadastro.add(campoCadastroLogin);
		campoCadastroSenha.setBounds(300, 400, 200, 30);
		telaCadastro.add(campoCadastroSenha);
		OkButtonCadastro.setBounds(350, 450, 100, 30);
		OkButtonCadastro.addActionListener(this);
		telaCadastro.add(OkButtonCadastro);
		JLabel nomeCadastro = new JLabel("nome: ");
		nomeCadastro.setBounds(250, 300, 50, 30);
		telaCadastro.add(nomeCadastro);
		JLabel loginCadastro = new JLabel("Login :");
		loginCadastro.setBounds(250, 350, 50, 30);
		telaCadastro.add(loginCadastro);
		JLabel senhaCadastro = new JLabel("Senha :");
		senhaCadastro.setBounds(250, 400, 50, 30);
		telaCadastro.add(senhaCadastro);
	}

	public void janelaInicial() {
		JPanel telaInicial = new JPanel(null);
		cards.add(telaInicial, "Tela Inicial");
		opcaoCadastro.setBounds(290, 420, 220, 50);
		opcaoCadastro.addActionListener(this);
		telaInicial.add(opcaoCadastro);
		opcaoLogin.setBounds(290, 350, 220, 50);
		opcaoLogin.addActionListener(this);
		telaInicial.add(opcaoLogin);
	}

	public void janelas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		add(cards);
		janelaInicial();
		janelaLogin();
		janelaCadastro();
		janelaPedido();

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(OkButtonLogin)) {
			int i = 0;
			Cliente[] listaDeClientes = gerente.repositorioC().getCliente();
			while (i < gerente.repositorioC().getNumeroClientes()) {
				if (listaDeClientes[i].getNome().equals(campoLogin.getText())
						&& listaDeClientes[i].getSenha().equals(campoSenhaLogin.getText())) {
					cL.show(cards, "Efetuar Pedido");
					campoLogin.setText("");
					campoSenhaLogin.setText("");
					break;
				}
				i++;
			}
		}
		if (e.getSource().equals(OkButtonCadastro)) {
			cL.show(cards, "Tela Inicial");
			gerente.adicionarCliente(new Cliente(campoCadastroLogin.getText(), campoCadastroNome.getText(),
					campoCadastroSenha.getText()));
			campoCadastroLogin.setText("");
			campoCadastroNome.setText("");
			campoCadastroSenha.setText("");
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

}
