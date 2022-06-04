package view;

import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;

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
			menuCliente();
			break;

		}
	}

	public void menuCadastrar() {
		System.out.println("* * * CADASTRO DE CLIENTE * * *\n");
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
		System.out.println("Cadastro realizado com sucesso!");
		System.out.println("\n* * * * * * *");
		clienteController.cadastrar(cliente);
		
		menuCliente();
	}

	public void menuListar() {
		
		clienteController.listar();
		System.out.println("Não possui usuário de cliente!\n");

		System.out.println("* * * LISTA DE CLIENTES * * *\n");
		cliente.toString();
		System.out.println("\n* * * * * * *");
		
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
	}

	public void menuDeletar() {
		System.out.println("* * * DELETAR CLIENTE * * *\n");
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
		
		if(!clienteController.deletar(cliente)) {
			System.out.print("Cliente não encontrado!");
		}
		else {
			System.out.print("Cadastro do cliente excluído com sucesso!");
		}
	}
	
}
