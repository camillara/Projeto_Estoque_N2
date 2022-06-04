package view;

import java.util.Scanner;

public class LoginView {
	
	public void login() {
		Scanner leia = new Scanner(System.in);
		System.out.println("* * * LOGIN * * *");
		System.out.print("Informe o usuário: ");
		String username = leia.nextLine();
		System.out.print("Informe a senha: ");
		String password = leia.nextLine();
		System.out.println("Logado com sucesso");
		System.out.println("* * * * * * * * * *\n");
		
		MenuView menu = new MenuView();
		menu.menu();
	}

}
