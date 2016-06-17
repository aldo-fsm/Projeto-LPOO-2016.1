package entidades;

public class Pedido implements Cloneable {
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
		int i;
		for (i = 0; i < numeroItensPedido; i++) {
			stringPedido += ";" + itens[i];
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
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(String string) {
		if (string.equals("DEFAULT")) {
			setStatus(Status.DEFAULT);
		} else if (string.equals("CANCELADO")) {
			setStatus(Status.CANCELADO);
		} else if (string.equals("ENTREGUE")) {
			setStatus(Status.ENTREGUE);
		} else if (string.equals("ENVIADO")) {
			setStatus(Status.ENVIADO);
		} else if (string.equals("PREPARANDO")) {
			setStatus(Status.PREPARANDO);
		} else if (string.equals("REALIZADO")) {
			setStatus(Status.REALIZADO);
		}
	}

	public String listarItens() {
		String lista = "";

		for (int i = 0; i < numeroItensPedido; i++) {
			lista += itens[i] + " ;  ";
		}

		return lista;
	}

	public int getNumeroItensPedido() {
		return numeroItensPedido;
	}

	public void setNumeroItensPedido(int numeroItensPedido) {
		this.numeroItensPedido = numeroItensPedido;
	}

	public String toExport() {
		String exportada = idPedido + "," + idCliente + "," + idRestaurate + "," + status;
		return exportada;
	}

}