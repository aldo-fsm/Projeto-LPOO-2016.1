/*package repositorios;

import entidades.Restaurante;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import interfaces.InterfaceRepositorioRestaurante;

public class RepositorioRestaurante implements InterfaceRepositorioRestaurante {

	private Restaurante[] restaurantes = new Restaurante[MAX_NUMERO_RESTAURANTES];
	public static final int MAX_NUMERO_RESTAURANTES = 100;
	private int numeroRestaurantes = 0; // numero atual de restaurantes
	private long proximoId = 1;

	public long getProximoId() {
		return proximoId;
	}

	// adiciona um restaurante no final do array
	public void adicionar(Restaurante restaurante) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		if (numeroRestaurantes < MAX_NUMERO_RESTAURANTES) {
			restaurante.setId(proximoId);
			restaurante.validar();
			this.restaurantes[numeroRestaurantes] = restaurante;
			numeroRestaurantes++;
			proximoId++;
		}else{
			throw new RepositorioCheioException();
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
	public void alterarSenha(int id, String novaSenha) throws IdInvalidoException, SenhaInvalidaException {
		if (id >= 0 && id < numeroRestaurantes) {
			Restaurante restaurante = restaurantes[id].clone();
			restaurante.setSenha(novaSenha);
			restaurante.validar();
			restaurantes[id] = restaurante;
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

	public Restaurante getRestauranteId(long id) {
		int i = 0;
		while (i < numeroRestaurantes) {
			if (id == restaurantes[i].getId()) {
				return restaurantes[i];
			}
			i++;
		}
		return null;
	}

	public int getNumeroRestaurantes() {
		return numeroRestaurantes;
	}

	public String listarCardapio(int id) {
		Restaurante restaurante = getRestaurante(id);
		String cardapio = "";
		for (int i = 0; i < restaurante.getNumeroPratosCardapio(); i++) {
			cardapio = cardapio + restaurante.getPratoCardapio(i).getId() + ". "
					+ restaurante.getPratoCardapio(i).getNome() + " -------- "
					+ restaurante.getPratoCardapio(i).getPreco() + "\n";
		}
		return cardapio;
	}

	public String listarCardapio(long id) {
		Restaurante restaurante = getRestauranteId(id);
		String cardapio = "";
		for (int i = 0; i < restaurante.getNumeroPratosCardapio(); i++) {
			cardapio = cardapio + restaurante.getPratoCardapio(i).getId() + ". "
					+ restaurante.getPratoCardapio(i).getNome() + " -------- "
					+ restaurante.getPratoCardapio(i).getPreco() + "\n";
		}
		return cardapio;
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
			if (restaurantes[i].getId() == id) {
				copia[i] = restaurantes[i].clone();
				return copia[i];
			}
		}
		return null;
	}

	public void setProximoId(long id) {
		proximoId = id;
	}

	public void setRestaurantes(Restaurante[] restaurantes) {
		this.restaurantes = restaurantes;
	}

	public void setNumeroRestaurantes(int numeroRestaurantes) {
		this.numeroRestaurantes = numeroRestaurantes;
	}
}
*/