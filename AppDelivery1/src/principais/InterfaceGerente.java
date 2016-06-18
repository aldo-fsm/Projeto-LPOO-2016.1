package principais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public class InterfaceGerente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton cadastrarClienteButton;
	private JButton removerClienteButton;
	private JButton cadastrarRestauranteButton;
	private JButton removerRestauranteButton;
	private JButton adicionarPratoButton;
	private JButton removerPratoButton;

	private Gerente gerente;

	public InterfaceGerente() {

		super("AppDelivery - Gerente");

		atualizarGerente();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(null);

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
		
		cadastrarClienteButton.setBackground(new Color(150, 250, 250));
		removerClienteButton.setBackground(new Color(150, 250, 250));
		cadastrarRestauranteButton.setBackground(new Color(150, 250, 250));
		removerRestauranteButton.setBackground(new Color(150, 250, 250));
		adicionarPratoButton.setBackground(new Color(150, 250, 250));
		removerPratoButton.setBackground(new Color(150, 250, 250));

		add(cadastrarClienteButton);
		add(removerClienteButton);
		add(cadastrarRestauranteButton);
		add(removerRestauranteButton);
		add(adicionarPratoButton);
		add(removerPratoButton);

		getContentPane().setBackground(new Color(130, 210, 135));
		getContentPane().setSize(800, 600);
		setResizable(false);
	}

	public void atualizarDataBase() {
		DataBase.salvarEstado(gerente);
	}

	public boolean cadastrarCliente() {

		String nome = JOptionPane.showInputDialog("Digite o Nome do Cliente");
		if (nome == null || nome.equals("")) {
			return false;
		} else if (nome.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere valido");
			return false;
		}

		String login = JOptionPane.showInputDialog("Digite o Login do Cliente");
		if (login == null || login.equals("")) {
			return false;
		} else if (login.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere válido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioC().getNumeroElementos(); i++) {
				if (gerente.repositorioC().get(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login não está disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Cliente");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere valido");
			return false;
		}

		try {
			gerente.adicionarCliente(new Cliente(login, senha, nome));
			atualizarDataBase();
			return true;
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (SenhaInvalidaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (RepositorioCheioException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

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
			JOptionPane.showMessageDialog(this, " \" ; \" não é um caractere valido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioR().getNumeroElementos();i++) {
				if (gerente.repositorioR().get(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login nao esta disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Restaurante");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" nao e um caractere valido");
			return false;
		}

		try {
			gerente.adicionarRestaurante(new Restaurante(login, senha, nome));
			atualizarDataBase();
			return true;
		} catch (IdInvalidoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (SenhaInvalidaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (RepositorioCheioException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	public void atualizarGerente() {
		gerente = DataBase.lerBaseGerente();
	}

	public boolean adicionarPrato() {
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 300));

		JLabel label1 = new JLabel("Escolha um Restaurante");
		JLabel label2 = new JLabel("Dados do Prato :");
		JLabel label3 = new JLabel("Nome");
		JLabel label4 = new JLabel("Preco");
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
		for (int i = 0; i < gerente.repositorioR().getNumeroElementos(); i++) {
			Restaurante restaurante = gerente.repositorioR().get(i);
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
			try {
				String nome = campoTextoNome.getText();
				Double preco = Double.parseDouble(campoTextoPreco.getText());

				if (nome == null || nome.equals("")) {
					return false;
				} else if (nome.contains(";") || nome.contains("/")) {
					JOptionPane.showMessageDialog(this, " \" ; \" e \" / \" nao sao caracteres validos");
					return false;
				}
				gerente.adicionarPrato(cb.getSelectedIndex(), new ItemCardapio(nome, preco));
				atualizarDataBase();
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public void removerPrato() {
		atualizarGerente();
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 150));

		JLabel label1 = new JLabel("Escolha um Restaurante");
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBackground(Color.WHITE);

		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);

		painel.add(cb);
		painel.add(label1);

		for (int i = 0; i < gerente.repositorioR().getNumeroElementos(); i++) {
			Restaurante restaurante = gerente.repositorioR().get(i);
			cb.addItem("Nome : " + restaurante.getNome() + ",  id : " + restaurante.getId());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Remover Prato", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			int iRest = cb.getSelectedIndex();
			Restaurante restaurante = gerente.repositorioR().get(iRest);
			if (restaurante.getNumeroPratosCardapio() != 0) {
				label1.setText("Escolha o item a ser removido");
				cb.removeAllItems();
				for (int i = 0; i < restaurante.getNumeroPratosCardapio(); i++) {
					ItemCardapio item = restaurante.getPratoCardapio(i);
					cb.addItem("Nome : " + item.getNome() + ",  id : " + item.getId());
				}
				n = JOptionPane.showOptionDialog(null, painel, "Remover Prato", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (n == 0) {
					gerente.removerPrato(iRest, cb.getSelectedIndex());
					atualizarDataBase();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Este restaurante nao possui nenhum prato");
			}
		}

	}

	public void removerRestaurante() {
		atualizarGerente();
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 150));

		JLabel label1 = new JLabel("Escolha o restaurante a ser removido");
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBackground(Color.WHITE);

		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);

		painel.add(cb);
		painel.add(label1);

		for (int i = 0; i < gerente.repositorioR().getNumeroElementos(); i++) {
			Restaurante restaurante = gerente.repositorioR().get(i);
			cb.addItem("Nome : " + restaurante.getNome() + ",  id : " + restaurante.getId());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Remover Restaurante", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			gerente.removerRestaurante(cb.getSelectedIndex());
			atualizarDataBase();
		}
	}

	public void removerCliente() {
		atualizarGerente();
		if (gerente.repositorioC().getNumeroElementos() > 0) {
			JPanel painel = new JPanel(null);
			painel.setPreferredSize(new Dimension(500, 150));

			JLabel label1 = new JLabel("Escolha o Cliente a ser removido");
			JComboBox<String> cb = new JComboBox<String>();
			cb.setBackground(Color.WHITE);

			cb.setBounds(50, 40, 300, 30);
			label1.setBounds(50, 10, 300, 20);

			painel.add(cb);
			painel.add(label1);

			for (int i = 0; i < gerente.repositorioC().getNumeroElementos(); i++) {
				Cliente cliente = gerente.repositorioC().get(i);
				cb.addItem("Nome : " + cliente.getNome() + ",  id : " + cliente.getId());
			}

			int n = JOptionPane.showOptionDialog(null, painel, "Remover Cliente", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (n == 0) {
				gerente.removerCliente(cb.getSelectedIndex());
				atualizarDataBase();
			}
		} else {
			JOptionPane.showMessageDialog(this, "nao ha nenhum cliente", "Erro", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new InterfaceGerente();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(cadastrarClienteButton)) {
			if (!cadastrarCliente()) {
				JOptionPane.showMessageDialog(this, "Nao foi possivel cadastrar o Cliente", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(cadastrarRestauranteButton)) {
			if (!cadastrarRestaurante()) {
				JOptionPane.showMessageDialog(this, "Nao foi possivel cadastrar o Restaurante", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(adicionarPratoButton)) {
			if (!adicionarPrato()) {
				JOptionPane.showMessageDialog(this, "Nao foi possivel cadastrar o prato", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(removerPratoButton)) {
			removerPrato();
		}
		if (e.getSource().equals(removerRestauranteButton)) {
			removerRestaurante();
		}
		if (e.getSource().equals(removerClienteButton)) {
			removerCliente();
		}

	}

}
