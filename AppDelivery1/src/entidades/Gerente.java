package entidades;

import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import interfaces.IRepositorio;
import repositorios.Repositorio;

public class Gerente {

	private IRepositorio<Cliente> clientes = new Repositorio<Cliente>();
	private IRepositorio<Restaurante> restaurantes = new Repositorio<Restaurante>();

	// adiciona um cliente no repositorio
	public void adicionarCliente(Cliente cliente)
			throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		clientes.adicionar(cliente);
	}

	// remove um cliente do repositorio
	public void removerCliente(int id) {
		clientes.remover(id);
	}

	// adiciona um prato no cardapio de um restaurante do repositorio
	public void adicionarPrato(int restauranteId, ItemCardapio item) {
		restaurantes.get(restauranteId).adicionarPrato(item);
	}

	// remove um prato do cardapio de um restaurante do repositorio
	public void removerPrato(int restauranteId, int idPrato) {
		restaurantes.get(restauranteId).removerPrato(idPrato);
	}

	// adiciona um restaurante no repositorio
	public void adicionarRestaurante(Restaurante restaurante)
			throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		restaurantes.adicionar(restaurante);
	}

	// remove um restaurante do repositorio
	public void removerRestaurante(int id) {
		restaurantes.remover(id);
	}

	// imprime no console o id e o nome de todos os restaurantes do repositorio
	public String listarRestaurantes() {
		Restaurante restaurante;
		String listaDeRestaurantes = "";
		for (int i = 0; i < restaurantes.getNumeroElementos(); i++) {
			restaurante = restaurantes.get(i);
			listaDeRestaurantes = listaDeRestaurantes + restaurante.getId() + ". " + restaurante.getNome() + "\n";
			// System.out.println(i + ". " + restaurante.getId() + " " +
			// restaurante.getNome()
			// + "\n");
		}
		return listaDeRestaurantes;
	}

	// imprime no console o id e o nome de todos os clientes do repositorio
	public void listarClientes() {
		Cliente cliente;
		for (int i = 0; i < clientes.getNumeroElementos(); i++) {
			cliente = clientes.get(i);
			System.out.println(i + ". " + cliente.getId() + " " + cliente.getNome());
		}
	}

	public void listarCardapio(int id) {
		repositorioR().get(id).listarCardapio();
	}

	// retorna o repositorio de clientes
	public Repositorio<Cliente> repositorioC() {
		return (Repositorio<Cliente>) clientes;
	}

	// retorna o repositorio de restaurantes
	public Repositorio<Restaurante> repositorioR() {
		return (Repositorio<Restaurante>) restaurantes;
	}

	// atribui ao repositorio de clientes
	public void setRepositorioC(Repositorio<Cliente> clientes) {
		this.clientes = clientes;
	}

	// atribui ao repositorio de restaurantes
	public void setRepositorioR(Repositorio<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
}
