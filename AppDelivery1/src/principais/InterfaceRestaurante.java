package principais;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dados.DataBase;
import entidades.ItemCardapio;
import entidades.Restaurante;
import repositorios.RepositorioPedido;
import repositorios.RepositorioRestaurante;

public class InterfaceRestaurante extends JFrame implements ActionListener {

	private Restaurante restaurante;
	private RepositorioRestaurante repositorio;
	private RepositorioPedido pedidos;

	private JButton loginOkButton;
	private JButton cadastrarItemButton;
	private JButton removerItemButton;
	private JButton removerOkButton;
	private JButton removerCancelarButton;
	private JButton listarPedidosButton;
	private JButton logoutButton;
	private JButton listarVoltarButton;

	private JTextArea areaTextoPedidos;

	private JTextField campoTextoLogin;
	private JPasswordField campoSenhaLogin;
	private JComboBox<ItemCardapio> removerItemComboBox;
	private CardLayout cL = new CardLayout();
	private JPanel telas = new JPanel(cL);

	public InterfaceRestaurante() {

		super("AppDelivery - Restaurante");

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);

		JPanel telaInicial = new JPanel(null);
		JPanel telaLogado = new JPanel(null);
		JPanel telaRemover = new JPanel(null);
		JPanel telaListarPedidos = new JPanel(null);

		telas.add(telaLogado, "logado");
		telas.add(telaInicial, "tela inicial");
		telas.add(telaRemover, "tela remover");
		telas.add(telaListarPedidos, "tela listar pedidos");

		add(telas);

		// tela inicial
		campoTextoLogin = new JTextField();
		campoSenhaLogin = new JPasswordField();
		loginOkButton = new JButton("OK");
		loginOkButton.addActionListener(this);
		JLabel loginNome = new JLabel("Login :");
		JLabel loginSenha = new JLabel("Senha :");
		campoTextoLogin.setBounds(300, 350, 200, 30);
		campoSenhaLogin.setBounds(300, 400, 200, 30);
		loginNome.setBounds(250, 350, 50, 30);
		loginSenha.setBounds(250, 400, 50, 30);
		loginOkButton.setBounds(350, 450, 100, 30);
		telaInicial.add(campoTextoLogin);
		telaInicial.add(campoSenhaLogin);
		telaInicial.add(loginNome);
		telaInicial.add(loginSenha);
		telaInicial.add(loginOkButton);

		// tela logado
		cadastrarItemButton = new JButton("Adicionar Item no Cardapio");
		removerItemButton = new JButton("Remover Item do Cardapio");
		listarPedidosButton = new JButton("Listar Pedidos em Espera");
		logoutButton = new JButton("Logout");

		cadastrarItemButton.addActionListener(this);
		removerItemButton.addActionListener(this);
		listarPedidosButton.addActionListener(this);
		logoutButton.addActionListener(this);

		cadastrarItemButton.setBounds(290, 280, 220, 50);
		removerItemButton.setBounds(290, 350, 220, 50);
		listarPedidosButton.setBounds(290, 420, 220, 50);
		logoutButton.setBounds(300, 500, 200, 30);
		telaLogado.add(cadastrarItemButton);
		telaLogado.add(removerItemButton);
		telaLogado.add(listarPedidosButton);
		telaLogado.add(logoutButton);

		// tela de remocao de item
		removerOkButton = new JButton("Remover");
		removerCancelarButton = new JButton("Cancelar");
		removerItemComboBox = new JComboBox<ItemCardapio>();

		removerOkButton.addActionListener(this);
		removerCancelarButton.addActionListener(this);
		removerItemComboBox.addActionListener(this);

		removerItemComboBox.setBounds(290, 280, 220, 30);
		removerOkButton.setBounds(290, 500, 100, 30);
		removerCancelarButton.setBounds(410, 500, 100, 30);

		telaRemover.add(removerOkButton);
		telaRemover.add(removerCancelarButton);
		telaRemover.add(removerItemComboBox);

		// tela listar pedidos

