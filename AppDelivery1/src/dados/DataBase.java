package dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import entidades.Gerente;
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
			System.out.println("Erro na grava√ß√£o do arquivo ...");
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

	// Salva em um arquivo de texto os dados do repositÛrio de restaurantes
	public static void salvarEstado(RepositorioRestaurante restaurantes) {
		int i;
		String nome = "arquivos/repositorioRestaurante";
		String str = Long.toString(restaurantes.getProximoId()) + "\n";
		for (i = 0; i < restaurantes.getNumeroRestaurantes(); i++) {
			str += restaurantes.getRestaurante(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva em um arquivo de texto os dados do repositÛrio de clientes
	public static void salvarEstado(RepositorioCliente clientes) {
		int i;
		String nome = "arquivos/repositorioCliente";
		String str = Long.toString(clientes.getProximoId()) + "\n";
		for (i = 0; i < clientes.getNumeroClientes(); i++) {
			str += clientes.getCliente(i).toString() + "\n";
		}
		gravarDados(nome, str);
	}

	// Salva o estado dos repositÛrios do gerente
	public static void salvarEstado(Gerente gerente) {
		salvarEstado(gerente.repositorioC());
		salvarEstado(gerente.repositorioR());
	}

	public static RepositorioCliente lerBaseClientes() {

		return null;
	}

	public static RepositorioRestaurante LerBaseRestaurantes() {

		return null;
	}

	public static RepositorioPedido lerBasePedidos() {

		return null;
	}

	public static Gerente lerBaseGerente() {

		return null;
	}
}