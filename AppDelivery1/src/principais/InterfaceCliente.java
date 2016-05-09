package principais;

import dados.DataBase;
import repositorios.RepositorioCliente;

public class InterfaceCliente {
	public static void main(String[] args) {
		DataBase baseDeDados =  new DataBase();
		baseDeDados.salvarEstado(new RepositorioCliente());
	}
}
