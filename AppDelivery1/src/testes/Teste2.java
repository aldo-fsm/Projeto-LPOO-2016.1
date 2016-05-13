package testes;

import dados.DataBase;
import entidades.Gerente;
import entidades.ItemCardapio;
import entidades.Restaurante;
import principais.InterfaceCliente;
import principais.InterfaceGerente;
import repositorios.RepositorioPedido;
import repositorios.RepositorioRestaurante;

public class Teste2 {
	public static void main(String[] args){
//		Gerente novo = new Gerente();
//		/*terei que mudar a estrutura da interface gerente para que possa interagir com o resto*/
//		InterfaceGerente iGerente = new InterfaceGerente();
//		InterfaceCliente telaCliente = new InterfaceCliente(novo);
//		telaCliente.janelas();
//		RepositorioPedido rP = new RepositorioPedido();
//		rP.adicionar(12938, 2038);
//		ItemCardapio a = new ItemCardapio("la", 2.9);
//		a.setId(0);
//		ItemCardapio b = new ItemCardapio("chau", 2.90);
//		b.setId(1);
//		ItemCardapio[] veT = { a, b };
//		
//		
//		rP.getPedidos(0).setItens(veT);
//		rP.adicionar(1234, 12345566);
//		ItemCardapio[] ets = {a};
//		
//		rP.getPedido(1).setItens(ets);
//		DataBase.salvarEstado(rP);
//		ItemCardapio[] ara =DataBase.lerBasePedidos().getPedidos(0).getItens();
//		System.out.println(ara[1]);
		Gerente Teste = DataBase.lerBaseGerente();
		System.out.println(DataBase.lerBaseClientes().getCliente(0));
		System.out.println(DataBase.lerBaseGerente().repositorioC().getCliente(0));
		System.out.println(Teste.repositorioC().getNumeroClientes());
		
	}
}
