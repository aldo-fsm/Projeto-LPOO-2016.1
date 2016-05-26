package interfaces;

import entidades.Pedido;
import excecoes.RepositorioCheioException;

public interface InterfaceRepositorioPedido {

	public Pedido[] getPedidos();

	public void setPedidos(Pedido[] pedidos);

	public void setNumeroPedidos(int numeroPedidos);

	public long getProximoId();

	public void setProximoId(long proximoId);

	public void adicionar(Pedido pedido) throws RepositorioCheioException;

	public void remover(int id);

	public Pedido getCopia(long id);

	public Pedido[] copiar();

	public Pedido getPedido(int id);

	public int getNumeroPedidos();
}
