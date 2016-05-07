package entidades;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private long id;

	public Usuario(String login, String senha, String nome, long id) {

		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setId(id);

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
