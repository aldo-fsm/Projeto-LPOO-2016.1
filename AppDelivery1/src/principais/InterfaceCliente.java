package principais;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dados.DataBase;
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
	private JButton pedir = new JButton("Pedir");
	private JButton OkButtonSair = new JButton("Sair");
	private JButton sair = new JButton("Deixar De ser Cliente");
	private JTextField campoLogin = new JTextField();
	private JTextField CampoLoginSair = new JTextField();
	private JPasswordField campoSenhaLogin = new JPasswordField();
	private JPasswordField CampoSenhaSair = new JPasswordField();
	private JTextField campoCadastroLogin = new JTextField();
	private JPasswordField campoCadastroSenha = new JPasswordField();
	private Gerente gerente;
	private String senhaDoUsuario;
	private String loginDoUsuario;

	public static void main(String[] args) {
		Gerente gerente = DataBase.lerBaseGerente();
		System.out.println(gerente.repositorioR().getNumeroRestaurantes());
		InterfaceCliente telaCliente = new InterfaceCliente(gerente);
		telaCliente.janelas();
	}

	public InterfaceCliente(Gerente gerente) {
		this.gerente = gerente;
	}

	public void janelaPrincipal() {
		JPanel telaLogado = new JPanel(null);
		cards.add(telaLogado, "Tela Principal");
		logoutButton.setBounds(300, 500, 200, 30);
		logoutButton.addActionListener(this);
		telaLogado.add(logoutButton);
		pedir.setBounds(295, 300, 210, 50);
		pedir.addActionListener(this);
		telaLogado.add(pedir);
		sair.setBounds(300, 460, 200, 30);
		sair.addActionListener(this);
		telaLogado.add(sair);
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
		System.out.println(DataBase.lerBaseGerente().listarRestaurantes());
		JLabel listaDeRestaurantes = new JLabel(DataBase.lerBaseGerente().listarRestaurantes());
		listaDeRestaurantes.setBounds(200, 50, 210, 50);
		telaPedir.add(listaDeRestaurantes);
		telaPedir.add(logoutButton);
		efetuarPedido.setBounds(295, 300, 210, 50);
		efetuarPedido.addActionListener(this);
		telaPedir.add(efetuarPedido);
		adicionarItem.setBounds(295, 360, 210, 50);
		adicionarItem.addActionListener(this);
		telaPedir.add(adicionarItem);
		removerItem.setBounds(295, 420, 210, 50);
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
		janelaPrincipal();
		janelaSair();
	}

	public void janelaSair() {
		JPanel telaSair = new JPanel(null);
		cards.add(telaSair, "Tela Sair");
		CampoLoginSair.setBounds(300, 350, 200, 30);
		telaSair.add(CampoLoginSair);
		CampoSenhaSair.setBounds(300, 400, 200, 30);
		telaSair.add(CampoSenhaSair);
		JLabel login = new JLabel("Login :");
		login.setBounds(250, 350, 50, 30);
		telaSair.add(login);
		JLabel senha = new JLabel("Senha :");
		senha.setBounds(250, 400, 50, 30);
		telaSair.add(senha);
		OkButtonSair.setBounds(350, 450, 100, 30);
		OkButtonSair.addActionListener(this);
		telaSair.add(OkButtonSair);

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(OkButtonLogin)) {
			int i = 0;
			boolean logado = false;
			Cliente[] listaDeClientes = gerente.repositorioC().getCliente();
			while (i < gerente.repositorioC().getNumeroClientes()) {
				if (listaDeClientes[i].getNome().equals(campoLogin.getText())
						&& listaDeClientes[i].getSenha().equals(campoSenhaLogin.getText())) {
					cL.show(cards, "Tela Principal");
					loginDoUsuario = campoLogin.getText();
					campoLogin.setText("");
					senhaDoUsuario = campoSenhaLogin.getText();
					campoSenhaLogin.setText("");
					logado = true;
					break;
				}
				i++;
			}
			if (!logado) {
				JOptionPane.showMessageDialog(this, "Senha ou Login incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
				campoSenhaLogin.setText("");
				campoLogin.setText("");
			}
		}
		if (e.getSource().equals(OkButtonCadastro)) {
			int i = 0;
			boolean podeCadastrar = true;
			while (i < gerente.repositorioC().getNumeroClientes()) {
				if ((campoCadastroLogin.getText().equals(gerente.repositorioC().getCliente(i).getLogin())
						|| campoCadastroNome.getText().equals(gerente.repositorioC().getCliente(i).getNome()))) {
					podeCadastrar = false;
				}
				i++;
			}
			if (podeCadastrar) {
				if (!campoCadastroLogin.getText().equals("") && !campoCadastroNome.getText().equals("")
						&& !campoCadastroSenha.getText().equals("")) {
					cL.show(cards, "Tela Principal");
					gerente.adicionarCliente(new Cliente(campoCadastroLogin.getText(), campoCadastroNome.getText(),
							campoCadastroSenha.getText()));

					loginDoUsuario = campoCadastroLogin.getText();
					campoCadastroLogin.setText("");
					campoCadastroNome.setText("");
					senhaDoUsuario = campoCadastroSenha.getText();
					campoCadastroSenha.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Um ou mais campos esta(o) vazio(s)", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Informações ja utilizadas", "Erro", JOptionPane.ERROR_MESSAGE);
				campoCadastroLogin.setText("");
				campoCadastroNome.setText("");
				campoCadastroSenha.setText("");
			}
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
			} else {
				JOptionPane.showMessageDialog(this, "não e possivel logar, pois nao há clientes cadastrados", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(pedir)) {
			cL.show(cards, "Efetuar Pedido");
		}
		if (e.getSource().equals(sair)) {
			cL.show(cards, "Tela Sair");
		}
		if (e.getSource().equals(OkButtonSair)) {
			int i = 0;
			if (senhaDoUsuario.equals(CampoSenhaSair.getText()) && loginDoUsuario.equals(CampoLoginSair.getText())) {
				cL.show(cards, "Tela Inicial");
				while (i < gerente.repositorioC().getNumeroClientes()) {
					if (gerente.repositorioC().getCliente(i).getNome().equals(loginDoUsuario)
							&& gerente.repositorioC().getCliente(i).getSenha().equals(senhaDoUsuario)) {
						break;
					}
				}
				gerente.removerCliente(i);
				CampoLoginSair.setText("");
				CampoSenhaSair.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Senha ou Login incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
				CampoLoginSair.setText("");
				CampoSenhaSair.setText("");
			}
		}
	}

}
