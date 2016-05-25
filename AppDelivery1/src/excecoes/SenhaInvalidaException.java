package excecoes;

public class SenhaInvalidaException extends Exception {

	private String regraCriarSenha;

	public SenhaInvalidaException(String regraCriarSenha) {
		super("Senha invalida : " + regraCriarSenha);
		this.regraCriarSenha = regraCriarSenha;
	}

	public String getRegraCriarSenha() {
		return regraCriarSenha;
	}
}
