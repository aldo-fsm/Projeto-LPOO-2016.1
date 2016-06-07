package entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import excecoes.IdInvalidoException;
import excecoes.SenhaInvalidaException;

public class Restaurante extends Usuario {

	private Pedido[] pedidosEspera = new Pedido[MAX_PEDIDOS_ESPERA];
	private ItemCardapio[] cardapio = new ItemCardapio[MAX_PRATOS];
	public static final int MAX_PRATOS = 150;
	private static final int MAX_PEDIDOS_ESPERA = 100;
	private int numeroPratosCardapio = 0; // numero atual de pratos no cardapio
	private long proximoId = 0; // proximo id disponivel para itens no cardapio

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

	public String[] listarCardapio() {
		String[] retorno = new String[numeroPratosCardapio];
		for (int i = 0; i < numeroPratosCardapio; i++) {
			retorno[i] = cardapio[i].getNome();
		}

		return retorno;
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
			throw new SenhaInvalidaException("a senha deve ter no mínimo 10 dígitos e no máximo 18");
		}
		Pattern padraoNumeros = Pattern.compile("[0-9]");//cria um padrao de nmeros
		Pattern padraoLetras = Pattern.compile("[a-z]");//cria um padrão de letras 
		Matcher m1 = padraoLetras.matcher(getSenha().toLowerCase());//compara o padrao de letras a senha minuscula
		Matcher m2 = padraoNumeros.matcher(getSenha());///compara o padrao de numeros com a senha 
		if (!(m1.find() && m2.find())) {
			throw new SenhaInvalidaException("a senha deve conter pelo menos uma letra e um numero");
		}
		Pattern p = Pattern.compile("\\W");//padrão de simbolos
		Matcher m3 = p.matcher(getSenha());//confere se algum simbolo aparece na senha
		if (m3.find()) {
			throw new SenhaInvalidaException("a senha deve conter apenas letras e numeros");
		}

	}

}
