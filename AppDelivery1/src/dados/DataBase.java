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
import repositorios.Repositorio;

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
			System.out.println("Erro na gravação do arquivo ...");
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

	public void exportarCSV() {
		
	}

	// Salva em um arquivo de texto os dados do repositorio de restaurantes
	public static void salvarEstadoRestaurante(Repositorio<Restaurante> restaurante) {
		int i;
		String nome = "arquivos/repositorioRestaurante";
		String str = Long.toString(restaurante.getProximoId()) + "\n";
		for (i = 0; i < restaurante.getNumeroElementos(); i++) {
			str += restaurante.get(i).getProximoId() + ";" + restaurante.get(i).toString()
					+ "\n";
		}
		gravarDados(nome, str);
	}

	// Salva em um arquivo de texto os dados do repositorio de clientes
	public static void salvarEstadoCliente(Repositorio<Cliente> clientes) {
		int i;
		String nome = "arquivos/repositorioCliente";
		String str = Long.toString(clientes.getProximoId()) + "\n";
		for (i = 0; i < clientes.getNumeroElementos(); i++) {
			str += clientes.get(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva o estado dos repositorios do gerente
	public static void salvarEstado(Gerente gerente) {
		salvarEstadoCliente(gerente.repositorioC());
		salvarEstadoRestaurante(gerente.repositorioR());
	}

	/*
	 * ler dados do repositorio de clientes salvos em arquivo e retorna um
	 * RepositorioCliente com esses dados
	 */
	public static Repositorio<Cliente> lerBaseClientes() {
		String[] strings = lerDados("arquivos/repositorioCliente.txt");
		try {
			Repositorio<Cliente> repositorioCliente = new Repositorio<Cliente>();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			int i;
			String[] stringSplit;
			for (i = 1; i < strings.length; i++) {
				stringSplit = strings[i].split(";");
				clientes.add(i - 1, new Cliente(stringSplit[1], stringSplit[2], stringSplit[3]));
				clientes.get(i - 1).setId(Long.parseLong(stringSplit[0]));
			}
			repositorioCliente.setElementos(clientes);
			repositorioCliente.setProximoId(Long.parseLong(strings[0]));
			return repositorioCliente;
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Repositorio<Cliente>();
		} catch (NumberFormatException e) {
			return new Repositorio<Cliente>();
		}
	}

	/*
	 * ler dados do repositorio de restaurantes salvos em arquivo e retorna um
	 * RepositorioRestaurante com esses dados
	 */
	public static Repositorio<Restaurante> LerBaseRestaurantes() {
		String[] strings = lerDados("arquivos/repositorioRestaurante.txt");
		try {

			Repositorio<Restaurante> repositorioRestaurante = new Repositorio<Restaurante>();
			ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
			ItemCardapio[] cardapio;

			repositorioRestaurante.setProximoId(Long.parseLong(strings[0]));
			String[] stringSplit;
			String[] stringSplit2;
			int i;
			int j;
			for (i = 1; i < strings.length; i++) {

				stringSplit = strings[i].split(";");
				restaurantes.add(i - 1 ,new Restaurante(stringSplit[2], stringSplit[3], stringSplit[4]));
				restaurantes.get(i - 1).setProximoId(Long.parseLong(stringSplit[0]));
				restaurantes.get(i - 1).setId(Long.parseLong(stringSplit[1]));

				cardapio = new ItemCardapio[Restaurante.MAX_PRATOS];
				for (j = 5; j < stringSplit.length; j++) {
					stringSplit2 = stringSplit[j].split("/");
					cardapio[j - 5] = new ItemCardapio(stringSplit2[1], Double.parseDouble(stringSplit2[2]));
					cardapio[j - 5].setId(Long.parseLong(stringSplit2[0]));
				}
				restaurantes.get(i - 1).setNumeroPratosCardapio(j - 5);
				restaurantes.get(i - 1).setCardapio(cardapio);
			}
			repositorioRestaurante.setElementos(restaurantes);

			return repositorioRestaurante;
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Repositorio<Restaurante>();
		} catch (NumberFormatException e) {
			return new Repositorio<Restaurante>();
		}

	}

	// salva em um arquivo de texto os dados do repositorio de pedidos
	public static void salvarEstado(Repositorio<Pedido> pedido) {
		int i;
		String nome = "arquivos/repositorioPedido";
		String str = Long.toString(pedido.getProximoId()) + "\n";
		for (i = 0; i < pedido.getNumeroElementos(); i++) {
			str += pedido.get(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	/*
	 * ler dados do repositorio de pedidos salvos em arquivo e retorna um
	 * RepositorioPedido com esses dados
	 */
	public static Repositorio<Pedido> lerBasePedidos() {
		String[] strings = lerDados("arquivos/repositorioPedido.txt");
		try {
			Repositorio<Pedido> repositorioPedido = new Repositorio<Pedido>();
			ArrayList<Pedido> pedidos= new ArrayList<Pedido>();
			long proximoId = Long.parseLong(strings[0]);
			String[] stringSplit;
			String[] stringSplit2;
			ItemCardapio[] itens;
			repositorioPedido.setProximoId(proximoId);
			int i;
			int j;
			for (i = 1; i < strings.length; i++) {
				stringSplit = strings[i].split(";");
				pedidos.add(i - 1,new Pedido(Long.parseLong(stringSplit[2]), Long.parseLong(stringSplit[1])));
				pedidos.get(i - 1).setIdPedido(Long.parseLong(stringSplit[0]));
				pedidos.get(i - 1).setStatus(stringSplit[3]);

				itens = new ItemCardapio[Cliente.MAX_ITENS_CARRINHO];
				for (j = 4; j < stringSplit.length; j++) {
					stringSplit2 = stringSplit[j].split("/");
					itens[j - 4] = new ItemCardapio(stringSplit2[1], Double.parseDouble(stringSplit2[2]));
					itens[j - 4].setId(Long.parseLong(stringSplit2[0]));
				}
				pedidos.get(i - 1).setItens(itens);
				pedidos.get(i - 1).setNumeroItensPedido(j - 4);
			}
			repositorioPedido.setProximoId(Long.parseLong(strings[0]));
			repositorioPedido.setElementos(pedidos);
			return repositorioPedido;

		} catch (ArrayIndexOutOfBoundsException e) {
			return new Repositorio<Pedido>();
		} catch (NumberFormatException e) {
			return new Repositorio<Pedido>();
		}
	}

	/*
	 * ler dados dos repositorios de clientes e de restaurantes salvos em
	 * arquivo e retorna um Gerente com esses repositorios
	 */
	public static Gerente lerBaseGerente() {
		Gerente recuperado = new Gerente();
		recuperado.setRepositorioC(lerBaseClientes());
		recuperado.setRepositorioR(LerBaseRestaurantes());
		return recuperado;
	}
}