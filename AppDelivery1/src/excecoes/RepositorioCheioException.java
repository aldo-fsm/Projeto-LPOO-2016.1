package excecoes;

@SuppressWarnings("serial")
public class RepositorioCheioException extends Exception {
	public RepositorioCheioException() {
		super("Nao existe espaco suficiente no repositorio");
	}
}
