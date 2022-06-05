package controller;

import java.util.ArrayList;

import model.Cliente;
import service.ClienteService;

public class ClienteController {

	ClienteService clienteService = new ClienteService();

	public String cadastrar(Cliente cliente) {
		if (clienteService.ler(cliente)) {
			return "Cliente já possui cadastro!";
		} else {
			if (clienteService.escrever(cliente)) {
				return "Cliente cadastrado com sucesso!";
			} else {
				return "Tente novamente!";
			}
		}

	}

	public ArrayList<Cliente> listar() {
		return clienteService.ler();

	}

	public Boolean atualizar(Cliente cliente) {

		return clienteService.atualizar(cliente);

	}

	public Boolean deletar(Cliente cliente) {
		return clienteService.deletar(cliente);
	}

}
