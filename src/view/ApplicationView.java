package view;

import java.util.Scanner;

import controller.ClienteController;
import controller.FornecedorController;
import controller.UsuarioController;
import model.Cliente;
import model.Fornecedor;
import model.Usuario;

public abstract class ApplicationView {
	
	public Scanner leia;
	public Usuario user;
	public UsuarioController userController;
	public Cliente cliente;
	public ClienteController clienteController;
	public Fornecedor fornecedor;
	public FornecedorController fornecedorController;
	
	public ApplicationView() {
		leia = new Scanner(System.in);
		user = new Usuario();
		userController = new UsuarioController();
		cliente = new Cliente();
		clienteController = new ClienteController();
		fornecedor = new Fornecedor();
		fornecedorController = new FornecedorController();
	}

}
