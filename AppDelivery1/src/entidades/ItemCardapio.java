package entidades;

public class ItemCardapio {

	private long id;
	private String nome;
	private double preco;

	public ItemCardapio(long id, String nome, double preco) {

		setId(id);
		setNome(nome);
		setPreco(preco);

	}
	
	@Override
	public String toString() {
		return (id + "/" + nome + "/" + preco);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
