package controller;

import java.util.ArrayList;

import model.Fornecedor;
import service.FornecedorService;


public class FornecedorController {
FornecedorService fornecedorService = new FornecedorService();
	
	public String cadastrar(Fornecedor fornecedor) {
		if (fornecedorService.ler(fornecedor)) {
			return "Fornecedor já possui cadastro!";
		} else {
			if (fornecedorService.escrever(fornecedor)) {
				return "Fornecedor cadastrado com sucesso!";
			} else {
				return "Tente novamente!";
			}
		}

	}

	public ArrayList<Fornecedor> listar() {
		return fornecedorService.ler();

	}

	public Boolean atualizar(Fornecedor fornecedor) {
		
		
		return fornecedorService.atualizar(fornecedor);

	}

	public Boolean deletar(Fornecedor fornecedor) {
		
		return fornecedorService.deletar(fornecedor);
	}

}
