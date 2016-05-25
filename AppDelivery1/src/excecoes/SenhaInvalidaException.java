package excecoes;

public class SenhaInvalidaException extends Exception{
	
	public SenhaInvalidaException(String regraCriarSenha) {
		super("Senha invalida : "+regraCriarSenha);
	}
}
