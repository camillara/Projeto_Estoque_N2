package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteService {

	private final String DIR_CLIENTE_DB = "src/data_base/cliente.txt";

	File arquivo = new File(DIR_CLIENTE_DB);

	public Boolean escrever(Cliente cliente) {

		try {
			if (existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_CLIENTE_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				int contadorLinha = 1;
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					contadorLinha++;
				}
				cliente.setId(contadorLinha);
				String dados = cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getCpf() + ";"
						+ cliente.getGenero() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + "\n";
				FileWriter escreverArquivo = new FileWriter(arquivo, true);
				escreverArquivo.write(dados);
				escreverArquivo.close();
				return true;
			} else {
				criaArquivo();
				escrever(cliente); // recursão
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

	public Boolean ler(Cliente cliente) {

		return false;

	}

	public ArrayList<Cliente> ler() {
		ArrayList<Cliente> listaCliente = new ArrayList<>();
		return listaCliente;

	}

	public Boolean deletar(Cliente cliente) {
		if (existeArquivo()) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean atualizar(Cliente cliente) {
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
