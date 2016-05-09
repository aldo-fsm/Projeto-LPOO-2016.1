package principais;

import java.util.Scanner;
import dados.DataBase;
import entidades.Cliente;
import entidades.Gerente;
import entidades.ItemCardapio;
import entidades.Restaurante;

public class InterfaceGerente {
	public static void main(String[] args) {

		// se nao for statico agente muda depois de perguntar
		Scanner sc = new Scanner(System.in);
		Gerente novo = DataBase.lerBaseGerente();
		do {
			System.out.println("1. Adicionar/Cadastar um cliente\n2. Remover um Cliente");
			int opcao = sc.nextInt();
			if (opcao == 1) {
				//  Adicionar/Cadastar um cliente:
				//  Pede as informações inerentes ao Cliente;
				System.out.println("nome do cliente: ");
				sc.nextLine();
				String nomeDoCliente = sc.nextLine();
				System.out.println("login do cliente: ");
				String loginDoCliente = sc.nextLine();
				System.out.println("senha do cliente: ");
				String senhaDoCliente = sc.nextLine();
				//  Instacia o Cliente;
				Cliente Aryell = new Cliente(loginDoCliente, senhaDoCliente, nomeDoCliente);
				//  Adiciona o novo Cliente ao sistema;
				novo.adicionarCliente(Aryell);
				//  Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioC());
			} else if (opcao == 2) {
				//  Remover um Cliente:
				//  Uma lista com todos os clientes cadastrados e seus
				// respectivos ID’s deve ser exibida;
				novo.listarClientes();
				//  Solicitar o ID do Cliente que deve ser removido;
				System.out.println("informe o id do cliente a ser removido");
				int id = sc.nextInt();
				//  Remover o Cliente cujo ID foi fornecido.
				novo.removerCliente(id);
				//  Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioC());
			} else if (opcao == 3) {
				//  Adicionar um Restaurante
				//  Pede as informações inerentes ao Restaurante;
				System.out.println("nome do restaurante : ");
				sc.nextLine();
				String nomeDoRestaurante = sc.nextLine();
				System.out.println("login do restaurante : ");
				String loginDoRestaurante = sc.nextLine();
				System.out.println("senha do restaurante: ");
				String senhaDoRestaurante = sc.nextLine();
				//  Instacia o Restaurante;
				Restaurante Atlantico = new Restaurante(loginDoRestaurante, senhaDoRestaurante, nomeDoRestaurante);
				//  Adiciona o novo Restaurante ao sistema;
				novo.adicionarRestaurante(Atlantico);
				//  Salva o estado do sistema
				DataBase.salvarEstado(novo.repositorioR());
			} else if (opcao == 4) {
				//  Remover um Restaurante
				//  Uma lista com todos os Restaurantes cadastrados e seus
				// respectivos ID’s deve ser exibida;
				novo.listarRestaurantes();
				//  Solicitar o ID do Restaurante que deve ser removido;
				System.out.println("informe o id do restaurante a ser removido");
				int idDoRestaurnate = sc.nextInt();
				//  Remover o Restaurante cujo ID foi fornecido.
				novo.removerRestaurante(idDoRestaurnate);
				//  Salva o estado do sistema
				DataBase.salvarEstado(novo.repositorioR());
			} else if (opcao == 5) {
				//  Adicionar Prato
				//  Exibe uma lista com todos os Restaurantes cadastrados e seus respectivos ID’s;
				novo.listarRestaurantes();
				//  Solicitar o ID do Restaurante que deve receber o novo prato;
				System.out.println("informe o id do restaurante a receber o novo prato");
				int idDoRestaurante = sc.nextInt();
				//  Solicita as informações inerentes ao novo prato;
				System.out.println("nome do prato : ");
				sc.nextLine();
				String nomeDoPrato = sc.nextLine();
				System.out.println("informe o id do prato: ");
				long idDoPrato = sc.nextLong();
				System.out.println("informe o preco do prato: ");
				double precoDoPrato = sc.nextDouble();
				// Instancia e adiciona o novo prato 
				ItemCardapio novoPrato = new ItemCardapio(idDoPrato, nomeDoPrato, precoDoPrato);
				novo.adicionarPrato(idDoRestaurante, novoPrato);
				// Salva o estado do sistema;
				DataBase.salvarEstado(novo.repositorioR());
			}else if(opcao == 6){
				//  Remover Prato 
				//  Exibe uma lista com todos os Restaurantes cadastrados e  seus respectivos ID’s;
				novo.listarRestaurantes();
                //  Solicitar o ID do Restaurante que deve ter o prato removido de seu Cardápio; 
				System.out.println("informe o id do restaurante a remover o prato");
				int idDoRestaurante = sc.nextInt();
				//  Exibe o Cardápio desse Restaurante e os ID’s de cada item; 
				novo.listarCardapio(idDoRestaurante);
				//  Solicitar o ID do prato a ser Removido; 
				System.out.println("informe o id do prato a ser removido");
				int idDoPrato= sc.nextInt();
				//  Remover o prato; 
				novo.removerPrato(idDoRestaurante, idDoPrato);
				//  Salva o estado do sistema;   
				DataBase.salvarEstado(novo.repositorioR());
			}

			System.out.print("deseja mais alguma coisa?\n");
		} while (sc.nextLine().equals("sim"));
		sc.close();
	}
}
