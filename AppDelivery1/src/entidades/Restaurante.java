package entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dados.DataBase;
import excecoes.IdInvalidoException;
import excecoes.SenhaInvalidaException;
import repositorios.Repositorio;

public class Restaurante extends Usuario {

	private Pedido[] pedidosEspera = new Pedido[MAX_PEDIDOS_ESPERA];
	private ItemCardapio[] cardapio = new ItemCardapio[MAX_PRATOS];
	public static final int MAX_PRATOS = 150;
	private static final int MAX_PEDIDOS_ESPERA = 100;
	private int numeroPedidosEspera = 0;
	private int numeroPratosCardapio = 0; // numero atual de pratos no cardapio
	private long proximoId = 1; // proximo id disponivel para itens no cardapio

	public Restaurante(String login, String senha, String nome) {
		super(login, senha, nome);
	}

	// lista o id de cada pedido em espera
	public void listarPedidos() {
		for (int i = 0; i < MAX_PEDIDOS_ESPERA; i++) {
			if (pedidosEspera[i] != (null)) {
				System.out.println(pedidosEspera[i].getIdPedido());
			}
		}
	}
	

	@Override
	public Restaurante clone() {
		Restaurante copia = new Restaurante(getLogin(), getSenha(), getNome());
		copia.setId(getId());
		copia.setPedidosEspera(pedidosEspera);
		copia.setCardapio(cardapio);
		copia.numeroPratosCardapio = getNumeroPratosCardapio();
		return copia;
	}

	public void setCardapio(ItemCardapio[] cardapio) {
		this.cardapio = cardapio;
	}

	public void atualizarListaPedidos() {
		Repositorio<Pedido> repositorioPedido = DataBase.lerBasePedidos();
		Pedido pedido;
		int j = 0;
		for (int i = 0; i < repositorioPedido.getNumeroElementos(); i++) {
			pedido = repositorioPedido.get(i);
			if (getId() == pedido.getIdRestaurate()) {
				pedidosEspera[j] = pedido;
				j++;
			}
		}
		numeroPedidosEspera = j;
	}

	// adiciona um prato no final do array cardapio
	public void adicionarPrato(ItemCardapio item) {
		if (numeroPratosCardapio < MAX_PRATOS) {
			cardapio[numeroPratosCardapio] = item;
			item.setId(proximoId);
			numeroPratosCardapio++;
			proximoId++;
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

	public void confirmarEnvio(int id) {
		Repositorio<Pedido> repositorio = DataBase.lerBasePedidos();
		if (id >= 0 && id < repositorio.getNumeroElementos()) {
			Pedido pedido = repositorio.get(id);
			if (pedido.getIdRestaurate() == this.getId()) {
				pedido.setStatus(Status.ENVIADO);
				DataBase.salvarEstadoPedido(repositorio);
			}
		}
	}

	public void setPedidosEspera(Pedido[] pedidosEspera) {
		this.pedidosEspera = pedidosEspera;
		numeroPedidosEspera = pedidosEspera.length; 
	}

	public ItemCardapio getPratoCardapio(int id) {
		return cardapio[id];
	}

	public ItemCardapio getPratoCardapio(long id) {
		int i;
		for (i = 0; i < numeroPratosCardapio; i++) {
			if (cardapio[i].getId() == id) {
				break;
			}
		}
		return cardapio[i];
	}

	public int getNumeroPratosCardapio() {
		return numeroPratosCardapio;
	}

	public String listarCardapio() {
		String cardapio = "";
		for (int i = 0; i < getNumeroPratosCardapio(); i++) {
			cardapio = cardapio + getPratoCardapio(i).getId() + ". " + getPratoCardapio(i).getNome() + " -------- "
					+ getPratoCardapio(i).getPreco() + "\n";
		}
		return cardapio;
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

	public void setNumeroPratosCardapio(int numeroPratosCardapio) {
		this.numeroPratosCardapio = numeroPratosCardapio;
	}

	public void setProximoId(long proximoId) {
		this.proximoId = proximoId;
	}

	public long getProximoId() {
		return proximoId;
	}

	@Override
	public void validar() throws IdInvalidoException, SenhaInvalidaException {
		if (getId() <= 0) {
			throw new IdInvalidoException();
		}
		int numeroDigitosSenha = getSenha().length();
		if (numeroDigitosSenha < 10 || numeroDigitosSenha > 18) {
			throw new SenhaInvalidaException("a senha deve ter no m�nimo 10 d�gitos e no m�ximo 18");
		}
		Pattern padraoNumeros = Pattern.compile("[0-9]");// cria um padrao de
															// nmeros
		Pattern padraoLetras = Pattern.compile("[a-z]");// cria um padr�o de
														// letras
		Matcher m1 = padraoLetras.matcher(getSenha().toLowerCase());// compara o
																	// padrao de
																	// letras a
																	// senha
																	// minuscula
		Matcher m2 = padraoNumeros.matcher(getSenha());/// compara o padrao de
														/// numeros com a senha
		if (!(m1.find() && m2.find())) {
			throw new SenhaInvalidaException("a senha deve conter pelo menos uma letra e um numero");
		}
		Pattern p = Pattern.compile("\\W");// padr�o de simbolos
		Matcher m3 = p.matcher(getSenha());// confere se algum simbolo aparece
											// na senha
		if (m3.find()) {
			throw new SenhaInvalidaException("a senha deve conter apenas letras e numeros");
		}

	}

	public int getNumeroPedidosEspera() {
		return numeroPedidosEspera;
	}

	public void setNumeroPedidosEspera(int numeroPedidosEspera) {
		this.numeroPedidosEspera = numeroPedidosEspera;
	}

	public Pedido getPedidoEspera(int i) {
		return pedidosEspera[i];
	}

	public String toExport() {
		String exportada = getId() + ";" + getLogin() + ";" + getSenha() + ";" + getNome();
		return exportada;
	}
	public ItemCardapio[] getCardapio() {
		return cardapio;
	}
}
