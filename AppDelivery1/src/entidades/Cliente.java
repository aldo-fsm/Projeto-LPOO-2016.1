package entidades;


public class Cliente extends Usuario{

	private long[] favoritos;
	private ItemCardapio[] carrinho = new ItemCardapio[MAX_ITENS_CARRINHO];
	private static final int MAX_ITENS_CARRINHO = 150;
	private int numeroItensCarrinho = 0; // numero atual de itens no carrinho

	public Cliente(String login, String senha, String nome, long id) {
		super(login, senha, nome, id);
	}

	// adiciona um item no final do array carrinho
	public void adicionarNoCarrinho(ItemCardapio item) {
		if (numeroItensCarrinho < MAX_ITENS_CARRINHO) {
			carrinho[numeroItensCarrinho] = item;
			numeroItensCarrinho++;
		}
	}

	// remove o item na posicao id do array carrinho
	public void removerDoCarrinho(int id) {
		if (id >= 0 && id < numeroItensCarrinho) {
			while (id < numeroItensCarrinho - 1) {
				carrinho[id] = carrinho[id + 1];
				id++;
			}
			numeroItensCarrinho--;
		}
	}

	/*
	 * transforma a estrutura do carrinho em uma estrutura que representa um
	 * pedido
	 */
	public void efetuarPedido(long idRestaurante) {
		String stringPedido;
		stringPedido = getId() + "; " + idRestaurante + "; PREPARANDO;";
		for (int n = 0; n < numeroItensCarrinho; n++) {
			stringPedido = stringPedido+ "; " + (n + 1) + ". " + carrinho[n].getId() + "/" + carrinho[n].getNome()
					+ "/" + carrinho[n].getPreco();
		}

	}

	@Override
	public String toString() {
		return (getId() + ";" + getLogin() + ";" + getSenha() + ";" + getNome());
	}
	
	public void cancelarPedido() {

	}

	public long[] getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(long[] favoritos) {
		this.favoritos = favoritos;
	}

	public ItemCardapio[] getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(ItemCardapio[] carrinho) {
		this.carrinho = carrinho;
	}

}
