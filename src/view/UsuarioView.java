package view;

import java.util.ArrayList;
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
			menuAtualizar();
			break;

		case 4:
			menuDeletar();
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
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());
		System.out.println(userController.cadastrar(user));
		System.out.println("\n* * * * * * *\n");
		menuUsuario();
	}

	public void menuListar() {
		ArrayList<Usuario> userList = userController.listar();
		if (userController.listar().isEmpty()) {
			System.out.println("Não possui usuários cadastrados!\n");
		} else {
			System.out.println("* * * LISTA DE USUÁRIOS * * *\n");
			for (Usuario u : userList) {
				System.out.println(u);
			}
		}
		System.out.println("\n* * * * * * *\n");
		menuUsuario();
	}

	public void menuAtualizar() {
		System.out.println("* * * ATUALIZAR USUÁRIO * * *\n");
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());

		if (!userController.atualizar(user)) {
			System.out.println("Usuário e senha informados não encontrado! ");
			System.out.println("Por segurança, para alterar o cadastro de usuário é necessário informar a o Uername e Password corretos.");
		} else {
			System.out.println("Cadastro de usuário atualizado com sucesso!");
		}

		System.out.println("\n* * * * * * *\n");
		menuUsuario();
	}

	public void menuDeletar() {
		System.out.println("* * * DELETAR USUÁRIO * * *\n");
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());
		if(userController.deletar(user)) {
			System.out.println("Cadastro do usuário " + user.getUsername() + " excluído com sucesso!");
		}
		else {
			System.out.println("Usuário " + user.getUsername() + " não foi localizado!");
		}
		System.out.println("\n* * * * * * *\n");
		menuUsuario();
	}

}
