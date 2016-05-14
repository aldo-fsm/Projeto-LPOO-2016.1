package principais;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.DataBase;
import entidades.Cliente;
import entidades.Gerente;
import entidades.ItemCardapio;
import entidades.Restaurante;

public class InterfaceGraficaGerente extends JFrame implements ActionListener {

	private JButton cadastrarClienteButton;
	private JButton removerClienteButton;
	private JButton cadastrarRestauranteButton;
	private JButton removerRestauranteButton;
	private JButton adicionarPratoButton;
	private JButton removerPratoButton;

	private Gerente gerente;

	public InterfaceGraficaGerente() {

		super("AppDelivery - Gerente");

		gerente = DataBase.lerBaseGerente();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);
		setResizable(false);

		cadastrarClienteButton = new JButton("Cadastrar Cliente");
		removerClienteButton = new JButton("Remover Cliente");
		cadastrarRestauranteButton = new JButton("Cadastrar Restaurante");
		removerRestauranteButton = new JButton("remover Restauramte");
		adicionarPratoButton = new JButton("Adicionar Prato");
		removerPratoButton = new JButton("Remover Prato");

		cadastrarClienteButton.addActionListener(this);
		removerClienteButton.addActionListener(this);
		cadastrarRestauranteButton.addActionListener(this);
		removerRestauranteButton.addActionListener(this);
		adicionarPratoButton.addActionListener(this);
		removerPratoButton.addActionListener(this);

		cadastrarClienteButton.setBounds(290, 250, 220, 30);
		removerClienteButton.setBounds(290, 290, 220, 30);
		cadastrarRestauranteButton.setBounds(290, 340, 220, 30);
		removerRestauranteButton.setBounds(290, 380, 220, 30);
		adicionarPratoButton.setBounds(290, 430, 220, 30);
		removerPratoButton.setBounds(290, 470, 220, 30);

		add(cadastrarClienteButton);
		add(removerClienteButton);
		add(cadastrarRestauranteButton);
		add(removerRestauranteButton);
		add(adicionarPratoButton);
		add(removerPratoButton);

	}

	public void atualizarDataBase() {
		DataBase.salvarEstado(gerente);
	}

	public boolean cadastrarCliente() {

		String nome = JOptionPane.showInputDialog("Digite o Nome do Cliente");
		if (nome == null || nome.equals("")) {
			return false;
		} else if (nome.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		}

		String login = JOptionPane.showInputDialog("Digite o Login do Cliente");
		if (login == null || login.equals("")) {
			return false;
		} else if (login.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioC().getNumeroClientes(); i++) {
				if (gerente.repositorioC().getCliente(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login não está disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Cliente");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		}

		gerente.adicionarCliente(new Cliente(login, senha, nome));
		atualizarDataBase();
		return true;

	}

	public boolean cadastrarRestaurante() {
		String nome = JOptionPane.showInputDialog("Digite o Nome do Restaurante");
		if (nome == null || nome.equals("")) {
			return false;
		} else if (nome.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		}

		String login = JOptionPane.showInputDialog("Digite o Login do Restaurante");
		if (login == null || login.equals("")) {
			return false;
		} else if (login.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioR().getNumeroRestaurantes(); i++) {
				if (gerente.repositorioR().getRestaurante(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login não está disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Restaurante");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		}

		gerente.adicionarRestaurante(new Restaurante(login, senha, nome));
		atualizarDataBase();
		return true;
	}

	public boolean adicionarPrato() {
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 300));

		JLabel label1 = new JLabel("Escolha um restaurante");
		JLabel label2 = new JLabel("Dados do Prato :");
		JLabel label3 = new JLabel("Nome");
		JLabel label4 = new JLabel("Preço");
		JComboBox<String> cb = new JComboBox<String>();
		JTextField campoTextoNome = new JTextField();
		JTextField campoTextoPreco = new JTextField();

		cb.setBackground(Color.WHITE);
		campoTextoNome.setBounds(50, 150, 300, 20);
		campoTextoPreco.setBounds(50, 180, 300, 20);
		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);
		label2.setBounds(50, 90, 300, 20);
		label3.setBounds(10, 150, 300, 20);
		label4.setBounds(10, 180, 300, 20);
		for (int i = 0; i < gerente.repositorioR().getNumeroRestaurantes(); i++) {
			Restaurante restaurante = gerente.repositorioR().getRestaurante(i);
			cb.addItem("Nome : " + restaurante.getNome() + ", " + "id : " + restaurante.getId());
		}
		painel.add(cb);
		painel.add(label1);
		painel.add(label2);
		painel.add(label3);
		painel.add(label4);
		painel.add(campoTextoNome);
		painel.add(campoTextoPreco);
		int n = JOptionPane.showOptionDialog(null, painel, "Adicionar prato", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			try{
			String nome = campoTextoNome.getText();
			Double preco = Double.parseDouble(campoTextoPreco.getText());

			if (nome == null || nome.equals("")) {
				return false;
			} else if (nome.contains(";") || nome.contains("/")) {
				JOptionPane.showMessageDialog(this, " \" ; \" e \" / \" não são caracteres válidos");
				return false;
			}
			gerente.adicionarPrato(cb.getSelectedIndex(), new ItemCardapio(nome, preco));
			atualizarDataBase();
			return true;
			}catch(NumberFormatException e){
				return false;
			}
			
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		new InterfaceGraficaGerente();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(cadastrarClienteButton)) {
			if (!cadastrarCliente()) {
				JOptionPane.showMessageDialog(this, "Não foi possivel cadastrar o Cliente", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(cadastrarRestauranteButton)) {
			if (!cadastrarRestaurante()) {
				JOptionPane.showMessageDialog(this, "Não foi possivel cadastrar o Restaurante", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(adicionarPratoButton)) {
			if (!adicionarPrato()) {
				JOptionPane.showMessageDialog(this, "Não foi possivel cadastrar o prato", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
