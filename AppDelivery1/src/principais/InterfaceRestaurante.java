package principais;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dados.DataBase;
import entidades.ItemCardapio;
import entidades.Pedido;
import entidades.Restaurante;
import repositorios.Repositorio;

public class InterfaceRestaurante extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Restaurante restaurante;
	private Repositorio<Restaurante> repositorio;

	private JButton loginOkButton;
	private JButton cadastrarItemButton;
	private JButton removerItemButton;
	private JButton removerOkButton;
	private JButton removerCancelarButton;
	private JButton listarPedidosButton;
	private JButton logoutButton;
	private JButton listarVoltarButton;
	private JButton ConfirmarEnvioButton;
	private JButton CancelarPedidoButton;

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
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("icone.png");  
		this.setIconImage(iconeTitulo);
		
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
		loginOkButton.setBackground(new Color(150, 250, 250));
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
		telaInicial.setBackground(new Color(130, 210, 135));

		// tela logado
		ConfirmarEnvioButton = new JButton("Confirmar Envio");
		ConfirmarEnvioButton.setBackground(new Color(150, 250, 250));
		CancelarPedidoButton = new JButton("Cancelar Pedido");
		CancelarPedidoButton.setBackground(new Color(150, 250, 250));
		cadastrarItemButton = new JButton("Adicionar Item no Cardapio");
		cadastrarItemButton.setBackground(new Color(150, 250, 250));
		removerItemButton = new JButton("Remover Item do Cardapio");
		removerItemButton.setBackground(new Color(150, 250, 250));
		listarPedidosButton = new JButton("Listar Pedidos em Espera");
		listarPedidosButton.setBackground(new Color(150, 250, 250));
		logoutButton = new JButton("Logout");

		ConfirmarEnvioButton.addActionListener(this);
		CancelarPedidoButton.addActionListener(this);
		cadastrarItemButton.addActionListener(this);
		removerItemButton.addActionListener(this);
		listarPedidosButton.addActionListener(this);
		logoutButton.addActionListener(this);

		ConfirmarEnvioButton.setBounds(290, 140, 220, 50);
		CancelarPedidoButton.setBounds(290, 210, 220, 50);
		cadastrarItemButton.setBounds(290, 280, 220, 50);
		removerItemButton.setBounds(290, 350, 220, 50);
		listarPedidosButton.setBounds(290, 420, 220, 50);
		logoutButton.setBounds(300, 500, 200, 30);
		logoutButton.setBackground(new Color(150, 250, 250));
		
		telaLogado.add(ConfirmarEnvioButton);
		telaLogado.add(CancelarPedidoButton);
		telaLogado.add(cadastrarItemButton);
		telaLogado.add(removerItemButton);
		telaLogado.add(listarPedidosButton);
		telaLogado.add(logoutButton);
		telaLogado.setBackground(new Color(130, 210, 135));

		// tela de remocao de item
		removerOkButton = new JButton("Remover");
		removerCancelarButton = new JButton("Cancelar");
		removerItemComboBox = new JComboBox<ItemCardapio>();

		removerOkButton.addActionListener(this);
		removerOkButton.setBackground(new Color(150, 250, 250));
		removerCancelarButton.addActionListener(this);
		removerCancelarButton.setBackground(new Color(150, 250, 250));
		removerItemComboBox.addActionListener(this);
		removerItemComboBox.setBackground(Color.WHITE);

		removerItemComboBox.setBounds(290, 280, 220, 30);
		removerOkButton.setBounds(290, 500, 100, 30);
		removerOkButton.setBackground(new Color(150, 250, 250));
		removerCancelarButton.setBounds(410, 500, 100, 30);
		
		telaRemover.add(removerOkButton);
		telaRemover.add(removerCancelarButton);
		telaRemover.add(removerItemComboBox);
		telaRemover.setBackground(new Color(130, 210, 135));

		// tela listar pedidos

		areaTextoPedidos = new JTextArea();
		JScrollPane scroll = new JScrollPane(areaTextoPedidos);
		listarVoltarButton = new JButton("Voltar");
		listarVoltarButton.addActionListener(this);
		scroll.setBounds(200, 200, 400, 200);
		areaTextoPedidos.setEditable(false);
		listarVoltarButton.setBounds(350, 500, 100, 30);
		telaListarPedidos.add(scroll);
		telaListarPedidos.add(listarVoltarButton);
		telaListarPedidos.setBackground(new Color(130, 210, 135));
		cL.show(telas, "tela inicial");
		setResizable(false);
	}

	public void confirmarEnvio() {
		restaurante.atualizarListaPedidos();
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 150));

		JLabel label1 = new JLabel("Escolha o pedido a ser confirmado");
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBackground(Color.WHITE);

		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);

		painel.add(cb);
		painel.add(label1);

		for (int i = 0; i < restaurante.getNumeroPedidosEspera(); i++) {
			Pedido pedido = restaurante.getPedidoEspera(i);
			cb.addItem("id : " + pedido.getIdPedido() + ",  id do cliente : " + pedido.getIdCliente());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Confirmar Envio", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			Pedido pedido = restaurante.getPedidoEspera(cb.getSelectedIndex());
			Repositorio<Pedido> repositorioPedidos = DataBase.lerBasePedidos();
			for (int i = 0; i < repositorioPedidos.getNumeroElementos(); i++) {
				if (repositorioPedidos.get(i).getIdPedido() == pedido.getIdPedido()) {
					restaurante.confirmarEnvio(i);
				}
			}
		}
	}

	public void cancelarPedido() {
		restaurante.atualizarListaPedidos();
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 150));

		JLabel label1 = new JLabel("Escolha o pedido a ser cancelado");
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBackground(Color.WHITE);

		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);

		painel.add(cb);
		painel.add(label1);

		for (int i = 0; i < restaurante.getNumeroPedidosEspera(); i++) {
			Pedido pedido = restaurante.getPedidoEspera(i);
			cb.addItem("id : " + pedido.getIdPedido() + ",  id do cliente : " + pedido.getIdCliente());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Cancelar Pedido", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			Pedido pedido = restaurante.getPedidoEspera(cb.getSelectedIndex());
			Repositorio<Pedido> repositorioPedidos = DataBase.lerBasePedidos();
			for (int i = 0; i < repositorioPedidos.getNumeroElementos(); i++) {
				if (repositorioPedidos.get(i).getIdPedido() == pedido.getIdPedido()) {
					restaurante.cancelarPedido(i);
				}
			}
		}
	}

	public boolean cadastrarItem() {

		try {
			String nome = JOptionPane.showInputDialog(this, "Digite o nome do novo item");
			if (!nome.contains(";")) {
				if (nome.equals("")) {
					return false;
				}
				double preco = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o preco do item"));
				ItemCardapio item = new ItemCardapio(nome, preco);
				restaurante.adicionarPrato(item);
				atualizarDataBase();
				return true;
			} else {
				JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere vï¿½lido");
				return false;
			}

		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void atualizarDataBase() {
		DataBase.salvarEstadoRestaurante(repositorio);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(loginOkButton)) {
			repositorio = DataBase.LerBaseRestaurantes();
			String login = campoTextoLogin.getText();
			String senha = new String(campoSenhaLogin.getPassword());
			for (int i = 0; i < repositorio.getNumeroElementos(); i++) {
				restaurante = repositorio.get(i);
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

				JOptionPane.showMessageDialog(this, "Nao foi possivel cadastrar o item", "Erro",
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
				JOptionPane.showMessageDialog(this, "Nao ha nenhum item cadastrado", "Erro",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		}

		if (e.getSource().equals(listarPedidosButton)) {
			restaurante.atualizarListaPedidos();
			if (restaurante.getNumeroPedidosEspera() > 0) {
				Pedido pedido;
				for (int i = 0; i < restaurante.getNumeroPedidosEspera(); i++) {
					pedido = restaurante.getPedidoEspera(i);
					areaTextoPedidos.append("  " + "Id do pedido : " + pedido.getIdPedido() + "\n  Id do cliente : "
							+ pedido.getIdCliente() + "\n  Itens : " + pedido.listarItens() + "\n  Status : "
							+ pedido.getStatus() + "\n\n");
				}
				cL.show(telas, "tela listar pedidos");
			} else {
				JOptionPane.showMessageDialog(this, "Nao ha nenhum Pedido", "Erro", JOptionPane.INFORMATION_MESSAGE,
						null);
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
		if (e.getSource().equals(ConfirmarEnvioButton)) {
			confirmarEnvio();
		}
		if (e.getSource().equals(CancelarPedidoButton)) {
			cancelarPedido();
		}

	}

	public static void main(String[] args) {
		new InterfaceRestaurante();
	}
}
