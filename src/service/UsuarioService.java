package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Usuario;

public class UsuarioService {

	private final String DIR_USER_DB = "src/data_base/usuario.txt";

	File arquivo = new File(DIR_USER_DB);

	public Boolean escrever(Usuario user) {

		try {
			if (existeArquivo()) {

				FileReader arquivoLeitura = new FileReader(DIR_USER_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				int contadorLinha = 1;
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					contadorLinha++;
				}
				user.setIdUsuario(contadorLinha);
				String dados = user.getIdUsuario() + ";" + user.getUsername() + ";" + user.getPassword()+"\n";
				FileWriter escreverArquivo = new FileWriter(arquivo, true);
				escreverArquivo.write(dados);
				escreverArquivo.close();
				return true;
			} else {
				criaArquivo();
				escrever(user); // recursão
			}

		} catch (FileNotFoundException e) {
			System.out.println("Não foi possível abrir o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}

		catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;
	}

	public Boolean ler(Usuario user) {

		return false;

	}

	public ArrayList<Usuario> ler() {
		ArrayList<Usuario> listaUser = new ArrayList<>();
		return listaUser;

	}

	public Boolean deletar(Usuario user) {
		if (existeArquivo()) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean atualizar(Usuario user) {
		if (existeArquivo()) {
			return true;
		} else {
			return false;
		}

	}

	private Boolean existeArquivo() {
		return arquivo.exists();
	}

	private Boolean criaArquivo() {

		try {
			if (arquivo.exists()) {
				return false;
			} else {
				return arquivo.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao criar o arquivo de usuário: ");
			System.out.println("O erro gerado é: " + e.getMessage());
			return false;
		}

	}

}
