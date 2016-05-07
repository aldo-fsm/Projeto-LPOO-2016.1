package entidades;


public class Restaurante extends Usuario{

	private Pedido[] pedidosEspera = new Pedido[MAX_PEDIDOS_ESPERA];
	private ItemCardapio[] cardapio = new ItemCardapio[MAX_PRATOS];
	private static final int MAX_PRATOS = 150;
	private static final int MAX_PEDIDOS_ESPERA = 100;
	private int numeroPratosCardapio = 0; // numero atual de pratos no cardapio

	public Restaurante(String login, String senha, String nome, long id) {
		super(login, senha, nome, id);
	}

	// lista o id de cada pedido em espera
	public void listarPedidos() {
		for (int i = 0; i < MAX_PEDIDOS_ESPERA; i++) {
			if (pedidosEspera[i] != (null)) {
				System.out.println(pedidosEspera[i].getIdPedido());
			}
		}
	}

	// adiciona um prato no final do array cardapio
	public void adicionarPrato(ItemCardapio item) {
		if (numeroPratosCardapio < MAX_PRATOS) {
			cardapio[numeroPratosCardapio] = item;
			numeroPratosCardapio++;
		}
	}

	// remove o prato na posicao id do array cardapio
	public void removerPrato(int id) {
		if (id >= 0 && id < numeroPratosCardapio) {
			while (id < numeroPratosCardapio - 1) {
				cardapio[id] = cardapio[id + 1];
				id++;
			}
			numeroPratosCardapio--;
		}
	}

	public void cancelarPedido() {

	}

	public void confirmarEnvio() {

	}

	public Pedido[] getPedidosEspera() {
		return pedidosEspera;
	}

	public void setPedidosEspera(Pedido[] pedidosEspera) {
		this.pedidosEspera = pedidosEspera;
	}

	public ItemCardapio getPratoCardapio(int id) {
		return cardapio[id];
	}

	public int getNumeroPratosCardapio() {
		return numeroPratosCardapio;
	}

	@Override
	public String toString() {
		String stringRestaurante;
		stringRestaurante = getId() + ";" + getLogin() + ";" + getSenha() + ";" + getNome();
		for (int i = 0; i < numeroPratosCardapio; i++) {
			stringRestaurante += ";" + cardapio[i];
		}
		return stringRestaurante;
	}

}
