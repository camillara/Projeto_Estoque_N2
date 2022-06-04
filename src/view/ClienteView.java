package view;

import java.util.Scanner;

import model.Cliente;

public class ClienteView {
	
	Scanner leia = new Scanner(System.in);
	Cliente cliente = new Cliente();
	
	public void menuCliente(Cliente cliente) {
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
			menuListar(cliente);
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
			menuCliente(null);
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
		menuCliente(cliente);
	}

	public void menuListar(Cliente cliente) {
		System.out.println("* * * LISTA DE CLIENTES * * *\n");
		System.out.println("Código: " + cliente.getId());
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Genero: " + cliente.getGenero());
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Endereço: " + cliente.getEndereco());
		System.out.println("Telefone: " + cliente.getTelefone());
		System.out.println("\n* * * * * * *");
		menuCliente(null);
	}

}
