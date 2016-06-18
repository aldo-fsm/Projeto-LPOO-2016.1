
package principais;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

	public InterfaceCliente() {
		super("AppDelivery - Cliente");
	}

	// atributos de interface
	private static final long serialVersionUID = 1L;
	private CardLayout cL = new CardLayout();
	private JPanel cards = new JPanel(cL);

	// variaveis da tela inicial
	private JButton opcaoLogin = new JButton("Fazer Login");
	private JButton opcaoCadastro = new JButton("Cadastrar");

	// variaveis de login
	private JButton okButtonLogin = new JButton("OK");
	private JButton cancelarLogin = new JButton("cancelar");
	private JTextField campoLogin = new JTextField();
	private JPasswordField campoSenhaLogin = new JPasswordField();

	// variaveis da tela principal
	private JButton pedir = new JButton("Pedir");
	private JButton sair = new JButton("Sair");
	private JButton logoutButton = new JButton("Logout");

	// variaveis de pedir
	private JButton voltar = new JButton("Voltar");
	private JButton efetuarPedido = new JButton("Fazer Pedido");
	private JButton removerItem = new JButton("Remover Do Carrinho");
	private JButton adicionarItem = new JButton("Adicionar Ao Carrinho");
	private JButton cancelarPedido = new JButton("Cancelar Pedido");

	// variaveis de cadastro
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
		logoutButton.setBackground(new Color(150, 250, 250));
		telaLogado.add(logoutButton);
		pedir.setBounds(295, 300, 210, 50);
		pedir.addActionListener(this);
		pedir.setBackground(new Color(150, 250, 250));
		telaLogado.add(pedir);
		sair.setBounds(295, 420, 210, 50);
		sair.addActionListener(this);
		sair.setBackground(new Color(150, 250, 250));
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
		telaLogin.add(login);
		JLabel senha = new JLabel("Senha :");
		senha.setBounds(250, 400, 50, 30);
		telaLogin.add(senha);
		okButtonLogin.setBounds(285, 450, 100, 30);
		okButtonLogin.addActionListener(this);
		okButtonLogin.setBackground(new Color(150, 250, 250));
		telaLogin.add(okButtonLogin);
		cancelarLogin.setBounds(415, 450, 100, 30);
		cancelarLogin.addActionListener(this);
		cancelarLogin.setBackground(new Color(150, 250, 250));
		telaLogin.add(cancelarLogin);
		telaLogin.setBackground(new Color(130, 210, 135));
	}
	
	public void janelas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("icone.png");  
		this.setIconImage(iconeTitulo);
		add(cards);
		janelaInicial();
		janelaLogin();
		janelaCadastro();
		janelaPedido();
		janelaPrincipal();
		setResizable(false);
	}

	private void janelaPedido() {
		JPanel telaPedir = new JPanel(null);
		cards.add(telaPedir, "Efetuar Pedido");
		telaPedir.add(logoutButton);
		efetuarPedido.setBounds(295, 240, 210, 50);
		efetuarPedido.addActionListener(this);
		efetuarPedido.setBackground(new Color(150, 250, 250));
		telaPedir.add(efetuarPedido);
		adicionarItem.setBounds(295, 300, 210, 50);
		adicionarItem.addActionListener(this);
		adicionarItem.setBackground(new Color(150, 250, 250));
		telaPedir.add(adicionarItem);
		removerItem.setBounds(295, 360, 210, 50);
		removerItem.addActionListener(this);
		removerItem.setBackground(new Color(150, 250, 250));
		telaPedir.add(removerItem);
		cancelarPedido.setBounds(295, 420, 210, 50);
		cancelarPedido.addActionListener(this);
		cancelarPedido.setBackground(new Color(150, 250, 250));
		telaPedir.add(cancelarPedido);
		voltar.setBounds(295, 480, 210, 50);
		voltar.addActionListener(this);
		voltar.setBackground(new Color(150, 250, 250));
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
		okButtonCadastro.setBackground(new Color(150, 250, 250));
		telaCadastro.add(okButtonCadastro);
		cancelarCadastro.setBounds(415, 450, 100, 30);
		cancelarCadastro.addActionListener(this);
		cancelarCadastro.setBackground(new Color(150, 250, 250));
		telaCadastro.add(cancelarCadastro);
		JLabel nomeCadastro = new JLabel("nome: ");
		nomeCadastro.setBounds(250, 300, 50, 30);
		telaCadastro.add(nomeCadastro);
		JLabel loginCadastro = new JLabel("Login :");
		loginCadastro.setBounds(250, 350, 50, 30);
		telaCadastro.add(loginCadastro);
		JLabel senhaCadastro = new JLabel("Senha :");
		senhaCadastro.setBounds(250, 400, 50, 30);
		telaCadastro.add(senhaCadastro);
		telaCadastro.setBackground(new Color(130, 210, 135));
	}

	private void janelaInicial() {
		JPanel telaInicial = new JPanel(null);
		cards.add(telaInicial, "Tela Inicial");
		opcaoCadastro.setBounds(290, 420, 220, 50);
		opcaoCadastro.addActionListener(this);
		opcaoCadastro.setBackground(new Color(150, 250, 250));
		telaInicial.add(opcaoCadastro);
		opcaoLogin.setBounds(290, 350, 220, 50);
		opcaoLogin.addActionListener(this);
		opcaoLogin.setBackground(new Color(150, 250, 250));
		telaInicial.add(opcaoLogin);
		telaInicial.setBackground(new Color(130, 210, 135));
	}

	// metodo apresenta toda a interface do cliente

	private void removerItem() {
		String itemRemovido;
		boolean idIncorreto = true;
		int numeroDoItem;
		do {// pede e le id que o cliente digita

			// lista o cardapio do restaurante de id escolhido
			itemRemovido = "" + JOptionPane
					.showInputDialog("digite o numero correspondente ao item que deseja remover, abaixo, listado\n"
							+ gerente.repositorioC().get(numeroDoCliente).listarCarrinho());

			if (itemRemovido.equals("null")) {// caso tenha apertado em cancelar
												// a janela fecha
				break;
			}
			numeroDoItem = 0;
			while (numeroDoItem < gerente.repositorioC().get(numeroDoCliente).getNumeroItensCarrinho()) {
				/*
				 * confere se o id digitado pelo cliente corresponde a um dos
				 * ids dos pratos
				 */
				if (itemRemovido.compareTo(String
						.valueOf(gerente.repositorioC().get(numeroDoCliente).getCarrinho(numeroDoItem).getId())) == 0) {
					gerente.repositorioC().get(numeroDoCliente).removerDoCarrinho(numeroDoItem);
					gerente.repositorioC().get(numeroDoCliente).setNumeroItensCarrinho(
							gerente.repositorioC().get(numeroDoCliente).getNumeroItensCarrinho() + 1);
					DataBase.salvarEstado(gerente);// salva o estado do sistema
					idIncorreto = false;
					break;
				}
				numeroDoItem++;
			}
		} while (idIncorreto);
	}

	private void adicionarItem() {
		gerente.repositorioR().get(numeroRestauranteEscolhido).atualizarListaPedidos();
		String itemEscolhido;
		long idItemEscolhido;
		int i;
		boolean idIncorreto = true;
		do {// pede e le id que o cliente digita
			itemEscolhido = ""
					+ JOptionPane.showInputDialog("digite o numero correspondente ao item que deseja, abaixo listado\n"
							+ gerente.repositorioR().get(numeroRestauranteEscolhido).listarCardapio());
			if (itemEscolhido.equals("null")) {// caso tenha apertado em
												// cancelar a janela fecha
				break;
			}
			i = 0;
			while (i < gerente.repositorioR().get(numeroRestauranteEscolhido).getNumeroPratosCardapio()) {
				/*
				 * confere se o id digitado pelo cliente corresponde a um dos
				 * ids dos pratos
				 */
				if (itemEscolhido.compareTo(String.valueOf(
						gerente.repositorioR().get(numeroRestauranteEscolhido).getPratoCardapio(i).getId())) == 0) {
					idItemEscolhido = Long.parseLong(itemEscolhido);
					numeroDoCliente = 0;
					while (numeroDoCliente < gerente.repositorioC().getNumeroElementos()) {
						/*
						 * identifica o cliete que possui o login e senha
						 * informados
						 */
						if (gerente.repositorioC().get(numeroDoCliente).getLogin().equals(loginDoUsuario)
								&& gerente.repositorioC().get(numeroDoCliente).getSenha().equals(senhaDoUsuario)) {
							break;
						}
						numeroDoCliente++;
					}
					gerente.repositorioC().get(numeroDoCliente).adicionarNoCarrinho(
							gerente.repositorioR().get(numeroRestauranteEscolhido).getPratoCardapio(idItemEscolhido));
					DataBase.salvarEstado(gerente);// salva o estado do sistema
					idIncorreto = false;
					break;
				}
				i++;
			}
		} while (idIncorreto);
		gerente.repositorioR().get(numeroRestauranteEscolhido).atualizarListaPedidos();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cancelarLogin)) {
			cL.show(cards, "Tela Inicial");
			this.setTitle("AppDelivery - Cliente");
			campoLogin.setText("");// campo e apagado
			campoSenhaLogin.setText("");// campo e apagado
		}
		if (e.getSource().equals(cancelarCadastro)) {
			cL.show(cards, "Tela Inicial");
			this.setTitle("AppDelivery - Cliente");
			campoCadastroLogin.setText("");// campo e apagado
			campoCadastroNome.setText("");// campo e apagado
			campoCadastroSenha.setText("");// campo e apagado
		}
		if (e.getSource().equals(okButtonLogin)) {
			int i = 0;
			boolean logado = false;
			ArrayList<Cliente> listaDeClientes = gerente.repositorioC().copiar();
			while (i < gerente.repositorioC().getNumeroElementos()) {
				/*
				 * confere se as informaçoes de login e senha condizem com algum
				 * dos usuarios do repositorio
				 */
				if (listaDeClientes.get(i).getLogin().equals(campoLogin.getText())
						&& listaDeClientes.get(i).getSenha().equals(new String(campoSenhaLogin.getPassword()))) {
					cL.show(cards, "Tela Principal");
					this.setTitle(this.getTitle() + " - " + listaDeClientes.get(i).getLogin());
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
			while (i < gerente.repositorioC().getNumeroElementos()) {
				/*
				 * confere se as inforï¿½ï¿½es de login e/ou nome ja foram
				 * utilizadas
				 */
				if ((campoCadastroLogin.getText().equals(gerente.repositorioC().get(i).getLogin())
						|| campoCadastroNome.getText().equals(gerente.repositorioC().get(i).getNome()))) {
					podeCadastrar = false;
				}
				i++;
			}
			if (podeCadastrar) {
				/*
				 * caso nao tenha sido utilizada pode prosseguir com a execução
				 * do programa
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
						this.setTitle(this.getTitle() + " - " + campoCadastroLogin.getText());// principal
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
			this.setTitle("AppDelivery - Cliente"); // para pagina inicial
		}
		if (e.getSource().equals(opcaoCadastro)) {
			cL.show(cards, "Tela De Cadastro");// ao escolher a opïçao
												// cadastro
												// abre-se a janela de cadastro
		}
		if (e.getSource().equals(opcaoLogin)) {
			if (gerente.repositorioC().getNumeroElementos() != 0) {
				/*
				 * se houver cliente(s) a tela de login e aberta ao ser esta
				 * opiçao
				 */
				cL.show(cards, "Tela De Login");
			} else {
				JOptionPane.showMessageDialog(this, "não é possivel logar, pois não há clientes cadastrados", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(pedir)) {
			gerente = DataBase.lerBaseGerente();
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
				while (i < gerente.repositorioR().getNumeroElementos()) {
					if (restauranteEscolhido.compareTo(String.valueOf(gerente.repositorioR().get(i).getId())) == 0) {
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
			while (i < gerente.repositorioC().getNumeroElementos()) {
				if (gerente.repositorioC().get(i).getLogin().equals(loginDoUsuario)
						&& gerente.repositorioC().get(i).getSenha().equals(senhaDoUsuario)) {
					break;
				}
				i++;
			}
			gerente.removerCliente(i);
			DataBase.salvarEstado(gerente);
		}
		if (e.getSource().equals(efetuarPedido)) {
			if (gerente.repositorioC().get(numeroDoCliente).getNumeroItensCarrinho() > 0) {
				try {
					gerente.repositorioC().get(numeroDoCliente).efetuarPedido(idRestauranteEscolhido);
					JOptionPane.showMessageDialog(this, "Pedido efetuado com sucesso!!","Sucesso",JOptionPane.DEFAULT_OPTION);
				} catch (RepositorioCheioException e1) {
					JOptionPane.showMessageDialog(this, "Este restaurante atingiu o numero maximo de pedidos", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (IdInvalidoException | SenhaInvalidaException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "não há itens fazer pedido", "Erro", JOptionPane.ERROR_MESSAGE,
						null);
			}
		}
		if (e.getSource().equals(adicionarItem)) {
			adicionarItem();
		}
		if (e.getSource().equals(removerItem)) {
			if (gerente.repositorioC().get(numeroDoCliente).getNumeroItensCarrinho() != 0) {
				removerItem();
			} else {
				JOptionPane.showMessageDialog(null, "não há itens para remover", "Erro", JOptionPane.ERROR_MESSAGE,
						null);
			}
		}
		if (e.getSource().equals(voltar)) {
			cL.show(cards, "Tela Principal");
		}
		if (e.getSource().equals(cancelarPedido)) {
			if (gerente.repositorioR().get(numeroRestauranteEscolhido).getNumeroPedidosEspera() != 0) {
				gerente.repositorioR().get(numeroRestauranteEscolhido).cancelarPedido(gerente.repositorioR().get(numeroRestauranteEscolhido).getNumeroPedidosEspera());
				JOptionPane.showMessageDialog(this, "Seu pedido foi cancelado","Sucesso",JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "não há pedido para cancelar", "Erro", JOptionPane.ERROR_MESSAGE,
						null);
			}
		}
	}
}