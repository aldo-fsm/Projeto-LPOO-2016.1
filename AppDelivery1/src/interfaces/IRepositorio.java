package interfaces;

import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public interface IRepositorio <T> {
	
	public static final int MAX_NUMERO_ELEMENTOS = 100;

	public void adicionar(T elemento) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException;

	public void remover(int id);

	public void alterar(int id, T novoElemento) throws IdInvalidoException, SenhaInvalidaException;
	
	public T get(int id);

	public T[] copiar();

	public T getCopia(int id);

	public int getNumeroElementos();

	public void setProximoId(long proximoId);

	public long getProximoId();

	public void setNumeroElementos(int numeroElementos);

	public void setElementos(T[] elementos);
}
