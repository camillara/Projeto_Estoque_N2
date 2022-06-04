package view;

import java.util.Scanner;

import model.Usuario;

public class UsuarioView {
	public void menuUsuario(Usuario user) {
		Scanner leia = new Scanner(System.in);
		System.out.println("* * * MENU USUÁRIO * * *\n");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Listar");
		System.out.println("3 - Atualizar");
		System.out.println("4 - Deletar");
		System.out.println("5 - Voltar para o Menu");
		System.out.println("\n* * * * * * *");
		System.out.print("==>");
		int opcao = leia.nextInt();
		switch (opcao) {
		
		case 1:
			menuCadastrar();
			
			break;
		
		case 2: 
			menuListar(user);
			break;
		
		case 3: 
			break;
			
		case 4: 
			break;
		
		case 5: 
			MenuView menu = new MenuView();
			menu.menu();
			break;

		default: 
			System.out.println("Opção inválida. Escolha novamente.");
			menuUsuario(null);
			break;
		
		}
	}
	
	public void menuCadastrar() {
		Scanner leia = new Scanner(System.in);
		Usuario user = new Usuario();
		System.out.println("* * * CADASTRO DE USUÁRIO * * *\n");
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());
		System.out.println("Cadastro realizado com sucesso!");
		System.out.println("\n* * * * * * *");	
		menuUsuario(user);
	}
	
	public void menuListar(Usuario user) {
		System.out.println("* * * LISTA DE USUÁRIOS * * *\n");
		System.out.println("Usuário: " + user.getUsername());
		System.out.println("Senha: " + user.getPassword());
		System.out.println("\n* * * * * * *");	
		
		menuUsuario(null);
	}

}
