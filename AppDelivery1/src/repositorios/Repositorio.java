package repositorios;

import java.util.ArrayList;

import entidades.Usuario;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import interfaces.IRepositorio;

public class Repositorio<T> implements IRepositorio<T> {

	private ArrayList<T> elementos = new ArrayList<T>();
	private int proximoId = 1;

	@Override
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

	@Override
	public void remover(int id) {
		if (id >= 0 && id < elementos.size()) {
			elementos.remove(id);
		}
	}

	@Override
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

	@Override
	public T[] copiar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getCopia(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroElementos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setProximoId(long proximoId) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getProximoId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumeroElementos(int numeroElementos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setElementos(T[] elementos) {
		// TODO Auto-generated method stub

	}

}
