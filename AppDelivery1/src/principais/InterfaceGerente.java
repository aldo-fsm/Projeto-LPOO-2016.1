package principais;

import java.util.Scanner;
import dados.DataBase;
import entidades.Cliente;
import entidades.Gerente;

public class InterfaceGerente {
	public static void main(String[] args) {

		// se nao for statico agente muda depois de perguntar

		Scanner sc = new Scanner(System.in);
		Gerente novo = DataBase.lerBaseGerente();
		System.out.print("1. Adicionar/Cadastar um cliente\n");
		int opcao = sc.nextInt();

		if (opcao == 1) {
			// a. Adicionar/Cadastar um cliente:
			// i. Pede as informações inerentes ao Cliente;

			System.out.print("nome do cliente: ");
			sc.nextLine();
			String nomeDoCliente = sc.nextLine();
			System.out.print("login do cliente: ");
			String loginDoCliente = sc.nextLine();
			System.out.print("senha do cliente: ");
			String senhaDoCliente = sc.nextLine();

			// por ser incremental acredito que nao seja necessario:
			// System.out.println("id do cliente: ");
			// Long idDoCliente = sc.nextLong();
			// ii. Instacia o Cliente;
			Cliente Aryell = new Cliente(loginDoCliente, senhaDoCliente, nomeDoCliente);

			// iii. Adiciona o novo Cliente ao sistema;

			novo.adicionarCliente(Aryell);

			// iv. Salva o estado do sistema;

			DataBase.salvarEstado(novo.repositorioC());
		}else if(opcao == 2){
			
		}
	}
}
