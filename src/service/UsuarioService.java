package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Usuario;

public class UsuarioService {

	private final String DIR_USER_DB = "src/data_base/usuario.txt";

	File arquivo = new File(DIR_USER_DB);

	public Boolean escrever(Usuario user) {

		try {
			if (existeArquivo()) {

				FileReader arquivoLeitura = new FileReader(DIR_USER_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				user.setIdUsuario(idUsuario()+1);
				String dados = user.getIdUsuario() + ";" + user.getUsername() + ";" + user.getPassword() + "\n";
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
		try {
			if (existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_USER_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					String[] linhaSplit = linha.split(";");
					if (user.getUsername().equals(linhaSplit[1])) {
						return true;
					}
				}

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

	public ArrayList<Usuario> ler() {
		List<Usuario> users = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(DIR_USER_DB))) {
			String linha = br.readLine();
			while (linha != null) {
				String[] vetorUsuarios = linha.split(";");
				Integer idUsuario = Integer.parseInt(vetorUsuarios[0]);
				String nomeUsuario = vetorUsuarios[1];
				String senhaUsuario = vetorUsuarios[2];
				Usuario listaUsuario = new Usuario(idUsuario, nomeUsuario, senhaUsuario);
				users.add(listaUsuario);
				linha = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return (ArrayList<Usuario>) users;
	}

	public Boolean deletar(Usuario user) {
		try {
			if(ler(user)) {
				FileReader lerNoARquivo = new FileReader(DIR_USER_DB);
				BufferedReader br = new BufferedReader(lerNoARquivo);
				String linha = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(user.getUsername() + ";" + user.getPassword()) == false) {
						lista.add(linha);
				}
				linha = br.readLine();			}
				FileWriter apagar = new FileWriter(DIR_USER_DB, true);
				apagar.close();
				br.close();
				lerNoARquivo.close();
				FileWriter escrever = new FileWriter(DIR_USER_DB);
				BufferedWriter bw = new BufferedWriter(escrever);
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i));
					bw.newLine();
				}
				bw.close();
				escrever.close();
				return true;
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("Não foi possível abrir o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
	
		catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;
	}

	public Boolean atualizar(Usuario user) {
		Boolean retorno = false;
		Scanner leia = new Scanner (System.in);
		try{
			if(ler(user)) {
				FileReader lerNoARquivo = new FileReader(DIR_USER_DB);
				BufferedReader br = new BufferedReader(lerNoARquivo);			
				String linha = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(user.getUsername() + ";" + user.getPassword())== false) {
						lista.add(linha);
					} else {
						String[] vetorUsuario = linha.split(";");				
						Integer idUsuarioAlterar = Integer.parseInt(vetorUsuario[0]); // manter o código de usuário						
						String username = vetorUsuario[1]; // manter o username de usuário
						System.out.print("Informe a nova senha: ");
						String password = leia.nextLine();
						linha = idUsuarioAlterar + ";" + username + ";" + password;
						lista.add(linha);
						retorno = true;
						
					}
					linha = br.readLine();
				}
				FileWriter apagar = new FileWriter(DIR_USER_DB, true);
				apagar.close();
				br.close();
				lerNoARquivo.close();
				FileWriter escrever = new FileWriter(DIR_USER_DB);
				BufferedWriter bw = new BufferedWriter(escrever);
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i));
					bw.newLine();
				}
				bw.close();
				escrever.close();
				return retorno;
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("Não foi possível abrir o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
	
		catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return retorno;

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
	
	public Integer idUsuario() {
		criaArquivo();
		Integer qtd=0;
		try {						
			FileReader lerNoARquivo = new FileReader(DIR_USER_DB);
			BufferedReader br = new BufferedReader(lerNoARquivo);		
			String linha = br.readLine();
			ArrayList <String>lista = new ArrayList<>();			
			while(linha!=null) {
				String[] vetorCliente = linha.split(";");				
				Integer idCliente = Integer.parseInt(vetorCliente[0]);
				qtd=idCliente;
				linha = br.readLine();
			}			
			br.close();		
		}	
		catch (IOException e){
			System.out.println("Erro: " + e.getMessage());
		}		
		return qtd;		
	}

}
