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

public class InterfaceGerente extends JFrame implements ActionListener {

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
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
			return false;
		}

		String login = JOptionPane.showInputDialog("Digite o Login do Cliente");
		if (login == null || login.equals("")) {
			return false;
		} else if (login.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioC().getNumeroClientes(); i++) {
				if (gerente.repositorioC().getCliente(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login n�o est� disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Cliente");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
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
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
			return false;
		}

		String login = JOptionPane.showInputDialog("Digite o Login do Restaurante");
		if (login == null || login.equals("")) {
			return false;
		} else if (login.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
			return false;
		} else {
			for (int i = 0; i < gerente.repositorioR().getNumeroRestaurantes(); i++) {
				if (gerente.repositorioR().getRestaurante(i).getLogin().equals(login)) {
					JOptionPane.showMessageDialog(this, "Este login n�o est� disponivel");
					return false;
				}
			}
		}
		String senha = JOptionPane.showInputDialog("Digite a Senha do Restaurante");
		if (senha == null || senha.equals("")) {
			return false;
		} else if (senha.contains(";")) {
			JOptionPane.showMessageDialog(this, " \" ; \" n�o � um caractere v�lido");
			return false;
		}

		gerente.adicionarRestaurante(new Restaurante(login, senha, nome));
		atualizarDataBase();
		return true;
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
		JLabel label4 = new JLabel("Pre�o");
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
			try {
				String nome = campoTextoNome.getText();
				Double preco = Double.parseDouble(campoTextoPreco.getText());

				if (nome == null || nome.equals("")) {
					return false;
				} else if (nome.contains(";") || nome.contains("/")) {
					JOptionPane.showMessageDialog(this, " \" ; \" e \" / \" n�o s�o caracteres v�lidos");
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

		for (int i = 0; i < gerente.repositorioR().getNumeroRestaurantes(); i++) {
			Restaurante restaurante = gerente.repositorioR().getRestaurante(i);
			cb.addItem("Nome : " + restaurante.getNome() + ",  id : " + restaurante.getId());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Remover Prato", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			int iRest = cb.getSelectedIndex();
			Restaurante restaurante = gerente.repositorioR().getRestaurante(iRest);
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
				JOptionPane.showMessageDialog(this, "Este restaurante n�o possui nenhum prato");
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

		for (int i = 0; i < gerente.repositorioR().getNumeroRestaurantes(); i++) {
			Restaurante restaurante = gerente.repositorioR().getRestaurante(i);
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
		JPanel painel = new JPanel(null);
		painel.setPreferredSize(new Dimension(500, 150));

		JLabel label1 = new JLabel("Escolha o Cliente a ser removido");
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBackground(Color.WHITE);

		cb.setBounds(50, 40, 300, 30);
		label1.setBounds(50, 10, 300, 20);

		painel.add(cb);
		painel.add(label1);

		for (int i = 0; i < gerente.repositorioC().getNumeroClientes(); i++) {
			Cliente cliente = gerente.repositorioC().getCliente(i);
			cb.addItem("Nome : " + cliente.getNome() + ",  id : " + cliente.getId());
		}

		int n = JOptionPane.showOptionDialog(null, painel, "Remover Cliente", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			gerente.removerCliente(cb.getSelectedIndex());
			atualizarDataBase();
		}
	}

	public static void main(String[] args) {
		new InterfaceGerente();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(cadastrarClienteButton)) {
			if (!cadastrarCliente()) {
				JOptionPane.showMessageDialog(this, "N�o foi possivel cadastrar o Cliente", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(cadastrarRestauranteButton)) {
			if (!cadastrarRestaurante()) {
				JOptionPane.showMessageDialog(this, "N�o foi possivel cadastrar o Restaurante", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(adicionarPratoButton)) {
			if (!adicionarPrato()) {
				JOptionPane.showMessageDialog(this, "N�o foi possivel cadastrar o prato", "Erro",
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

/*package principais;

import java.util.Scanner;
import dados.DataBase;
import entidades.Cliente;
import entidades.Gerente;
import entidades.ItemCardapio;
import entidades.Restaurante;

public class InterfaceGerente {
	public static void main(String[] args) {

		// se nao for statico agente muda depois de perguntar
		Scanner sc = new Scanner(System.in);
		Gerente novo = DataBase.lerBaseGerente();
		do {
			System.out.println(
					"1. Adicionar/Cadastar um cliente\n2. Remover um Cliente\n3. adicionar restaurante\n4. remover restaurante\n5.adicionar prato\n6.remover prato");
			int opcao = sc.nextInt();
			if (opcao == 1) {
				// Adicionar/Cadastar um cliente:
				// Pede as informa��es inerentes ao Cliente;
				System.out.println("nome do cliente: ");
				sc.nextLine();
				String nomeDoCliente = sc.nextLine();
				System.out.println("login do cliente: ");
				String loginDoCliente = sc.nextLine();
				System.out.println("senha do cliente: ");
				String senhaDoCliente = sc.nextLine();
				// Instacia o Cliente;
				Cliente Aryell = new Cliente(loginDoCliente, senhaDoCliente, nomeDoCliente);
				// Adiciona o novo Cliente ao sistema;
				novo.adicionarCliente(Aryell);
				// Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioC());
			} else if (opcao == 2) {
				// Remover um Cliente:
				// Uma lista com todos os clientes cadastrados e seus
				// respectivos ID�s deve ser exibida;
				novo.listarClientes();
				// Solicitar o ID do Cliente que deve ser removido;
				System.out.println("informe o id do cliente a ser removido");
				int id = sc.nextInt();
				// Remover o Cliente cujo ID foi fornecido.
				novo.removerCliente(id);
				// Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioC());
			} else if (opcao == 3) {
				// Adicionar um Restaurante
				// Pede as informa��es inerentes ao Restaurante;
				System.out.println("nome do restaurante : ");
				sc.nextLine();
				String nomeDoRestaurante = sc.nextLine();
				System.out.println("login do restaurante : ");
				String loginDoRestaurante = sc.nextLine();
				System.out.println("senha do restaurante: ");
				String senhaDoRestaurante = sc.nextLine();
				// Instacia o Restaurante;
				Restaurante Atlantico = new Restaurante(loginDoRestaurante, senhaDoRestaurante, nomeDoRestaurante);
				// Adiciona o novo Restaurante ao sistema;
				novo.adicionarRestaurante(Atlantico);
				// Salva o estado do sistema
				DataBase.salvarEstado(novo.repositorioR());
			} else if (opcao == 4) {
				// Remover um Restaurante
				// Uma lista com todos os Restaurantes cadastrados e seus
				// respectivos ID�s deve ser exibida;
				novo.listarRestaurantes();
				// Solicitar o ID do Restaurante que deve ser removido;
				System.out.println("informe o id do restaurante a ser removido");
				int idDoRestaurnate = sc.nextInt();
				// Remover o Restaurante cujo ID foi fornecido.
				novo.removerRestaurante(idDoRestaurnate);
				// Salva o estado do sistema
				DataBase.salvarEstado(novo.repositorioR());
			} else if (opcao == 5) {
				// Adicionar Prato
				// Exibe uma lista com todos os Restaurantes cadastrados e seus
				// respectivos ID�s;
				novo.listarRestaurantes();
				// Solicitar o ID do Restaurante que deve receber o novo prato;
				System.out.println("informe o id do restaurante a receber o novo prato");
				int idDoRestaurante = sc.nextInt();
				// Solicita as informa��es inerentes ao novo prato;
				System.out.println("nome do prato : ");
				sc.nextLine();
				String nomeDoPrato = sc.nextLine();
				System.out.println("informe o id do prato: ");
				long idDoPrato = sc.nextLong();
				System.out.println("informe o preco do prato: ");
				double precoDoPrato = sc.nextDouble();
				// Instancia e adiciona o novo prato
				ItemCardapio novoPrato = new ItemCardapio(
						 idDoPrato, o id dos itens tambem sao incrementais nomeDoPrato, precoDoPrato);
				novo.adicionarPrato(idDoRestaurante, novoPrato);
				// Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioR());
			} else if (opcao == 6) {
				// Remover Prato
				// Exibe uma lista com todos os Restaurantes cadastrados e seus
				// respectivos ID�s;
				novo.listarRestaurantes();
				// Solicitar o ID do Restaurante que deve ter o prato removido
				// de seu Card�pio;
				System.out.println("informe o id do restaurante a remover o prato");
				int idDoRestaurante = sc.nextInt();
				// Exibe o Card�pio desse Restaurante e os ID�s de cada item;
				novo.listarCardapio(idDoRestaurante);
				// Solicitar o ID do prato a ser Removido;
				System.out.println("informe o id do prato a ser removido");
				int idDoPrato = sc.nextInt();
				// Remover o prato;
				novo.removerPrato(idDoRestaurante, idDoPrato);
				// Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioR());
			}

			System.out.print("deseja mais alguma coisa?\n");
		} while (sc.nextLine().equals("sim"));
		sc.close();
		}
}*/
