
package principais;

import java.awt.CardLayout;
import java.awt.Color;
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
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public class InterfaceCliente extends JFrame implements ActionListener {
	// atributos de interface
	private static final long serialVersionUID = 1L;
	private CardLayout cL = new CardLayout();
	private JPanel cards = new JPanel(cL);
	
	//variaveis da tela inicial
	private JButton opcaoLogin = new JButton("Fazer Login");
	private JButton opcaoCadastro = new JButton("Cadastrar");
	
	//variaveis de login
	private JButton okButtonLogin = new JButton("OK");
	private JButton cancelarLogin = new JButton("cancelar");
	private JTextField campoLogin = new JTextField();
	private JPasswordField campoSenhaLogin = new JPasswordField();
	
	//variaveis da tela principal
	private JButton pedir = new JButton("Pedir");
	private JButton sair = new JButton("Sair"); 
	private JButton logoutButton = new JButton("Logout");
	
	//variaveis de pedir
	private JButton voltar = new JButton("Voltar");
	private JButton efetuarPedido = new JButton("Fazer Pedido");
	private JButton removerItem = new JButton("Remover Do Carrinho");
	private JButton adicionarItem = new JButton("Adicionar Ao Carrinho");
	
	//variaveis de cadastro
	private JTextField campoCadastroLogin = new JTextField();
	private JTextField campoCadastroNome = new JTextField();
	private JButton okButtonCadastro = new JButton("OK");
	private JButton cancelarCadastro = new JButton("cancelar");
	private JPasswordField campoCadastroSenha = new JPasswordField();
	
	// atributos de escolha do cliente e carregamento do gerente
	private long idRestauranteEscolhido;
	private Gerente gerente = DataBase.lerBaseGerente();
	private String senhaDoUsuario;
	private String loginDoUsuario;
	private int numeroRestauranteEscolhido;
	private int numeroDoCliente;

	public static void main(String[] args) {
		InterfaceCliente telaCliente = new InterfaceCliente();
		telaCliente.janelas();
	}

	private void janelaPrincipal() {
		JPanel telaLogado = new JPanel(null);
		cards.add(telaLogado, "Tela Principal");
		logoutButton.setBounds(295, 360, 210, 50);
		logoutButton.addActionListener(this);
		logoutButton.setBackground(new Color(150,250, 250));
		logoutButton.setForeground(Color.WHITE);
		telaLogado.add(logoutButton);
		pedir.setBounds(295, 300, 210, 50);
		pedir.addActionListener(this);
		pedir.setBackground(new Color(150,250, 250));
		pedir.setForeground(Color.WHITE);
		telaLogado.add(pedir);
		sair.setBounds(295, 420, 210, 50);
		sair.addActionListener(this);
		sair.setBackground(new Color(150,250, 250));
		sair.setForeground(Color.WHITE);
		telaLogado.add(sair);
		telaLogado.setBackground(new Color(130, 210, 135));
	}

	private void janelaLogin() {
		JPanel telaLogin = new JPanel(null);
		cards.add(telaLogin, "Tela De Login");
		campoLogin.setBounds(300, 350, 200, 30);
		telaLogin.add(campoLogin);
		campoSenhaLogin.setBounds(300, 400, 200, 30);
		telaLogin.add(campoSenhaLogin);
		JLabel login = new JLabel("Login :");
		login.setBounds(250, 350, 50, 30);
		login.setForeground(Color.WHITE);
		telaLogin.add(login);
		JLabel senha = new JLabel("Senha :");
		senha.setBounds(250, 400, 50, 30);
		senha.setForeground(Color.WHITE);
		telaLogin.add(senha);
		okButtonLogin.setBounds(285, 450, 100, 30);
		okButtonLogin.addActionListener(this);
		okButtonLogin.setBackground(new Color(150,250, 250));
		okButtonLogin.setForeground(Color.WHITE);
		telaLogin.add(okButtonLogin);
		cancelarLogin.setBounds(415, 450, 100, 30);
		cancelarLogin.addActionListener(this);
		cancelarLogin.setBackground(new Color(150,250, 250));
		cancelarLogin.setForeground(Color.WHITE);
		telaLogin.add(cancelarLogin);
		telaLogin.setBackground(new Color(130, 210, 135));
	}

	private void janelaPedido() {
		JPanel telaPedir = new JPanel(null);
		cards.add(telaPedir, "Efetuar Pedido");
		telaPedir.add(logoutButton);
		efetuarPedido.setBounds(295, 240, 210, 50);
		efetuarPedido.addActionListener(this);
		efetuarPedido.setBackground(new Color(150,250, 250));
		efetuarPedido.setForeground(Color.WHITE);
		telaPedir.add(efetuarPedido);
		adicionarItem.setBounds(295, 300, 210, 50);
		adicionarItem.addActionListener(this);
		adicionarItem.setBackground(new Color(150,250, 250));
		adicionarItem.setForeground(Color.WHITE);
		telaPedir.add(adicionarItem);
		removerItem.setBounds(295, 360, 210, 50);
		removerItem.addActionListener(this);
		removerItem.setBackground(new Color(150,250, 250));
		removerItem.setForeground(Color.WHITE);
		telaPedir.add(removerItem);
		voltar.setBounds(295, 420, 210, 50);
		voltar.addActionListener(this);
		voltar.setBackground(new Color(150,250, 250));
		voltar.setForeground(Color.WHITE);
		telaPedir.add(voltar);
		telaPedir.setBackground(new Color(130, 210, 135));
	}

	private void janelaCadastro() {
		JPanel telaCadastro = new JPanel(null);
		cards.add(telaCadastro, "Tela De Cadastro");
		campoCadastroNome.setBounds(300, 300, 200, 30);
		telaCadastro.add(campoCadastroNome);
		campoCadastroLogin.setBounds(300, 350, 200, 30);
		telaCadastro.add(campoCadastroLogin);
		campoCadastroSenha.setBounds(300, 400, 200, 30);
		telaCadastro.add(campoCadastroSenha);
		okButtonCadastro.setBounds(285, 450, 100, 30);
		okButtonCadastro.addActionListener(this);
		okButtonCadastro.setBackground(new Color(150,250, 250));
		okButtonCadastro.setForeground(Color.WHITE);
		telaCadastro.add(okButtonCadastro);
		cancelarCadastro.setBounds(415, 450, 100, 30);
		cancelarCadastro.addActionListener(this);
		cancelarCadastro.setBackground(new Color(150,250, 250));
		cancelarCadastro.setForeground(Color.WHITE);
		telaCadastro.add(cancelarCadastro);
		JLabel nomeCadastro = new JLabel("nome: ");
		nomeCadastro.setBounds(250, 300, 50, 30);
		nomeCadastro.setForeground(Color.WHITE);
		telaCadastro.add(nomeCadastro);
		JLabel loginCadastro = new JLabel("Login :");
		loginCadastro.setBounds(250, 350, 50, 30);
		loginCadastro.setForeground(Color.WHITE);
		telaCadastro.add(loginCadastro);
		JLabel senhaCadastro = new JLabel("Senha :");
		senhaCadastro.setBounds(250, 400, 50, 30);
		senhaCadastro.setForeground(Color.WHITE);
		telaCadastro.add(senhaCadastro);
		telaCadastro.setBackground(new Color(130, 210, 135));
	}

	private void janelaInicial() {
		JPanel telaInicial = new JPanel(null);
		cards.add(telaInicial, "Tela Inicial");
		opcaoCadastro.setBounds(290, 420, 220, 50);
		opcaoCadastro.addActionListener(this);
		opcaoCadastro.setBackground(new Color(150,250, 250));
		opcaoCadastro.setForeground(Color.WHITE);
		telaInicial.add(opcaoCadastro);
		opcaoLogin.setBounds(290, 350, 220, 50);
		opcaoLogin.addActionListener(this);
		opcaoLogin.setBackground(new Color(150,250, 250));
		opcaoLogin.setForeground(Color.WHITE);
		telaInicial.add(opcaoLogin);
		telaInicial.setBackground(new Color(130, 210, 135));
	}

	// metodo apresenta toda a interface do cliente
	public void janelas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		add(cards);
		janelaInicial();
		janelaLogin();
		janelaCadastro();
		janelaPedido();
		janelaPrincipal();
		setResizable(false);
	}

	private void removerItem() {
		String itemRemovido;
		boolean idIncorreto = true;
		int numeroDoItem;
		do {// pede e le id que o cliente digita

			// lista o cardapio do restaurante de id escolhido
			itemRemovido = "" + JOptionPane
					.showInputDialog("digite o numero correspondente ao item que deseja remover, abaixo, listado\n"
							+ gerente.repositorioC().getCliente(numeroDoCliente).listarCarrinho());

			if (itemRemovido.equals("null")) {// caso tenha apertado em cancelar
												// a janela fecha
				break;
			}
			numeroDoItem = 0;
			while (numeroDoItem < gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho()) {
				/*
				 * confere se o id digitado pelo cliente corresponde a um dos
				 * ids dos pratos
				 */
				if (itemRemovido.compareTo(String.valueOf(
						gerente.repositorioC().getCliente(numeroDoCliente).getCarrinho(numeroDoItem).getId())) == 0) {
					gerente.repositorioC().getCliente(numeroDoCliente).removerDoCarrinho(numeroDoItem);
					gerente.repositorioC().getCliente(numeroDoCliente).setNumeroItensCarrinho(
							gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho() + 1);
					DataBase.salvarEstado(gerente);// salva o estado do sistema
					idIncorreto = false;
					break;
				}
				numeroDoItem++;
			}
		} while (idIncorreto);
	}

	private void adicionarItem() {
		String itemEscolhido;
		long idItemEscolhido;
		int i;
		boolean idIncorreto = true;
		do {// pede e le id que o cliente digita
			itemEscolhido = ""
					+ JOptionPane.showInputDialog("digite o numero correspondente ao item que deseja, abaixo listado\n"
							+ gerente.repositorioR().listarCardapio(idRestauranteEscolhido));
			if (itemEscolhido.equals("null")) {// caso tenha apertado em
												// cancelar a janela fecha
				break;
			}
			i = 0;
			while (i < gerente.repositorioR().getRestaurante(numeroRestauranteEscolhido).getNumeroPratosCardapio()) {
				/*
				 * confere se o id digitado pelo cliente corresponde a um dos
				 * ids dos pratos
				 */
				if (itemEscolhido.compareTo(String.valueOf(gerente.repositorioR()
						.getRestaurante(numeroRestauranteEscolhido).getPratoCardapio(i).getId())) == 0) {
					idItemEscolhido = Long.parseLong(itemEscolhido);
					numeroDoCliente = 0;
					while (numeroDoCliente < gerente.repositorioC().getNumeroClientes()) {
						/*
						 * identifica o cliete que possui o login e senha
						 * informados
						 */
						if (gerente.repositorioC().getCliente(numeroDoCliente).getLogin().equals(loginDoUsuario)
								&& gerente.repositorioC().getCliente(numeroDoCliente).getSenha()
										.equals(senhaDoUsuario)) {
							break;
						}
						numeroDoCliente++;
					}
					gerente.repositorioC().getCliente(numeroDoCliente).adicionarNoCarrinho(gerente.repositorioR()
							.getRestaurante(numeroRestauranteEscolhido).getPratoCardapio(idItemEscolhido));
					DataBase.salvarEstado(gerente);// salva o estado do sistema
					idIncorreto = false;
					break;
				}
				i++;
			}
		} while (idIncorreto);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cancelarLogin)) {
			cL.show(cards, "Tela Inicial");
			campoLogin.setText("");// campo e apagado
			campoSenhaLogin.setText("");// campo e apagado
		}
		if (e.getSource().equals(cancelarCadastro)) {
			cL.show(cards, "Tela Inicial");
			campoCadastroLogin.setText("");// campo e apagado
			campoCadastroNome.setText("");// campo e apagado
			campoCadastroSenha.setText("");// campo e apagado
		}
		if (e.getSource().equals(okButtonLogin)) {
			int i = 0;
			boolean logado = false;
			Cliente[] listaDeClientes = gerente.repositorioC().getClientes();
			while (i < gerente.repositorioC().getNumeroClientes()) {
				/*
				 * confere se as informaçoes de login e senha condizem com
				 * algum dos usuarios do repositorio
				 */
				if (listaDeClientes[i].getLogin().equals(campoLogin.getText())
						&& listaDeClientes[i].getSenha().equals(new String(campoSenhaLogin.getPassword()))) {
					cL.show(cards, "Tela Principal");
					/*
					 * se condiz a tela principal e aberta
					 */
					loginDoUsuario = campoLogin.getText();
					/*
					 * o login e salvo caso o usuario deseje deixar de ser
					 * cliente
					 */
					senhaDoUsuario = new String(campoSenhaLogin.getPassword());
					// assim como a senha
					campoLogin.setText("");// campo e apagado
					campoSenhaLogin.setText("");// campo e apagado
					logado = true;// informaçoes validas
					break;
				}
				i++;
			}
			if (!logado) {
				JOptionPane.showMessageDialog(this, "Senha ou Login incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
				campoSenhaLogin.setText("");// campo e apagado
				campoLogin.setText("");// campo e apagado
			}
		}
		if (e.getSource().equals(okButtonCadastro)) {
			int i = 0;
			boolean podeCadastrar = true;
			while (i < gerente.repositorioC().getNumeroClientes()) {
				/*
				 * confere se as inforï¿½ï¿½es de login e/ou nome ja foram
				 * utilizadas
				 */
				if ((campoCadastroLogin.getText().equals(gerente.repositorioC().getCliente(i).getLogin())
						|| campoCadastroNome.getText().equals(gerente.repositorioC().getCliente(i).getNome()))) {
					podeCadastrar = false;
				}
				i++;
			}
			if (podeCadastrar) {
				/*
				 * caso nao tenha sido utilizada pode prosseguir com a
				 * execução do programa
				 */
				if (!campoCadastroLogin.getText().equals("") && !campoCadastroNome.getText().equals("")
						&& !(new String(campoCadastroSenha.getPassword())).equals("")) {// confere
					/*
					 * se os campos estïverem vazios
					 */
					try {
						gerente.adicionarCliente(new Cliente(campoCadastroLogin.getText(),
								new String(campoCadastroSenha.getPassword()), campoCadastroNome.getText()));
						cL.show(cards, "Tela Principal");// vai para tela
															// principal
					} catch (IdInvalidoException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (SenhaInvalidaException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (RepositorioCheioException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
					/*
					 * adiciona um novo cliente no repositorio do gerente
					 * informado
					 */
					loginDoUsuario = campoCadastroLogin.getText();
					/*
					 * salva na classe o login do cliente
					 */
					senhaDoUsuario = new String(campoCadastroSenha.getPassword());
					/*
					 * salva na classe o senha do cliente
					 */
					campoCadastroLogin.setText("");// apaga os campos de texto
					campoCadastroNome.setText("");// apaga os campos de texto
					campoCadastroSenha.setText("");// apaga os campos de texto
					DataBase.salvarEstado(gerente);// salva o estado do sistema
													// apos adicionado o cliente
				} else {
					JOptionPane.showMessageDialog(this, "Um ou mais campos esta(o) vazio(s)", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Informaçoes ja utilizadas", "Erro", JOptionPane.ERROR_MESSAGE);
				campoCadastroLogin.setText("");// apaga os campos de texto
				campoCadastroNome.setText("");// apaga os campos de texto
				campoCadastroSenha.setText("");// apaga os campos de texto
			}
		}
		if (e.getSource().equals(logoutButton)) {
			cL.show(cards, "Tela Inicial");// ao ser deslogado o usuario volta
											// para pagina inicial
		}
		if (e.getSource().equals(opcaoCadastro)) {
			cL.show(cards, "Tela De Cadastro");// ao escolher a opïçao
												// cadastro
												// abre-se a janela de cadastro
		}
		if (e.getSource().equals(opcaoLogin)) {
			if (gerente.repositorioC().getNumeroClientes() != 0) {
				/*
				 * se houver cliente(s) a tela de login e aberta ao ser esta
				 * opiçao
				 */
				cL.show(cards, "Tela De Login");
			} else {
				JOptionPane.showMessageDialog(this, "nï¿½o e possivel logar, pois nao hï¿½ clientes cadastrados",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(pedir)) {
			String restauranteEscolhido;
			int i;
			boolean idIncorreto = true;
			do {
				restauranteEscolhido = "" + JOptionPane
						.showInputDialog("digite o numero correspondente ao restaurante que deseja, abaixo listado\n"
								+ gerente.listarRestaurantes());
				if (restauranteEscolhido.equals("null")) {
					break;
				}
				i = 0;
				while (i < gerente.repositorioR().getNumeroRestaurantes()) {
					if (restauranteEscolhido
							.compareTo(String.valueOf(gerente.repositorioR().getRestaurante(i).getId())) == 0) {
						idRestauranteEscolhido = Long.parseLong(restauranteEscolhido);
						cL.show(cards, "Efetuar Pedido");
						numeroRestauranteEscolhido = i;
						idIncorreto = false;
						break;
					}
					i++;
				}
			} while (idIncorreto);
		}
		if (e.getSource().equals(sair)) {
			cL.show(cards, "Tela Inicial");
			int i = 0;
			while (i < gerente.repositorioC().getNumeroClientes()) {
				if (gerente.repositorioC().getCliente(i).getLogin().equals(loginDoUsuario)
						&& gerente.repositorioC().getCliente(i).getSenha().equals(senhaDoUsuario)) {
					break;
				}
				i++;
			}
			gerente.removerCliente(i);
			DataBase.salvarEstado(gerente);
		}
		if (e.getSource().equals(efetuarPedido)) {
			if (gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho() > 0) {
				try {
					gerente.repositorioC().getCliente(numeroDoCliente).efetuarPedido(idRestauranteEscolhido);
				} catch (RepositorioCheioException e1) {
					JOptionPane.showMessageDialog(this, "Este restaurante atingiu o numero maximo de pedidos", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				cL.show(cards, "Tela Principal");
			} else {
				JOptionPane.showMessageDialog(null, "nao ha itens fazer pedido", "Erro", JOptionPane.ERROR_MESSAGE,
						null);
			}
		}
		if (e.getSource().equals(adicionarItem)) {
			adicionarItem();
		}
		if (e.getSource().equals(removerItem)) {
			if (gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho() != 0) {
				removerItem();
			} else {
				JOptionPane.showMessageDialog(null, "nao ha itens para remover", "Erro", JOptionPane.ERROR_MESSAGE,
						null);
			}
		}
		if (e.getSource().equals(voltar)) {
			cL.show(cards, "Tela Principal");
		}
	}
}