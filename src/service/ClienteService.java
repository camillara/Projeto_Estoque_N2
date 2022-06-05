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

import model.Cliente;

public class ClienteService {

	private final String DIR_CLIENTE_DB = "src/data_base/cliente.txt";

	File arquivo = new File(DIR_CLIENTE_DB);

	public Boolean escrever(Cliente cliente) {

		try {
			if (existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_CLIENTE_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				cliente.setId(codigoCliente() + 1);
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
		try {
			if (existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_CLIENTE_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					String[] linhaSplit = linha.split(";");
					if (cliente.getCpf().equals(linhaSplit[2])) {
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

	public ArrayList<Cliente> ler() {
		List<Cliente> cliente = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			String linha = br.readLine();
			while (linha != null) {
				String[] vetorClientes = linha.split(";");
				Integer idCliente = Integer.parseInt(vetorClientes[0]);
				String nomeCliente = vetorClientes[1];
				String cpfCliente = vetorClientes[2];
				String generoCliente = vetorClientes[3];
				String enderecoCliente = vetorClientes[4];
				String telefoneCliente = vetorClientes[5];
				Cliente listaCliente = new Cliente(idCliente, nomeCliente, cpfCliente, generoCliente, enderecoCliente,
						telefoneCliente);
				cliente.add(listaCliente);
				linha = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return (ArrayList<Cliente>) cliente;

	}

	public Boolean deletar(Cliente cliente) {
		try {
			if (ler(cliente)) {
				FileReader lerNoARquivo = new FileReader(DIR_CLIENTE_DB);
				BufferedReader br = new BufferedReader(lerNoARquivo);
				String linha = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(cliente.getNome() + ";" + cliente.getCpf()) == false) {
						lista.add(linha);
					}
					linha = br.readLine();
				}
				FileWriter apagar = new FileWriter(DIR_CLIENTE_DB, true);
				apagar.close();
				br.close();
				lerNoARquivo.close();
				FileWriter escrever = new FileWriter(DIR_CLIENTE_DB);
				BufferedWriter bw = new BufferedWriter(escrever);
				for (int i = 0; i < lista.size(); i++) {
					bw.write(lista.get(i));
					bw.newLine();
				}
				bw.close();
				escrever.close();
				return true;
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

	public Integer codigoCliente() {

		criaArquivo();
		Integer qtd = 0;
		try {
			FileReader lerNoARquivo = new FileReader(DIR_CLIENTE_DB);
			BufferedReader br = new BufferedReader(lerNoARquivo);
			String linha = br.readLine();
			ArrayList<String> lista = new ArrayList<>();
			while (linha != null) {
				String[] vetorCliente = linha.split(";");
				Integer idCliente = Integer.parseInt(vetorCliente[0]);
				qtd = idCliente;
				linha = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return qtd;
	}

}
