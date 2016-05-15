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

	//atributos de interface
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
	private JButton sair = new JButton("Deixar De ser Cliente");
	private JTextField campoLogin = new JTextField();
	private JPasswordField campoSenhaLogin = new JPasswordField();
	private JTextField campoCadastroLogin = new JTextField();
	private JPasswordField campoCadastroSenha = new JPasswordField();
	//atributos de escolha do cliente e carregamento do gerente
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
		OkButtonLogin.setBounds(350, 450, 100, 30);
		OkButtonLogin.addActionListener(this);
		telaLogin.add(OkButtonLogin);
	}

	private void janelaPedido() {
		JPanel telaPedir = new JPanel(null);
		cards.add(telaPedir, "Efetuar Pedido");
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

	private void janelaCadastro() {
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

	private void janelaInicial() {
		JPanel telaInicial = new JPanel(null);
		cards.add(telaInicial, "Tela Inicial");
		opcaoCadastro.setBounds(290, 420, 220, 50);
		opcaoCadastro.addActionListener(this);
		telaInicial.add(opcaoCadastro);
		opcaoLogin.setBounds(290, 350, 220, 50);
		opcaoLogin.addActionListener(this);
		telaInicial.add(opcaoLogin);
	}

	//metodo apresenta toda a interface do cliente
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
	}

	private void removerItem(){
		String itemRemovido;
		boolean idIncorreto = true;
		int numeroDoItem;
		do {
			//pede e le id que o cliente digita
			itemRemovido = "" + JOptionPane.showInputDialog(
					"digite o numero correspondente ao item que deseja remover, abaixo, listado\n" + gerente.repositorioC().getCliente(numeroDoCliente).listarCarrinho());//lista o cardapio do restaurante de id escolhido
			if (itemRemovido.equals("null")) {//caso tenha apertado em cancelar a janela fecha
				break;
			}
			numeroDoItem = 0;
			while (numeroDoItem < gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho()) {//confere se o id digitado pelo cliente corresponde a um dos ids dos pratos
				if (itemRemovido
						.compareTo(String.valueOf(gerente.repositorioC().getCliente(numeroDoCliente).getCarrinho(numeroDoItem).getId())) == 0) {
					gerente.repositorioC().getCliente(numeroDoCliente).removerDoCarrinho(numeroDoItem);
					gerente.repositorioC().getCliente(numeroDoCliente).setNumeroItensCarrinho(gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho()+1);
					DataBase.salvarEstado(gerente);//salva o estado do sistema
					idIncorreto = false;
					break;
				}
				numeroDoItem++;
			}
		} while (idIncorreto);
	}
	
	private void adicionarItem(){
		String itemEscolhido;
		long idItemEscolhido;
		int i;
		boolean idIncorreto = true;
		do {//pede e le id que o cliente digita
			itemEscolhido = "" + JOptionPane.showInputDialog(
					"digite o numero correspondente ao item que deseja, abaixo listado\n" + gerente.repositorioR().listarCardapio(idRestauranteEscolhido));
			if (itemEscolhido.equals("null")) {//caso tenha apertado em cancelar a janela fecha
				break;
			}
			i = 0;
			while (i < gerente.repositorioR().getRestaurante(numeroRestauranteEscolhido).getNumeroPratosCardapio()) {//confere se o id digitado pelo cliente corresponde a um dos ids dos pratos
				if (itemEscolhido
						.compareTo(String.valueOf(gerente.repositorioR().getRestaurante(numeroRestauranteEscolhido).getPratoCardapio(i).getId())) == 0) {
					idItemEscolhido = Long.parseLong(itemEscolhido);
					numeroDoCliente = 0;
					while(numeroDoCliente<gerente.repositorioC().getNumeroClientes()){//identifica o cliete que possui o login e senha informados
						if(gerente.repositorioC().getCliente(numeroDoCliente).getLogin().equals(loginDoUsuario)&&gerente.repositorioC().getCliente(numeroDoCliente).getSenha().equals(senhaDoUsuario)){
							break;
						}
						numeroDoCliente++;
					}
					gerente.repositorioC().getCliente(numeroDoCliente).adicionarNoCarrinho(gerente.repositorioR().getRestaurante(numeroRestauranteEscolhido).getPratoCardapio(idItemEscolhido));
					DataBase.salvarEstado(gerente);//salva o estado do sistema
					idIncorreto = false;
					break;
				}
				i++;
			}
		} while (idIncorreto);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(OkButtonLogin)) {
			int i = 0;
			boolean logado = false;
			Cliente[] listaDeClientes = gerente.repositorioC().getCliente();
			while (i < gerente.repositorioC().getNumeroClientes()) {// confere se as informaçoes de login e senha condizem com algum dos usuarios do repositorio
				if (listaDeClientes[i].getLogin().equals(campoLogin.getText())
						&& listaDeClientes[i].getSenha().equals(campoSenhaLogin.getText())) {
					cL.show(cards, "Tela Principal");// se condiz a tela principal e aberta
					loginDoUsuario = campoLogin.getText();// o login e salvo caso o usuario deseje deixar de ser cliente
					senhaDoUsuario = campoSenhaLogin.getText();// assim como a senha
					campoLogin.setText("");// campo e apagado
					campoSenhaLogin.setText("");// campo e apagado
					logado = true;// informações validas
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
		if (e.getSource().equals(OkButtonCadastro)) {
			int i = 0;
			boolean podeCadastrar = true;
			while (i < gerente.repositorioC().getNumeroClientes()) {// confere se as inforções de login e/ou nome ja foram utilizadas
				if ((campoCadastroLogin.getText().equals(gerente.repositorioC().getCliente(i).getLogin())
						|| campoCadastroNome.getText().equals(gerente.repositorioC().getCliente(i).getNome()))) {
					podeCadastrar = false;
				}
				i++;
			}
			if (podeCadastrar) {// caso nao tenha sido utilizada pode prosseguir com a execução do programa
				if (!campoCadastroLogin.getText().equals("") && !campoCadastroNome.getText().equals("")
						&& !campoCadastroSenha.getText().equals("")) {// confere se os campos estão vazios
					cL.show(cards, "Tela Principal");// vai para tela principal
					gerente.adicionarCliente(new Cliente(campoCadastroLogin.getText(), campoCadastroNome.getText(),
							campoCadastroSenha.getText())); // adiciona um novo cliente no repositorio do gerente informado
					loginDoUsuario = campoCadastroLogin.getText();// salva na classe o login do cliente
					senhaDoUsuario = campoCadastroSenha.getText();// salva na classe o senha do cliente
					campoCadastroLogin.setText("");// apaga os campos de texto
					campoCadastroNome.setText("");// apaga os campos de texto
					campoCadastroSenha.setText("");// apaga os campos de texto
					DataBase.salvarEstado(gerente);// salva o estado do sistema apos adicionado o cliente
				} else {
					JOptionPane.showMessageDialog(this, "Um ou mais campos esta(o) vazio(s)", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Informações ja utilizadas", "Erro", JOptionPane.ERROR_MESSAGE);
				campoCadastroLogin.setText("");// apaga os campos de texto
				campoCadastroNome.setText("");// apaga os campos de texto
				campoCadastroSenha.setText("");// apaga os campos de texto
			}
		}
		if (e.getSource().equals(logoutButton)) {
			cL.show(cards, "Tela Inicial");// ao ser deslogado o usuario volta para pagina inicial
		}
		if (e.getSource().equals(opcaoCadastro)) {
			cL.show(cards, "Tela De Cadastro");// ao escolher a opção cadastro abre-se a janela de cadastro
		}
		if (e.getSource().equals(opcaoLogin)) {
			if (gerente.repositorioC().getNumeroClientes() != 0) {// se houver cliente(s) a tela de login e aberta ao ser esta opção
				cL.show(cards, "Tela De Login");
			} else {
				JOptionPane.showMessageDialog(this, "não e possivel logar, pois nao há clientes cadastrados", "Erro",
						JOptionPane.ERROR_MESSAGE);
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
			if(gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho()>0){
			gerente.repositorioC().getCliente(numeroDoCliente).efetuarPedido(idRestauranteEscolhido);
			cL.show(cards, "Tela Inicial");
			}else{
				JOptionPane.showMessageDialog(null, "não há itens fazer pedido", "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}
		if (e.getSource().equals(adicionarItem)) {
			adicionarItem();
		}
		if (e.getSource().equals(removerItem)) {
			if(gerente.repositorioC().getCliente(numeroDoCliente).getNumeroItensCarrinho()!=0){
			removerItem();
			}else{
				JOptionPane.showMessageDialog(null, "não há itens para remover", "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
}