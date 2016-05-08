package repositorios;

import entidades.Cliente;
import entidades.Restaurante;

public class RepositorioRestaurante {

	private Restaurante[] restaurantes = new Restaurante[MAX_NUMERO_RESTAURANTES];
	private Restaurante[] backupRestaurantes = new Restaurante[MAX_NUMERO_RESTAURANTES];
	private static final int MAX_NUMERO_RESTAURANTES = 100;
	private int numeroRestaurantes = 0; // numero atual de restaurantes
	private long proximoId = 0;

	// adiciona um restaurante no final do array
	public void adicionar(Restaurante restaurante) {
		if (numeroRestaurantes < MAX_NUMERO_RESTAURANTES) {
			this.restaurantes[numeroRestaurantes] = restaurante;
			numeroRestaurantes++;
			proximoId++;
		}
	}

	// remove o restaurante na posicao id do array
	public void remover(int id) {
		if (id >= 0 && id < numeroRestaurantes) {
			while (id < numeroRestaurantes - 1) {
				restaurantes[id] = restaurantes[id + 1];
				id++;
			}
			numeroRestaurantes--;
		}
	}

	// altera a senha do restaurante na posicao id do array
	public void alterarSenha(int id, String novaSenha) {
		if (id >= 0 && id < numeroRestaurantes) {
			restaurantes[id].setSenha(novaSenha);
		}
	}

	// altera a nome do restaurante na posicao id do array
	public void alterarNome(int id, String novoNome) {
		if (id >= 0 && id < numeroRestaurantes) {
			restaurantes[id].setNome(novoNome);
		}
	}

	// retorna o restaurante da posicao id do array
	public Restaurante getRestaurante(int id) {
		if (id >= 0 && id < numeroRestaurantes) {
			return restaurantes[id];
		}
		return null;
	}

	public int getNumeroRestaurantes() {
		return numeroRestaurantes;
	}

	public void listarCardapio(int id) {
		Restaurante restaurante = getRestaurante(id);
		for (int i = 0; i < restaurante.getNumeroPratosCardapio(); i++) {
			System.out.println(i + ". " + restaurante.getPratoCardapio(i).getNome() + " -------- "
					+ restaurante.getPratoCardapio(i).getPreco());
		}
	}

	// retorna uma copia de todos os itens do repositorio
	public Restaurante[] copiar() {
		Restaurante[] copia = new Restaurante[MAX_NUMERO_RESTAURANTES];
		for (int i = 0; i < numeroRestaurantes; i++) {
			copia[i] = restaurantes[i].clone();
		}
		return copia;
	}
	
	public Restaurante getCopia(long id) {
		Restaurante[] copia = new Restaurante[numeroRestaurantes];
		for (int i = 0; i < numeroRestaurantes; i++) {
			if (restaurantes[i].getId()== id) {
				copia[i].setId(restaurantes[i].getId());
				copia[i].setLogin(restaurantes[i].getLogin());
				copia[i].setNome(restaurantes[i].getNome());
				copia[i].setSenha(restaurantes[i].getSenha());
				return copia[i];
			}
		}
		return null;
	}
}
