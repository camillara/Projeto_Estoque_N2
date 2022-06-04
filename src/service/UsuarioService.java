package service;

import java.util.ArrayList;

import model.Usuario;

public class UsuarioService {
	
	public Boolean escrever(Usuario user) {
		
		return true;
		
	}
	
	public Boolean ler(Usuario user) {
		
		return true;
		
	}
	
	public ArrayList<Usuario> ler() {
		ArrayList<Usuario> listaUser = new ArrayList<>();
		return listaUser;
		
		
	}
	
	public Boolean deletar(Usuario user) {
		if(existeArquivo()) {
			
			
		}
		else {
			return false;			
		}
		
		return true;
	}
	
	public Boolean atualizar(Usuario user) {
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
