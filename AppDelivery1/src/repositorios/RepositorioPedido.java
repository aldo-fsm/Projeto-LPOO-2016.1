package repositorios;

import entidades.Pedido;

public class RepositorioPedido {
	private Pedido[] pedidos = new Pedido[MAX_NUMERO_PEDIDOS];
	private static final int MAX_NUMERO_PEDIDOS = 100;
	private int numeroPedidos = 0; // numero atual de pedidos

	// adiciona um pedido no final do array
	private long proximoId = 1000;
	public void adicionar(long idRestaurante, long idCliente) {
		if (numeroPedidos < MAX_NUMERO_PEDIDOS) {
			this.pedidos[numeroPedidos] = new Pedido(idRestaurante, idCliente, proximoId);
			numeroPedidos++;
			proximoId++;
		}
	}

	// remove o pedido na posicao id do array
	public void remover(int id) {
		if (id >= 0 && id < numeroPedidos) {
			while (id < numeroPedidos - 1) {
				pedidos[id] = pedidos[id + 1];
				id++;
			}
			numeroPedidos--;
		}
	}
	// retorna o pedido da posicao id do array
	public Pedido getPedidos(int id) {
		if (id >= 0 && id < numeroPedidos) {
			return pedidos[id];
		}
		return null;
	}
	//retorna uma copia pelo id 
	public Pedido getCopia(int id) {
		Pedido[] copia = new Pedido[MAX_NUMERO_PEDIDOS];
		for (int i = 0; i < numeroPedidos; i++) {
			copia[i].setIdCliente(pedidos[i].getIdCliente());
			copia[i].setIdPedido(pedidos[i].getIdPedido());
			copia[i].setIdRestaurate(pedidos[i].getIdRestaurate());
			copia[i].setItens(pedidos[i].getItens());
			copia[i].setStatus(pedidos[i].getStatus());
		}
		if (id >= 0 && id < numeroPedidos) {
			return copia[id];
		}
		return null;
	}

	// retorna uma copia de todos os itens armazenados
	public Pedido[] getCopia() {
		Pedido[] copia = new Pedido[MAX_NUMERO_PEDIDOS];
		for (int i = 0; i < numeroPedidos; i++) {
			copia[i].setIdCliente(pedidos[i].getIdCliente());
			copia[i].setIdPedido(pedidos[i].getIdPedido());
			copia[i].setIdRestaurate(pedidos[i].getIdRestaurate());
			copia[i].setItens(pedidos[i].getItens());
			copia[i].setStatus(pedidos[i].getStatus());
		}
		return copia;
	}

	public int getNumeroPedidos() {
		return numeroPedidos;
	}
}