package testes;

import entidades.Restaurante;
import excecoes.IdInvalidoException;
import excecoes.SenhaInvalidaException;

public class Teste3 {
	public static void main(String[] args) {
		Restaurante r = new Restaurante("login", "aaaaaaaa5aaaaa", "nome");
		try {
			r.validar();
		} catch (IdInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SenhaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
