package testes;

import entidades.Gerente;
import principais.InterfaceCliente;
import principais.InterfaceGerente;

public class Teste2 {
	public static void main(String[] args){
		Gerente novo = new Gerente();
		/*terei que mudar a estrutura da interface gerente para que possa interagir com o resto*/
		InterfaceGerente iGerente = new InterfaceGerente();
		InterfaceCliente telaCliente = new InterfaceCliente(novo);
		telaCliente.janelas();
	}
}
