package entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dados.DataBase;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;
import repositorios.Repositorio;

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
	public void efetuarPedido(long idRestaurante)
			throws RepositorioCheioException, IdInvalidoException, SenhaInvalidaException {
		Repositorio<Pedido> repositorio = DataBase.lerBasePedidos();
		Pedido novoPedido = new Pedido(idRestaurante, getId());
		novoPedido.setItens(carrinho);
		novoPedido.setNumeroItensPedido(numeroItensCarrinho);
		repositorio.adicionar(novoPedido);
		DataBase.salvarEstadoPedido(repositorio);
		this.setNumeroItensCarrinho(0);

	}

	@Override
	public String toString() {
		return (getId() + ";" + getLogin() + ";" + getSenha() + ";" + getNome());
	}

	public void setNumeroItensCarrinho(int numeroItensCarrinho) {
		this.numeroItensCarrinho = numeroItensCarrinho;
	}

	public void cancelarPedido(int id) {
		Repositorio<Pedido> repositorio = DataBase.lerBasePedidos();
		if (id >= 0 && id < repositorio.getNumeroElementos()) {
			Pedido pedido = repositorio.get(id);
			if (pedido.getIdRestaurate() == this.getId()) {
				pedido.setStatus(Status.CANCELADO);
				DataBase.salvarEstadoPedido(repositorio);
			}
		}
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

	public String listarPedidosInterface(long id) {
		String retorno = "";
		for (int i = 0; i < Repositorio.MAX_NUMERO_ELEMENTOS; i++) {
			if (DataBase.lerBasePedidos().get(i) != (null)) {
				if (DataBase.lerBasePedidos().get(i).getIdCliente() == id) {
					retorno = retorno + DataBase.lerBasePedidos().get(i).getIdPedido() + ". ";
					for (int j = 0; j < DataBase.lerBasePedidos().get(i).getItens().length; j++) {
						if (DataBase.lerBasePedidos().get(i).getItens(j) != (null)) {
							retorno = retorno + " " + DataBase.lerBasePedidos().get(i).getItens(j);
						}
					}
					retorno += "\n";
					;
				}
			}
		}
		return retorno;
	}

	@Override
	public void validar() throws IdInvalidoException, SenhaInvalidaException {
		if (getId() <= 0) {
			throw new IdInvalidoException();
		}
		int numeroDigitosSenha = getSenha().length();
		if (numeroDigitosSenha < 6 || numeroDigitosSenha > 10) {
			throw new SenhaInvalidaException(" a senha deve ter no mínimo 6 dígitos e no máximo 10");
		}
		Pattern p = Pattern.compile("\\W");// padrão de simbolos
		Matcher m3 = p.matcher(getSenha());// confere se algum simbolo aparece
											// na senha
		if (m3.find()) {
			throw new SenhaInvalidaException("a senha deve conter apenas letras e numeros");
		}
	}

}
