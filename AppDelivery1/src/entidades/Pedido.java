package entidades;

public class Pedido {
	private long idCliente;
	private long idPedido;
	private long idRestaurate;
	private ItemCardapio[] itens = new ItemCardapio[150];
	private Status status = Status.DEFAULT;

	public Pedido(long idRestaurante, long idCliente, long idPedido) {
		setIdRestaurate(idRestaurante);
		setIdCliente(idCliente);
		setIdPedido(idPedido);
	}

	@Override
	public String toString() {
		String stringPedido;
		stringPedido = idPedido + ";" + idCliente + ";" + idRestaurate + ";" + status;
		for (int i = 0; i < 150; i++) {
			stringPedido += itens[i];
		}
		return stringPedido;
	}

	@Override
	public Pedido clone() {
		Pedido copia = new Pedido(getIdRestaurate(), getIdCliente(), getIdPedido());
		copia.setStatus(getStatus());
		copia.setItens(getItens());
		return copia;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public void verificarStatus() {

	}

	public long getIdRestaurate() {
		return idRestaurate;
	}

	public void setIdRestaurate(long idRestaurate) {
		this.idRestaurate = idRestaurate;
	}

	public ItemCardapio[] getItens() {
		return itens;
	}

	public void setItens(ItemCardapio[] itens) {
		this.itens = itens;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}