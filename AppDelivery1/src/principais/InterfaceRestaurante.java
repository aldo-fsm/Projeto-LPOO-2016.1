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

import entidades.ItemCardapio;
import entidades.Restaurante;

public class InterfaceRestaurante extends JFrame implements ActionListener {

	Restaurante restaurante = new Restaurante("aaa", "bbb", "ccc");//temporario(teste)

	private JButton loginOkButton;
	private JButton cadastrarItemButton;
	private JButton removerItemButton;
	private JButton listarPedidosButton;
	private JButton logoutButton;
	private JTextField campoTextoLogin;
	private JPasswordField campoSenhaLogin;
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

		telas.add(telaLogado, "logado");
		telas.add(telaInicial, "tela inicial");

		add(telas);

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
				return true;
			}
		} catch (NumberFormatException e) {

			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void removerItem() {

	}

	public static void main(String[] args) {
		new InterfaceRestaurante();
	}

	public void listarPedidos() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(loginOkButton)) {

			
			
			cL.show(telas, "logado");
			campoTextoLogin.setText(null);
			campoSenhaLogin.setText(null);

		}
		if (e.getSource().equals(logoutButton)) {
			cL.show(telas, "tela inicial");
		}

		if (e.getSource().equals(cadastrarItemButton)) {
			if (!cadastrarItem()) {

				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o item", "Erro",
						JOptionPane.ERROR_MESSAGE);

			}

		}
		if (e.getSource().equals(removerItemButton)) {
			removerItem();
		}
		if (e.getSource().equals(listarPedidosButton)) {
			listarPedidos();
		}

	}
}
