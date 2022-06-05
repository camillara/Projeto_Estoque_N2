package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;
import model.Usuario;

public class ClienteView {

	Scanner leia = new Scanner(System.in);
	Cliente cliente = new Cliente();
	ClienteController clienteController = new ClienteController();

	public void menuCliente() {
		System.out.println("* * * MENU CLIENTE * * *\n");
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
			menuCliente();
			break;

		}
	}

	public void menuCadastrar() {
		System.out.println("* * * CADASTRO DE CLIENTE * * *\n");
		System.out.print("Informe o nome: ");
		cliente.setNome(leia.nextLine().toUpperCase());
		char genero = 'a';
		while (genero != 'M' && genero != 'F') {
			System.out.print("Informe o Genero: [ M - Masculino ] [ F - Feminino ]: ");
			genero = leia.next().toUpperCase().charAt(0);
		}
		if (genero == 'M') {
			cliente.setGenero("Masculino");
		} else {
			cliente.setGenero("Feminino");
		}
		leia.nextLine();
		System.out.print("Informe o CPF: ");
		cliente.setCpf(leia.nextLine());
		System.out.print("Informe o endereço: ");
		cliente.setEndereco(leia.nextLine().toUpperCase());
		System.out.print("Informe o telefone: ");
		cliente.setTelefone(leia.nextLine());
		System.out.println(clienteController.cadastrar(cliente));
		System.out.println("\n* * * * * * *\n");
		menuCliente();
	}

	public void menuListar() {
		ArrayList<Cliente> clienteList = clienteController.listar();
		if (clienteController.listar().isEmpty()) {
			System.out.println("Não possui usuários cadastrados!\n");
		} else {
			System.out.println("* * * LISTA DE CLIENTES * * *\n");
			for (Cliente c : clienteList) {
				System.out.println(c);
			}
		}
		System.out.println("\n* * * * * * *\n");
		menuCliente();
	}

	public void menuAtualizar() {
		System.out.println("* * * ATUALIZAR CLIENTE * * *\n");
		System.out.print("Informe o código do cliente: ");
		cliente.setId(leia.nextInt());
		leia.nextLine();
		System.out.print("Informe o nome: ");
		cliente.setNome(leia.nextLine().toUpperCase());
		char genero = 'a';
		while (genero != 'M' && genero != 'F') {
			System.out.print("Informe o Genero: [ M - Masculino ] [ F - Feminino ]: ");
			genero = leia.next().toUpperCase().charAt(0);
		}
		if (genero == 'M') {
			cliente.setGenero("Masculino");
		} else {
			cliente.setGenero("Feminino");
		}
		leia.nextLine();
		System.out.print("Informe o CPF: ");
		cliente.setCpf(leia.nextLine());
		System.out.print("Informe o endereço: ");
		cliente.setEndereco(leia.nextLine().toUpperCase());
		System.out.print("Informe o telefone: ");
		cliente.setTelefone(leia.nextLine());

		if (!clienteController.atualizar(cliente)) {
			System.out.print("Cliente não encontrado! ");
		} else {
			System.out.print("Cadastro de cliente atualizado com sucesso!");
		}

		System.out.println("\n* * * * * * *");
		menuCliente();
	}

	public void menuDeletar() {
		System.out.println("* * * DELETAR CLIENTE * * *\n");
		System.out.print("Informe o nome: ");
		cliente.setNome(leia.nextLine().toUpperCase());
		System.out.print("Informe o CPF: ");
		cliente.setCpf(leia.nextLine());
		if (clienteController.deletar(cliente)) {
			System.out.println("Cadastro do usuário " + cliente.getNome() + " excluído com sucesso!");
		} else {
			System.out.println("Usuário " + cliente.getNome() + " não foi localizado!");
		}
		System.out.println("\n* * * * * * *\n");
		menuCliente();
	}

}
