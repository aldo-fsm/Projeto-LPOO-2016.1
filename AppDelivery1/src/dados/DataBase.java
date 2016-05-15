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

	// Salva em um arquivo de texto os dados do repositorio de restaurantes
	public static void salvarEstado(RepositorioRestaurante restaurantes) {
		int i;
		String nome = "arquivos/repositorioRestaurante";
		String str = Long.toString(restaurantes.getProximoId()) + "\n";
		for (i = 0; i < restaurantes.getNumeroRestaurantes(); i++) {
			str += restaurantes.getRestaurante(i).getProximoId() + ";" + restaurantes.getRestaurante(i).toString()
					+ "\n";
		}
		gravarDados(nome, str);
	}

	// Salva em um arquivo de texto os dados do repositorio de clientes
	public static void salvarEstado(RepositorioCliente clientes) {
		int i;
		String nome = "arquivos/repositorioCliente";
		String str = Long.toString(clientes.getProximoId()) + "\n";
		for (i = 0; i < clientes.getNumeroClientes(); i++) {
			str += clientes.getCliente(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva o estado dos repositorios do gerente
	public static void salvarEstado(Gerente gerente) {
		salvarEstado(gerente.repositorioC());
		salvarEstado(gerente.repositorioR());
	}

	/*
	 * ler dados do repositorio de clientes salvos em arquivo e retorna um
	 * RepositorioCliente com esses dados
	 */
	public static RepositorioCliente lerBaseClientes() {
		String[] strings = lerDados("arquivos/repositorioCliente.txt");
		if (strings != null) {

			RepositorioCliente repositorioCliente = new RepositorioCliente();
			Cliente[] clientes = new Cliente[RepositorioCliente.MAX_NUMERO_CLIENTES];
			int i;
			String[] stringSplit;
			for (i = 1; i < strings.length; i++) {
				stringSplit = strings[i].split(";");
				clientes[i - 1] = new Cliente(stringSplit[1], stringSplit[2], stringSplit[3]);
				repositorioCliente.adicionar(clientes[i - 1]);
				clientes[i - 1].setId(Long.parseLong(stringSplit[0]));
			}
			repositorioCliente.setProximoId(Long.parseLong(strings[0]));
			return repositorioCliente;
		} else {
			return null;
		}
	}

	/*
	 * ler dados do repositorio de restaurantes salvos em arquivo e retorna um
	 * RepositorioRestaurante com esses dados
	 */
	public static RepositorioRestaurante LerBaseRestaurantes() {
		String[] strings = lerDados("arquivos/repositorioRestaurante.txt");
		if (strings != null) {

			RepositorioRestaurante repositorioRestaurante = new RepositorioRestaurante();
			Restaurante[] restaurantes = new Restaurante[RepositorioRestaurante.MAX_NUMERO_RESTAURANTES];
			ItemCardapio[] cardapio;

			repositorioRestaurante.setProximoId(Long.parseLong(strings[0]));
			String[] stringSplit;
			String[] stringSplit2;
			int i;
			int j;
			for (i = 1; i < strings.length; i++) {

				stringSplit = strings[i].split(";");
				restaurantes[i - 1] = new Restaurante(stringSplit[2], stringSplit[3], stringSplit[4]);
				restaurantes[i - 1].setProximoId(Long.parseLong(stringSplit[0]));
				restaurantes[i - 1].setId(Long.parseLong(stringSplit[1]));

				cardapio = new ItemCardapio[Restaurante.MAX_PRATOS];
				for (j = 5; j < stringSplit.length; j++) {
					stringSplit2 = stringSplit[j].split("/");
					cardapio[j - 5] = new ItemCardapio(stringSplit2[1], Double.parseDouble(stringSplit2[2]));
					cardapio[j - 5].setId(Long.parseLong(stringSplit2[0]));
				}
				restaurantes[i - 1].setNumeroPratosCardapio(j - 5);
				restaurantes[i - 1].setCardapio(cardapio);
			}
			repositorioRestaurante.setRestaurantes(restaurantes);
			repositorioRestaurante.setNumeroRestaurantes(i - 1);

			return repositorioRestaurante;
		} else {
			return null;
		}

	}

	// salva em um arquivo de texto os dados do repositorio de pedidos
	public static void salvarEstado(RepositorioPedido pedido) {
		int i;
		String nome = "arquivos/repositorioPedido";
		String str = Long.toString(pedido.getProximoId()) + "\n";
		for (i = 0; i < pedido.getNumeroPedidos(); i++) {
			str += pedido.getPedido(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	public static RepositorioPedido lerBasePedidos() {
		String[] strings = lerDados("arquivos/repositorioPedido.txt");
		if (!strings.equals(null)) {
			RepositorioPedido repositorioPedido = new RepositorioPedido();
			Pedido[] pedidos = new Pedido[RepositorioPedido.MAX_NUMERO_PEDIDOS];
			long proximoId = Long.parseLong(strings[0]);
			String[] stringSplit;
			String[] stringSplit2;
			ItemCardapio[] itens;
			repositorioPedido.setProximoId(proximoId);
			int i;
			int j;
			for (i = 1; i < strings.length; i++) {
				stringSplit = strings[i].split(";");
				pedidos[i - 1] = new Pedido(Long.parseLong(stringSplit[2]), Long.parseLong(stringSplit[1]));
				pedidos[i - 1].setIdPedido(Long.parseLong(stringSplit[0]));
				pedidos[i - 1].setStatus(stringSplit[3]);

				itens = new ItemCardapio[Cliente.MAX_ITENS_CARRINHO];
				for (j = 4; j < stringSplit.length; j++) {
					stringSplit2 = stringSplit[j].split("/");
					itens[j - 4] = new ItemCardapio(stringSplit2[1], Double.parseDouble(stringSplit2[2]));
					itens[j - 4].setId(Long.parseLong(stringSplit2[0]));
				}
				pedidos[i - 1].setItens(itens);
				pedidos[i - 1].setNumeroItensPedido(j - 4);
			}
			repositorioPedido.setProximoId(Long.parseLong(strings[0]));
			repositorioPedido.setNumeroPedidos(i - 1);
			repositorioPedido.setPedidos(pedidos);
			return repositorioPedido;

		} else {
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