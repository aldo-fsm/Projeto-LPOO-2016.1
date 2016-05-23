package excecoes;

public class IdInvalidoException extends Exception {
	
	public IdInvalidoException() {
		super("Id invalido : o id precisa ser maior que 0");
	}

}
