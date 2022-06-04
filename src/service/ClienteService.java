package service;

import java.util.ArrayList;

import model.Cliente;



public class ClienteService {
	
public Boolean escrever(Cliente cliente) {
		
		return true;
		
	}
	
	public Boolean ler(Cliente cliente) {
		
		return true;
		
	}
	
	public ArrayList<Cliente> ler() {
		ArrayList<Cliente> listaCliente = new ArrayList<>();
		return listaCliente;
		
		
	}
	
	public Boolean deletar(Cliente cliente) {
		if(existeArquivo()) {
			
			
		}
		else {
			return false;			
		}
		
		return true;
	}
	
	public Boolean atualizar(Cliente cliente) {
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
