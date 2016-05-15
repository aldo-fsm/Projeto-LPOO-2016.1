package entidades;

import dados.DataBase;
import repositorios.RepositorioPedido;

public class Cliente extends Usuario {

	private long[] favoritos;
	private ItemCardapio[] carrinho = new ItemCardapio[MAX_ITENS_CARRINHO];
	public static final int MAX_ITENS_CARRINHO = 150;
	private int numeroItensCarrinho = 0; // numero atual de itens no carrinho

	public int getNumeroItensCarrinho() {
		return numeroItensCarrinho;
	}

	public Cliente(String login, String senha, String nome) {
		super(login, senha, nome);
	}

	@Override
	public Cliente clone() {
		Cliente copia = new Cliente(getLogin(), getSenha(), getNome());
		copia.setId(getId());
		copia.setCarrinho(getCarrinho());
		copia.setFavoritos(getFavoritos());
		copia.numeroItensCarrinho = getNumeroItensCarrinho();
		return copia;
	}

	// adiciona um item no final do array carrinho
	public void adicionarNoCarrinho(ItemCardapio item) {
		if (numeroItensCarrinho < MAX_ITENS_CARRINHO) {
			carrinho[numeroItensCarrinho] = item;
			numeroItensCarrinho++;
		}
	}

	public String listarCarrinho() {
		String retorno = "";
		for (int i = 0; i < numeroItensCarrinho; i++) {
			retorno = retorno + carrinho[i].toString() + "\n";
		}
		return retorno;
	}

	// remove o item na posicao id do array carrinho
	public void removerDoCarrinho(int id) {
		if (id >= 0 && id < numeroItensCarrinho) {
			while (id < numeroItensCarrinho - 1) {
				carrinho[id] = carrinho[id + 1];
				id++;
			}
			numeroItensCarrinho = id - 1;
		}
	}

	/*
	 * transforma a estrutura do carrinho em uma estrutura que representa um
	 * pedido
	 */
	public void efetuarPedido(long idRestaurante) {
		RepositorioPedido repositorio = DataBase.lerBasePedidos();
		Pedido novoPedido = new Pedido(idRestaurante, getId());
		novoPedido.setItens(carrinho);
		novoPedido.setNumeroItensPedido(numeroItensCarrinho);
		repositorio.adicionar(novoPedido);
		DataBase.salvarEstado(repositorio);
		this.setNumeroItensCarrinho(0);
	}

	@Override
	public String toString() {
		return (getId() + ";" + getLogin() + ";" + getSenha() + ";" + getNome());
	}

	public void setNumeroItensCarrinho(int numeroItensCarrinho) {
		this.numeroItensCarrinho = numeroItensCarrinho;
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

	public ItemCardapio getCarrinho(int i) {
		return carrinho[i];
	}

	public void setCarrinho(ItemCardapio[] carrinho) {
		this.carrinho = carrinho;
	}

}
