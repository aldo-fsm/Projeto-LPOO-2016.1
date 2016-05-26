package interfaces;

import entidades.Cliente;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public interface InterfaceRepositorioCliente {
	
	public void adicionar(Cliente cliente) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException;

	public void remover(int id);

	public void alterarSenha(int id, String novaSenha) throws IdInvalidoException, SenhaInvalidaException;

	public void alterarNome(int id, String novoNome);

	public Cliente getCliente(int id);

	public Cliente[] getClientes();

	public Cliente[] copiar();

	public Cliente getCopia(long id);

	public int getNumeroClientes();

	public void setProximoId(long proximoId);

	public long getProximoId();

	public void setNumeroClientes(int numeroClientes);

	public void setClientes(Cliente[] clientes);
}
