package dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Gerente;
import entidades.ItemCardapio;
import entidades.Pedido;
import entidades.Restaurante;
import repositorios.RepositorioCliente;
import repositorios.RepositorioPedido;
import repositorios.RepositorioRestaurante;

public class DataBase {

	// nome: caminho+nome do arquivo
	// str: Conteudo a ser gravado
	public static void gravarDados(String nome, String str) {
		FileWriter arq;
		try {
			arq = new FileWriter(nome + ".txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(str);
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro na grava��o do arquivo ...");
		}
	}

	// caminho: caminho do arquivo em disco
	// Retorna um array com o conteudo do arquivo separado por linhas
	public static String[] lerDados(String caminho) {
		ArrayList<String> list = new ArrayList<String>();
		String[] retorno = null;
		BufferedReader in;
		File f = new File(caminho);
		try {
			if (!f.exists())
				f.createNewFile();
			in = new BufferedReader(new FileReader(caminho));
			while (in.ready()) {
				String line = in.readLine();
				list.add(line);
			}
			in.close();
			retorno = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				retorno[i] = list.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	// Salva em um arquivo de texto os dados do reposit�rio de restaurantes
	public static void salvarEstado(RepositorioRestaurante restaurantes) {
		int i;
		String nome = "arquivos/repositorioRestaurante";
		String str = Long.toString(restaurantes.getProximoId()) + "\n";
		for (i = 0; i < restaurantes.getNumeroRestaurantes(); i++) {
			str += restaurantes.getRestaurante(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva em um arquivo de texto os dados do reposit�rio de clientes
	public static void salvarEstado(RepositorioCliente clientes) {
		int i;
		String nome = "arquivos/repositorioCliente";
		String str = Long.toString(clientes.getProximoId()) + "\n";
		for (i = 0; i < clientes.getNumeroClientes(); i++) {
			str += clientes.getCliente(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva o estado dos reposit�rios do gerente
	public static void salvarEstado(Gerente gerente) {
		salvarEstado(gerente.repositorioC());
		salvarEstado(gerente.repositorioR());
	}

	public static RepositorioCliente lerBaseClientes() {
		String[] strings = lerDados("arquivos/repositorioCliente.txt");
		if (strings != null) {

			RepositorioCliente repositorioCliente = new RepositorioCliente();
			Cliente[] clientes = new Cliente[RepositorioCliente.MAX_NUMERO_CLIENTES];
			repositorioCliente.setProximoId(Long.parseLong(strings[0]));
			int i;
			int j;
			String[] stringSplit1;
			String[] stringSplit2;
			for (i = 1; i < strings.length; i++) {
				stringSplit1 = strings[i].split(";");
				clientes[i-1] = new Cliente(stringSplit1[1], stringSplit1[2], stringSplit1[3]);
				clientes[i-1].setId(Long.parseLong(stringSplit1[0]));
				i++;
			}
			repositorioCliente.setClientes(clientes);
			repositorioCliente.setNumeroClientes(i);

			return repositorioCliente;
		} else {
			return null;
		}
	}

	public static RepositorioRestaurante LerBaseRestaurantes() {
		try {
			String[] str = lerDados("arquivos/repositorioRestaurante.txt");
			String[] stringSplit;
			String[] stringSplit2;
			RepositorioRestaurante repositorioRestaurante = new RepositorioRestaurante();
			Restaurante restaurante;
			Restaurante[] restaurantes = new Restaurante[RepositorioRestaurante.MAX_NUMERO_RESTAURANTES];
			ItemCardapio prato;
			ItemCardapio[] cardapio;
			repositorioRestaurante.setProximoId(Long.parseLong(str[0]));
			long id;
			String login;
			String senha;
			String nome;
			long idPrato;
			String nomePrato;
			double precoPrato;

			int i;
			int j;
			for (i = 1; i < str.length; i++) {

				stringSplit = str[i].split(";");
				id = Long.parseLong(stringSplit[0]);
				login = stringSplit[1];
				senha = stringSplit[2];
				nome = stringSplit[3];
				restaurante = new Restaurante(login, senha, nome);
				restaurante.setId(id);
				restaurantes[i - 1] = restaurante;

				cardapio = new ItemCardapio[Restaurante.MAX_PRATOS];
				for (j = 4; j < stringSplit.length; j++) {
					stringSplit2 = stringSplit[j].split("/");
					idPrato = Long.parseLong(stringSplit2[0]);
					nomePrato = stringSplit2[1];
					precoPrato = Double.parseDouble(stringSplit2[2]);

					prato = new ItemCardapio(nomePrato, precoPrato);
					prato.setId(idPrato);
					cardapio[j - 4] = prato;
				}
				restaurante.setNumeroPratosCardapio(j - 4);
				restaurante.setCardapio(cardapio);

			}
			repositorioRestaurante.setRestaurantes(restaurantes);
			repositorioRestaurante.setNumeroRestaurantes(i - 1);

			return repositorioRestaurante;

		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static RepositorioPedido lerBasePedidos() {
		try {
			RepositorioPedido repositorioPedido = new RepositorioPedido();
			Pedido[] pedidos = new Pedido[100];
			int numeroPedidos = 0; // numero atual de pedidos
			long proximoId = 0;
			String[] str = lerDados("arquivos/repositorioPedido.txt");

			return repositorioPedido;

		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Gerente lerBaseGerente() {
		Gerente recuperado = new Gerente();
		recuperado.setRepositorioC(lerBaseClientes());
		recuperado.setRepositorioR(LerBaseRestaurantes());
		return recuperado;
	}
}