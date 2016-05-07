package repositorios;

import entidades.Cliente;

public class RepositorioCliente {

	private Cliente[] clientes = new Cliente[MAX_NUMERO_CLIENTES];
	private static final int MAX_NUMERO_CLIENTES = 100;
	private Cliente[] backupCliente = new Cliente[MAX_NUMERO_CLIENTES];
	private int numeroClientes = 0; // numero atual de clientes
	private long proximoId = 0;

	// adiciona um cliente no final do array
	public void adicionar(String login, String senha, String nome) {
		if (numeroClientes < MAX_NUMERO_CLIENTES) {
			this.clientes[numeroClientes] = new Cliente(login, senha, nome, proximoId);
			numeroClientes++;
			proximoId++;
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
	public void alterarSenha(int id, String novaSenha) {
		if (id >= 0 && id < numeroClientes) {
			clientes[id].setSenha(novaSenha);
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

	public void recuperarCliente(long id) {
		// recupera um cliente e o posiciona ao fim do vetor de clientes
		for (int i = 0; i < MAX_NUMERO_CLIENTES; i++) {
			if (backupCliente[i].getId() == id) {
				clientes[numeroClientes] = backupCliente[i];
				numeroClientes++;
				break;
			}
		}
	}

	// retorna uma copia de todos os itens do repositorio
	public Cliente[] copiar() {
		Cliente[] copia = new Cliente[MAX_NUMERO_CLIENTES];
		for (int i = 0; i < numeroClientes; i++) {
			copia[i] = clientes[i].clone();
		}
		return copia;
	}

	public void backupClientes() {
		// aloca no vetor backupCliente as informacoes atuais do vetor cliente
		backupCliente = clientes;
	}

	public int getNumeroClientes() {
		return numeroClientes;
	}

	public void setProximoId(long proximoId) {
		this.proximoId = proximoId;
	}
}