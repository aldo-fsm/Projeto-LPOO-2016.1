package entidades;

public class Pedido {
	private long idCliente;
	private long idPedido;
	private long idRestaurate;
	private ItemCardapio[] itens = new ItemCardapio[150];
	private Status status = Status.DEFAULT;
	private int numeroItensPedido = 0;

	public Pedido(long idRestaurante, long idCliente) {
		setIdRestaurate(idRestaurante);
		setIdCliente(idCliente);
	}

	@Override
	public String toString() {
		String stringPedido = idPedido + ";" + idCliente + ";" + idRestaurate + ";" + status;
		for (int i = 0; i < numeroItensPedido; i++) {
			stringPedido += ";" + itens[i].toString();
		}
		return stringPedido;
	}

	@Override
	public Pedido clone() {
		Pedido copia = new Pedido(getIdRestaurate(), getIdCliente());
		copia.setIdPedido(idPedido);
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
		numeroItensPedido = itens.length;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String string) {
		if (string.compareTo("DEFAULT") == 0) {
			setStatus(Status.DEFAULT);
		} else if (string.compareTo("DEFAULT") == 0) {
			setStatus(Status.ENTREGUE);
		} else if (string.compareTo("ENTREGUE") == 0) {
			setStatus(Status.ENVIADO);
		} else if (string.compareTo("ENVIADO") == 0) {
			setStatus(Status.REALIZADO);
		} else if (string.compareTo("REALIZADO") == 0) {
			setStatus(Status.PREPARANDO);
		}
	}

	public int getNumeroItensPedido() {
		return numeroItensPedido;
	}

	public void setNumeroItensPedido(int numeroItensPedido) {
		this.numeroItensPedido = numeroItensPedido;
	}
}