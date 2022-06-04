package view;

import java.util.Scanner;

import controller.UsuarioController;
import model.Usuario;

public class UsuarioView {
	Scanner leia = new Scanner(System.in);
	Usuario user = new Usuario();
	UsuarioController userController = new UsuarioController();

	public void menuUsuario() {
		System.out.println("* * * MENU USUÁRIO * * *\n");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Listar");
		System.out.println("3 - Atualizar");
		System.out.println("4 - Deletar");
		System.out.println("5 - Voltar para o Menu");
		System.out.println("\n* * * * * * *");
		System.out.print("==>");
		int opcao = leia.nextInt();
		leia.nextLine();
		switch (opcao) {

		case 1:
			menuCadastrar();

			break;

		case 2:
			menuListar();
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
			menuUsuario();
			break;

		}
	}

	public void menuCadastrar() {
		System.out.println("* * * CADASTRO DE USUÁRIO * * *\n");
		//leia.nextLine();
		//System.out.print("Informe o código usuário: ");
		//user.setIdUsuario(leia.nextInt());
		//leia.nextLine();
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());
		System.out.println("Cadastro realizado com sucesso!");
		System.out.println("\n* * * * * * *");


		userController.cadastrar(user);

		menuUsuario();
	}

	public void menuListar() {

		userController.listar();
		System.out.println("Não possui usuário cadastrado!\n");

		System.out.println("* * * LISTA DE USUÁRIOS * * *\n");
		user.toString();
		System.out.println("\n* * * * * * *");

		menuUsuario();
	}

	public void menuAtualizar() {
		System.out.println("* * * ATUALIZAR USUÁRIO * * *\n");
		leia.nextLine();
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());

		if (!userController.atualizar(user)) {
			System.out.print("Usuário não encontrado! ");
		} else {
			System.out.print("Cadastro de usuário atualizado com sucesso!");
		}

		System.out.println("\n* * * * * * *");
	}
	
	public void menuDeletar() {
		System.out.println("* * * DELETAR USUÁRIO * * *\n");
		leia.nextLine();
		System.out.print("Informe o código usuário: ");
		user.setIdUsuario(leia.nextInt());
		leia.nextLine();
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());
		
		if(!userController.deletar(user)) {
			System.out.print("Usuário não encontrado!");
		}
		else {
			System.out.print("Cadastro do usuário excluído com sucesso!");
		}
	}

}
