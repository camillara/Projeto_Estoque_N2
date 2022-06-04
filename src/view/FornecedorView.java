package view;

import java.util.Scanner;

import controller.FornecedorController;
import model.Fornecedor;

public class FornecedorView {
	Scanner leia = new Scanner(System.in);
	Fornecedor fornecedor = new Fornecedor();
	FornecedorController fornecedorController = new FornecedorController();
	
	
	public void menuFornecedor() {
		System.out.println("* * * MENU FORNECEDOR * * *\n");
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
			menuFornecedor();
			break;

		}
	}

	public void menuCadastrar() {
		System.out.println("* * * CADASTRO DE FORNECEDOR * * *\n");
		System.out.print("Informe o código do fornecedor: ");
		fornecedor.setId(leia.nextInt());
		leia.nextLine();
		System.out.print("Informe o CPF / CNPJ: ");
		fornecedor.setCnpj(leia.nextLine());
		System.out.print("Informe a Razão Social: ");
		fornecedor.setRazaoSocial(leia.nextLine().toUpperCase());
		System.out.print("Informe o nome fantasia: ");
		fornecedor.setFantasia(leia.nextLine().toUpperCase());
		Integer tipoPessoa = 0;
		while (tipoPessoa != 1 && tipoPessoa != 2) {
			System.out.print("Informe o tipo: [ 1 - Pessoa Física ] [ 2 - Pessoa Jurídica ]: ");
			tipoPessoa = leia.nextInt();
		}
		fornecedor.setTipoPessoa(tipoPessoa);
		leia.nextLine();
		System.out.println("Cadastro realizado com sucesso!");
		System.out.println("\n* * * * * * *");
		fornecedorController.cadastrar(fornecedor);
		menuFornecedor();
	}

	public void menuListar() {
		
		fornecedorController.listar();
		
		System.out.println("Não possui usuário cadastrado!\n");
		System.out.println("* * * LISTA DE FORNECEDORES * * *\n");
		fornecedor.toString();
		System.out.println("\n* * * * * * *");

		menuFornecedor();
	}
	
	public void menuAtualizar() {
		System.out.println("* * * ATUALIZAR FORNECEDOR * * *\n");
		System.out.print("Informe o código do fornecedor: ");
		fornecedor.setId(leia.nextInt());
		leia.nextLine();
		System.out.print("Informe o CPF / CNPJ: ");
		fornecedor.setCnpj(leia.nextLine());
		System.out.print("Informe a Razão Social: ");
		fornecedor.setRazaoSocial(leia.nextLine().toUpperCase());
		System.out.print("Informe o nome fantasia: ");
		fornecedor.setFantasia(leia.nextLine().toUpperCase());
		Integer tipoPessoa = 0;
		while (tipoPessoa != 1 && tipoPessoa != 2) {
			System.out.print("Informe o tipo: [ 1 - Pessoa Física ] [ 2 - Pessoa Jurídica ]: ");
			tipoPessoa = leia.nextInt();
		}
		fornecedor.setTipoPessoa(tipoPessoa);
		leia.nextLine();

		if (!fornecedorController.atualizar(fornecedor)) {
			System.out.print("Fornecedor não encontrado! ");
		} else {
			System.out.print("Cadastro de fornecedor atualizado com sucesso!");
		}

		System.out.println("\n* * * * * * *");
	}
	
	public void menuDeletar() {
		System.out.println("* * * DELETAR FORNECEDOR * * *\n");
		System.out.print("Informe o código do fornecedor: ");
		fornecedor.setId(leia.nextInt());
		leia.nextLine();
		System.out.print("Informe o CPF / CNPJ: ");
		fornecedor.setCnpj(leia.nextLine());
		System.out.print("Informe a Razão Social: ");
		fornecedor.setRazaoSocial(leia.nextLine().toUpperCase());
		System.out.print("Informe o nome fantasia: ");
		fornecedor.setFantasia(leia.nextLine().toUpperCase());
		Integer tipoPessoa = 0;
		while (tipoPessoa != 1 && tipoPessoa != 2) {
			System.out.print("Informe o tipo: [ 1 - Pessoa Física ] [ 2 - Pessoa Jurídica ]: ");
			tipoPessoa = leia.nextInt();
		}
		fornecedor.setTipoPessoa(tipoPessoa);
		leia.nextLine();
		
		if(!fornecedorController.deletar(fornecedor)) {
			System.out.print("Fornecedor não encontrado!");
		}
		else {
			System.out.print("Cadastro do fornecedor excluído com sucesso!");
		}
	}

}
