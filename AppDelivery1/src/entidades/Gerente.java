package entidades;

import repositorios.RepositorioRestaurante;
import repositorios.RepositorioCliente;

public class Gerente{

	private RepositorioCliente clientes = new RepositorioCliente();
	private RepositorioRestaurante restaurantes = new RepositorioRestaurante();


	// adiciona um cliente no repositorio
	public void adicionarCliente(Cliente cliente) {
		clientes.adicionar(cliente);
	}

	// remove um cliente do repositorio
	public void removerCliente(int id) {
		clientes.remover(id);
	}

	// adiciona um prato no cardapio de um restaurante do repositorio
	public void adicionarPrato(int restauranteId, ItemCardapio item) {
		restaurantes.getRestaurante(restauranteId).adicionarPrato(item);
	}

	// remove um prato do cardapio de um restaurante do repositorio
	public void removerPrato(int restauranteId, int idPrato) {
		restaurantes.getRestaurante(restauranteId).removerPrato(idPrato);
	}

	// adiciona um restaurante no repositorio
	public void adicionarRestaurante(Restaurante restaurante) {
		restaurantes.adicionar(restaurante);
	}

	// remove um restaurante do repositorio
	public void removerRestaurante(int id) {
		restaurantes.remover(id);
	}

	// imprime no console o id e o nome de todos os restaurantes do repositorio
	public void listarRestaurantes() {
		Restaurante restaurante;
		for (int i = 0; i < restaurantes.getNumeroRestaurantes(); i++) {
			restaurante = restaurantes.getRestaurante(i);
			System.out.println(i + ". " + restaurante.getId() + " " + restaurante.getNome());
		}
	}

	// imprime no console o id e o nome de todos os clientes do repositorio
	public void listarClientes() {
		Cliente cliente;
		for (int i = 0; i < clientes.getNumeroClientes(); i++) {
			cliente = clientes.getCliente(i);
			System.out.println(i + ". " + cliente.getId() + " " + cliente.getNome());
		}
	}

	public void listarCardapio(int id) {
		repositorioR().listarCardapio(id);
	}

	// retorna o repositorio de clientes
	public RepositorioCliente repositorioC() {
		return clientes;
	}

	// retorna o repositorio de restaurantes
	public RepositorioRestaurante repositorioR() {
		return restaurantes;
	}

}
