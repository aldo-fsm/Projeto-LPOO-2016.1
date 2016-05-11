package testes;


import entidades.Gerente;
import entidades.ItemCardapio;
import dados.DataBase;
import entidades.Cliente;
import entidades.Restaurante;
import repositorios.RepositorioCliente;
import repositorios.RepositorioRestaurante;

public class Teste1 {
/*	public static void main(String[] args) {

		Gerente gerente = new Gerente();

		//testando o repositorio de restaurantes

		gerente.adicionarRestaurante(new Restaurante("login1", "senha1", "restaurante1"));
		gerente.adicionarRestaurante(new Restaurante("login2", "senha2", "restaurante2"));
		gerente.adicionarRestaurante(new Restaurante("login3", "senha3", "restaurante3"));
		gerente.adicionarRestaurante(new Restaurante("login4", "senha4", "restaurante4"));

		System.out.println("lista de restaurantes\n");
		gerente.listarRestaurantes();

		System.out.println("\n-removerRestaurante(0);");
		gerente.removerRestaurante(0);

		System.out.println("\nnova lista de restaurantes\n");
		gerente.listarRestaurantes();

		System.out.println("\n-alterarNome(2, \"restaurante1\");");
		gerente.repositorioR().alterarNome(2, "restaurante1");
		System.out.println(
				"\no novo nome do restaurante de posicao 2 e:\n" + gerente.repositorioR().getRestaurante(2).getNome());

		System.out.println("\n-alterarSenha(2, \"senha1\");");
		gerente.repositorioR().alterarSenha(2, "senha1");
		System.out.println("\na nova senha do restaurante de posicao 2 e:\n"
				+ gerente.repositorioR().getRestaurante(2).getSenha());

		gerente.adicionarPrato(1, new ItemCardapio("Pizza", 29.90));
		gerente.adicionarPrato(1, new ItemCardapio("Macaronada", 9.90));
		gerente.adicionarPrato(1, new ItemCardapio("Feijoada", 12.90));
		gerente.adicionarPrato(1, new ItemCardapio("Arroz", 5.90));

		System.out.println("\nO cardapio do restaurante numero 1 tem:");
		gerente.listarCardapio(1);

		System.out.println("\n-removerPrato(1, 2);");
		gerente.removerPrato(1, 2);

		System.out.println("\no novo cardapio tem:");
		gerente.listarCardapio(1);

		// testando o repositorio de clientes

		gerente.adicionarCliente(new Cliente("login5", "senha5", "cliente1"));
		gerente.adicionarCliente(new Cliente("login5", "senha5", "cliente2"));
		gerente.adicionarCliente(new Cliente("login5", "senha5", "cliente3"));
		gerente.adicionarCliente(new Cliente("login5", "senha5", "cliente4"));

		System.out.println("\nlista de clientes\n");
		gerente.listarClientes();

		System.out.println("\n-removerCliente(2);");
		gerente.removerCliente(2);

		System.out.println("\nnova lista de clientes\n");
		gerente.listarClientes();

		System.out.println("\n-alterarNome(2, \"Jose\");");
		gerente.repositorioC().alterarNome(2, "Jose");
		System.out
				.println("\no novo nome do cliente de posicao 2 e:\n" + gerente.repositorioC().getCliente(2).getNome());

		System.out.println("\n-alterarSenha(2,\"senhaDeJose\");");
		gerente.repositorioC().alterarSenha(2, "senhaDeJose");
		System.out
				.println("a nova senha do cliente de posicao 2 e:\n" + gerente.repositorioC().getCliente(2).getSenha());

		// testando os metodos de cliente

		gerente.repositorioC().getCliente(1)
				.adicionarNoCarrinho(gerente.repositorioR().getRestaurante(1).getPratoCardapio(1));
		gerente.repositorioC().getCliente(1)
				.adicionarNoCarrinho(gerente.repositorioR().getRestaurante(1).getPratoCardapio(2));
		gerente.repositorioC().getCliente(1)
				.adicionarNoCarrinho(gerente.repositorioR().getRestaurante(1).getPratoCardapio(3));
		gerente.repositorioC().getCliente(1).efetuarPedido(1);

		//testando DataBase
		
		gerente.adicionarPrato(0, new ItemCardapio("nome do prato", 999.99));
		
		DataBase.salvarEstado(gerente);		
		RepositorioCliente testeClientes = DataBase.lerBaseClientes();
		System.out.println(testeClientes.getProximoId());
		for(int i = 0;i<testeClientes.getNumeroClientes();i++){
			System.out.println(testeClientes.getCliente(i));
		}
		RepositorioRestaurante testeRestaurates = DataBase.LerBaseRestaurantes();
		System.out.println(testeRestaurates.getProximoId());
		for(int i = 0;i<testeRestaurates.getNumeroRestaurantes();i++){
			System.out.println(testeRestaurates.getRestaurante(i).toString());
		}
	}*/
}