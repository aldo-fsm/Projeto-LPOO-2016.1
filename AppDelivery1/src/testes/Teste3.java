package testes;

import java.io.IOException;
import dados.DataBase;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public class Teste3 {
	public static void main(String[] args) throws IOException, IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
//		Restaurante r = new Restaurante("login", "aaaaaaaa5aaaaa", "nome");
//		try {
//			r.validar();
//		} catch (IdInvalidoException e) {
//			e.printStackTrace();
//		} catch (SenhaInvalidaException e) {
//			e.printStackTrace();
//		}
//		Repositorio<Cliente> a= new Repositorio<Cliente>();
//		a.adicionar(new Cliente("aw543535564654", "a792nnj", "545645646674vvt"));
//		System.out.println(a.get(0).getNome());
//		ArrayList<Cliente> b = a.copiar();
//		a.get(0).setNome("aryell");
//		System.out.println(b.get(0).getNome());
		DataBase.exportarCSV();
	}
}
