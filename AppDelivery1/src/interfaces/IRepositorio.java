package interfaces;

import java.util.ArrayList;

import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public interface IRepositorio <T extends Cloneable> {
	
	public static final int MAX_NUMERO_ELEMENTOS = 100;

	public void adicionar(T elemento) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException;

	public void remover(int id);

	public void alterar(int id, T novoElemento) throws IdInvalidoException, SenhaInvalidaException;
	
	public T get(int id);

	public ArrayList<T> copiar();

	public T getCopia(int id);

	public int getNumeroElementos();

	public void setProximoId(long proximoId);

	public long getProximoId();

	public void setElementos(ArrayList<T> elementos);
	
}
