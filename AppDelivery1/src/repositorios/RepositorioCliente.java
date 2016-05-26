package repositorios;

import entidades.Cliente;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import interfaces.InterfaceRepositorioCliente;

public class RepositorioCliente implements InterfaceRepositorioCliente{

	private Cliente[] clientes = new Cliente[MAX_NUMERO_CLIENTES];
	public static final int MAX_NUMERO_CLIENTES = 100;
	private int numeroClientes = 0; // numero atual de clientes
	private long proximoId = 1; // proximo id disponivel

	// adiciona um cliente no final do array
	public void adicionar(Cliente cliente) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		if (numeroClientes < MAX_NUMERO_CLIENTES) {
			cliente.setId(proximoId);
			cliente.validar();
			this.clientes[numeroClientes] = cliente;
			numeroClientes++;
			proximoId++;
		}else{
			throw new RepositorioCheioException();
		}
	}

	// remove o cliente na posicao id do array
	public void remover(int id) {
		if (id >= 0 && id < numeroClientes) {
			while (id < numeroClientes - 1) {
				clientes[id] = clientes[id + 1];
				id++;
			}
			numeroClientes--;
		}
	}

	// altera a senha do cliente na posicao id do array
	public void alterarSenha(int id, String novaSenha) throws IdInvalidoException, SenhaInvalidaException {
		if (id >= 0 && id < numeroClientes) {
			Cliente cliente = clientes[id].clone();
			cliente.validar();
			clientes[id] = cliente;
		}
	}

	// altera o nome do cliente na posicao id do array
	public void alterarNome(int id, String novoNome) {
		if (id >= 0 && id < numeroClientes) {
			clientes[id].setNome(novoNome);
		}
	}

	// retorna o cliente da posicao id do array
	public Cliente getCliente(int id) {
		if (id >= 0 && id < numeroClientes) {
			return clientes[id];
		}
		return null;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	// retorna uma copia de todos os itens do repositorio
	public Cliente[] copiar() {
		Cliente[] copia = new Cliente[MAX_NUMERO_CLIENTES];
		for (int i = 0; i < numeroClientes; i++) {
			copia[i] = clientes[i].clone();
		}
		return copia;
	}

	public Cliente getCopia(long id) {
		Cliente[] copia = new Cliente[numeroClientes];
		for (int i = 0; i < numeroClientes; i++) {
			if (clientes[i].getId() == id) {
				copia[i] = clientes[i].clone();
				return copia[i];
			}
		}
		return null;
	}

	public int getNumeroClientes() {
		return numeroClientes;
	}

	public void setProximoId(long proximoId) {
		this.proximoId = proximoId;
	}

	public long getProximoId() {
		return proximoId;
	}

	public void setNumeroClientes(int numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}
}
