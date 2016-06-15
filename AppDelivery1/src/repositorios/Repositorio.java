package repositorios;

import java.util.ArrayList;
import entidades.Usuario;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import interfaces.IRepositorio;

public class Repositorio<T> implements IRepositorio<T> {

	private ArrayList<T> elementos = new ArrayList<T>();
	private long proximoId = 1;

	public void adicionar(T elemento) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		if (elementos.size() < IRepositorio.MAX_NUMERO_ELEMENTOS) {
			if (elemento instanceof Usuario) {
				((Usuario) elemento).validar();
				elementos.add(elemento);
			} else {
				elementos.add(elemento);
			}
			proximoId++;
		} else {
			throw new RepositorioCheioException();
		}

	}

	public void remover(int id) {
		if (id >= 0 && id < elementos.size()) {
			elementos.remove(id);
		}
	}

	public void alterar(int id, T novoElemento) throws IdInvalidoException, SenhaInvalidaException {
		if (id >= 0 && id < elementos.size()) {
			if (novoElemento instanceof Usuario) {
				((Usuario) novoElemento).validar();
				elementos.set(id, novoElemento);
			} else {
				elementos.set(id, novoElemento);
			}
		}
	}

	@Override
	public T get(int id) {
		return elementos.get(id);
	}

	@SuppressWarnings({ "unchecked"})
	public ArrayList<T> copiar() {
		return(ArrayList<T>)elementos.clone();
	}

	@SuppressWarnings("unchecked")
	public T getCopia(int id) {
		return ((ArrayList<T>)elementos.clone()).get(id);
	}

	public int getNumeroElementos() {
		return elementos.size();
	}

	public void setProximoId(long proximoId) {
		this.proximoId = proximoId;
	}

	public long getProximoId() {
		return proximoId;
	}
	
	
	public void setNumeroElementos(int numeroElementos) {

	}

	public void setElementos(ArrayList<T> elementos) {
		this.elementos = elementos;
	}

}
