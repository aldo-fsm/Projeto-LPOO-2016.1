package repositorios;

import entidades.Pedido;
import entidades.Cliente;
import entidades.Usuario;
import java.util.ArrayList;
import entidades.Restaurante;
import interfaces.IRepositorio;
import excecoes.IdInvalidoException;
import excecoes.SenhaInvalidaException;
import excecoes.RepositorioCheioException;

public class Repositorio<T extends Cloneable> implements IRepositorio<T> {

	private ArrayList<T> elementos = new ArrayList<T>();
	private long proximoId = 1;

	public void adicionar(T elemento) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException {
		if (elementos.size() < IRepositorio.MAX_NUMERO_ELEMENTOS) {
			if (elemento instanceof Usuario) {
				if (elementos.get(0) instanceof Cliente) {
					((Cliente) elemento).validar();
					elementos.add(elemento);
					((Cliente) elementos.get(elementos.size()-1)).setId(proximoId);
					proximoId++;
				} else {
					((Restaurante) elemento).validar();
					elementos.add(elemento);
					((Restaurante) elementos.get(elementos.size()-1)).setId(proximoId);
					proximoId++;
				}
			} else {
				if (elemento instanceof Pedido) {
					((Pedido) elemento).setIdPedido(proximoId);
				}
				elementos.add(elemento);
				((Pedido) elementos.get(elementos.size()-1)).setIdPedido(proximoId);
				proximoId++;
			}
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
				((Usuario) novoElemento).setId(((Usuario) elementos.get(id)).getId());
				((Usuario) novoElemento).validar();
				elementos.set(id, novoElemento);
			} else if (novoElemento instanceof Pedido) {
				((Pedido) novoElemento).setIdPedido(((Pedido) elementos.get(id)).getIdPedido());
				elementos.set(id, novoElemento);
			} else {
				elementos.set(id, novoElemento);
			}
		}
	}

	@Override
	public T get(int id) {
		if (elementos.size() > id) {
			return elementos.get(id);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<T> copiar() {
		if (elementos.size() > 0) {
			ArrayList<T> copia = new ArrayList<T>();
			for (int i = 0; i < elementos.size(); i++) {
				if (elementos.get(0) instanceof Cliente) {
					copia.add((T) ((Cliente) elementos.get(i)).clone());
				} else if (elementos.get(0) instanceof Restaurante) {
					copia.add((T) ((Restaurante) elementos.get(i)).clone());
				} else if (elementos.get(0) instanceof Pedido) {
					copia.add((T) ((Pedido) elementos.get(i)).clone());
				}
			}
			return copia;
		}
		return new ArrayList<T>();
	}

	public T getCopia(int id) {
		return copiar().get(id);
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

	public void setElementos(ArrayList<T> elementos) {
		this.elementos = elementos;
	}

	public ArrayList<T> getElementos() {
		return elementos;
	}

}
