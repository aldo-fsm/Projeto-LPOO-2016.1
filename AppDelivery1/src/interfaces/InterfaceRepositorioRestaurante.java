package interfaces;

import entidades.Restaurante;
import excecoes.IdInvalidoException;
import excecoes.RepositorioCheioException;
import excecoes.SenhaInvalidaException;

public interface InterfaceRepositorioRestaurante {

	public long getProximoId();

	public void adicionar(Restaurante restaurante) throws IdInvalidoException, SenhaInvalidaException, RepositorioCheioException;

	public void remover(int id);

	public void alterarSenha(int id, String novaSenha) throws IdInvalidoException, SenhaInvalidaException;

	public void alterarNome(int id, String novoNome);

	public Restaurante getRestaurante(int id);

	public Restaurante getRestauranteId(long id);

	public int getNumeroRestaurantes();

	public String listarCardapio(int id);

	public String listarCardapio(long id);

	public Restaurante[] copiar();

	public Restaurante getCopia(long id);

	public void setProximoId(long id);

	public void setRestaurantes(Restaurante[] restaurantes);

	public void setNumeroRestaurantes(int numeroRestaurantes);
}
