package view;

import java.util.Scanner;

public class MenuView {
	public void menu() {
		Scanner leia = new Scanner(System.in);
		System.out.println("* * * MENU * * *\n");
		System.out.println("1 - Usuário");
		System.out.println("2 - Fornecedor");
		System.out.println("3 - Tipo Pessoa");
		System.out.println("4 - Tipo Produto");
		System.out.println("5 - Cliente");
		System.out.println("6 - Produto");
		System.out.println("0 - Sair");
		System.out.println("\n* * * * * * *");
		System.out.print("==>");
		int opcao = leia.nextInt();
		switch (opcao) {
		case 0:
			System.out.println("Sistema encerrado");
			System.exit(0);
			break;
		
		case 1:
			UsuarioView userView = new UsuarioView();
			userView.menuUsuario();
			
			break;
		
		case 2: 
			FornecedorView fornecedorView = new FornecedorView();
			fornecedorView.menuFornecedor();
			break;
		
		case 3: 
			break;
			
		case 4: 
			break;
		
		case 5: 
			ClienteView clienteView = new ClienteView();
			clienteView.menuCliente();
			break;
			
		case 6: 
			break;
			
		default: 
			System.out.println("Opção inválida. Escolha novamente.");
			menu();
			break;
		
		}
	}


}