		areaTextoPedidos = new JTextArea();
		listarVoltarButton = new JButton("Voltar");
		listarVoltarButton.addActionListener(this);
		areaTextoPedidos.setBounds(200, 200, 400, 200);
		areaTextoPedidos.setEditable(false);
		listarVoltarButton.setBounds(350, 500, 100, 30);
		telaListarPedidos.add(areaTextoPedidos);
		telaListarPedidos.add(listarVoltarButton);

		cL.show(telas, "tela inicial");
	}

	public boolean cadastrarItem() {

		try {

			String nome = JOptionPane.showInputDialog("Digite o nome do novo item");

			if (nome == (null)) {
				return true;
			} else if (nome.equals("")) {
				return false;
			} else {
				double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do item"));
				ItemCardapio item = new ItemCardapio(nome, preco);
				restaurante.adicionarPrato(item);
				atualizarDataBase();
				return true;
			}
		} catch (NumberFormatException e) {

			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void atualizarDataBase() {
		DataBase.salvarEstado(repositorio);
	}

	public void atualizarListaPedidos() {
		pedidos = DataBase.lerBasePedidos();
		for (int i = 0; i < pedidos.getNumeroPedidos(); i++) {
			if (pedidos.getPedido(i).getIdRestaurate() != restaurante.getId()) {
				pedidos.remover(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(loginOkButton)) {
			repositorio = DataBase.LerBaseRestaurantes();
			String login = campoTextoLogin.getText();
			String senha = campoSenhaLogin.getText();
			for (int i = 0; i < repositorio.getNumeroRestaurantes(); i++) {
				restaurante = repositorio.getRestaurante(i);
				if (restaurante.getLogin().equals(login) && restaurante.getSenha().equals(senha)) {
					break;
				}
				restaurante = null;
			}
			if (restaurante != null) {
				cL.show(telas, "logado");
				this.setTitle(this.getTitle() + " - " + restaurante.getLogin());
				campoSenhaLogin.setText(null);
			} else {
				JOptionPane.showMessageDialog(this, "Senha ou Login incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
				campoSenhaLogin.setText(null);
			}

		}
		if (e.getSource().equals(logoutButton)) {
			cL.show(telas, "tela inicial");
			this.setTitle("AppDelivery - Restaurante");
		}

		if (e.getSource().equals(cadastrarItemButton)) {
			if (!cadastrarItem()) {

				JOptionPane.showMessageDialog(null, "N�o foi possivel cadastrar o item", "Erro",
						JOptionPane.ERROR_MESSAGE);

			}

		}

		if (e.getSource().equals(removerItemButton)) {
			if (restaurante.getNumeroPratosCardapio() != 0) {
				removerItemComboBox.removeAllItems();
				for (int i = 0; i < restaurante.getNumeroPratosCardapio(); i++) {
					removerItemComboBox.addItem(restaurante.getPratoCardapio(i));
				}
				cL.show(telas, "tela remover");
			} else {
				JOptionPane.showMessageDialog(this, "N�o h� nenhum item cadastrado", "Erro",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		}

		if (e.getSource().equals(listarPedidosButton)) {
			atualizarListaPedidos();
			if (pedidos.getNumeroPedidos() > 0) {
				for (int i = 0; i < pedidos.getNumeroPedidos(); i++) {
					areaTextoPedidos.append("\n" + pedidos.getPedido(i));
				}
				cL.show(telas, "tela listar pedidos");
			} else {
				JOptionPane.showMessageDialog(this, "N�o h� nenhum Pedido", "Erro",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		}

		if (e.getSource().equals(removerOkButton)) {
			restaurante.removerPrato(removerItemComboBox.getSelectedIndex());
			atualizarDataBase();
			cL.show(telas, "logado");
		}

		if (e.getSource().equals(removerCancelarButton)) {
			cL.show(telas, "logado");
		}
		if (e.getSource().equals(listarVoltarButton)) {
			cL.show(telas, "logado");
			areaTextoPedidos.setText(null);
		}

	}

	public static void main(String[] args) {
		new InterfaceRestaurante();
	}
}
