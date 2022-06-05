package view;

public class LoginView extends ApplicationView{
	
	public void login() {
		System.out.println("* * * LOGIN * * *");
		System.out.print("Informe o usuário: ");
		user.setUsername(leia.nextLine().toUpperCase());
		System.out.print("Informe a senha: ");
		user.setPassword(leia.nextLine());		
		if(userController.validarLoginUsuario(user)) {
		System.out.println("Logado com sucesso");
		}
		else {
			System.out.println("Usuário ou Senha incorretos.");
			System.out.println("Tente novamente.\n");
			login();//recursão
		}
		System.out.println("* * * * * * * * * *\n");
		
		MenuView menu = new MenuView();
		menu.menu();
	}

}
