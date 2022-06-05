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
	private File arquivo;
	private BufferedWriter bw;

	public UsuarioService() {
		arquivo = new File(DIR_USER_DB);
	}

	public Boolean escrever(Usuario user) {

		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			if (existeArquivo()) {
				user.setIdUsuario(idUsuario() + 1);
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
		} catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;
	}

	public Boolean ler(Usuario user) {
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			if (existeArquivo()) {
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
		} catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;
	}

	public ArrayList<Usuario> ler() {
		List<Usuario> users = new ArrayList<>();
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			String linha = memoriaLeitura.readLine();
			while (linha != null) {
				Usuario listaUsuario = new Usuario();
				String[] vetorUsuarios = linha.split(";");
				listaUsuario.setIdUsuario(Integer.parseInt(vetorUsuarios[0]));
				listaUsuario.setUsername(vetorUsuarios[1]);
				listaUsuario.setPassword(vetorUsuarios[2]);
				users.add(listaUsuario);
				linha = memoriaLeitura.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return (ArrayList<Usuario>) users;
	}

	public Boolean deletar(Usuario user) {
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			if (ler(user)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(user.getUsername() + ";" + user.getPassword()) == false) {
						lista.add(linha);
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_USER_DB));
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i));
					bw.newLine();
				}
				bw.close();
				return true;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possível abrir o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;
	}

	public Boolean atualizar(Usuario user) {
		Boolean retorno = false;
		Scanner leia = new Scanner(System.in);
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			if (ler(user)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(user.getUsername() + ";" + user.getPassword()) == false) {
						lista.add(linha);
					} else {
						String[] vetorUsuario = linha.split(";");
						System.out.print("Informe a nova senha: ");
						String password = leia.nextLine(); // alterar somente a senha do usuário
						linha = vetorUsuario[0] + ";" + vetorUsuario[1] + ";" + password;
						lista.add(linha);
						retorno = true;
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_USER_DB));
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i));
					bw.newLine();
				}
				bw.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possível abrir o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		} catch (IOException e) {
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

	private Integer idUsuario() { // garantir que o ID do usuário seja sequencial. Mesmo se algum usuário for
									// deletado, continuará a sequência.
		Integer qtd = 0;
		if (!existeArquivo()) {
			return qtd;
		}
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
			String linha = memoriaLeitura.readLine();
			ArrayList<String> lista = new ArrayList<>();
			while (linha != null) {
				String[] vetorCliente = linha.split(";");
				Integer idCliente = Integer.parseInt(vetorCliente[0]);
				qtd = idCliente;
				linha = memoriaLeitura.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return qtd;
	}

	public boolean validarLoginUsuario(Usuario user) { // confirmar se usuário e senha estão corretos.
		if (existeArquivo()) { // se o arquivo de texto existir.
			try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_USER_DB))) {
				String linha1 = memoriaLeitura.readLine();
				while (linha1 != null && linha1.contains(user.getUsername() + ";" + user.getPassword()) == false) {
					linha1 = memoriaLeitura.readLine();
				}
				if (linha1 == null) { // leu todo o arquivo e não encontrou usuario e senha informados.
					return false;
				}
				return true;
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
		return false;
	}

}
