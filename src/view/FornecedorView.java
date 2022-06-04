package view;

import java.util.Scanner;

import model.Fornecedor;

public class FornecedorView {
	Scanner leia = new Scanner(System.in);
	Fornecedor fornecedor = new Fornecedor();
	
	
	public void menuFornecedor(Fornecedor fornecedor) {
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
			menuListar(fornecedor);
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
			menuFornecedor(null);
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
		menuFornecedor(fornecedor);
	}

	public void menuListar(Fornecedor fornecedor) {
		System.out.println("* * * LISTA DE FORNECEDORES * * *\n");
		System.out.println("Código: " + fornecedor.getId());
		System.out.println("CNPJ: " + fornecedor.getCnpj());
		System.out.println("Razão Social: " + fornecedor.getRazaoSocial());
		System.out.println("Fantasia: " + fornecedor.getFantasia());
		System.out.println("Tipo de Pessoa: " + fornecedor.getTipoPessoa());

		System.out.println("\n* * * * * * *");
		menuFornecedor(null);
	}

}
