package service;

import java.util.ArrayList;

import model.Fornecedor;


public class FornecedorService {
	
public Boolean escrever(Fornecedor fornecedor) {
		
		return true;
		
	}
	
	public Boolean ler(Fornecedor fornecedor) {
		
		return true;
		
	}
	
	public ArrayList<Fornecedor> ler() {
		ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();
		return listaFornecedor;
		
		
	}
	
	public Boolean deletar(Fornecedor fornecedor) {
		if(existeArquivo()) {
			
			
		}
		else {
			return false;			
		}
		
		return true;
	}
	
	public Boolean atualizar(Fornecedor fornecedor) {
		if(existeArquivo()) {
			
		}
		else {
			return false;
		}
		return true;		
	}
	
	private Boolean existeArquivo() {
		
		return true;
	}
	
	private Boolean criaArquivo() {
		
		return true;
	}

}
