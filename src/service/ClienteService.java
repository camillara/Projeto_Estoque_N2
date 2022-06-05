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

import model.Cliente;

public class ClienteService {

	private final String DIR_CLIENTE_DB = "src/data_base/cliente.txt";
	private File arquivo;
	private BufferedWriter bw;

	public ClienteService() {
		arquivo = new File(DIR_CLIENTE_DB);
	}

	public Boolean escrever(Cliente cliente) {

		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			if (existeArquivo()) {
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
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			if (existeArquivo()) {
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
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			String linha = memoriaLeitura.readLine();
			while (linha != null) {
				Cliente listaCliente = new Cliente();
				String[] vetorClientes = linha.split(";");
				listaCliente.setId(Integer.parseInt(vetorClientes[0]));
				listaCliente.setNome(vetorClientes[1]);
				listaCliente.setCpf(vetorClientes[2]);
				listaCliente.setGenero(vetorClientes[3]);
				listaCliente.setEndereco(vetorClientes[4]);
				listaCliente.setTelefone(vetorClientes[5]);
				cliente.add(listaCliente);
				linha = memoriaLeitura.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return (ArrayList<Cliente>) cliente;

	}

	public Boolean deletar(Cliente cliente) {
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			if (ler(cliente)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(cliente.getNome() + ";" + cliente.getCpf()) == false) {
						lista.add(linha);
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_CLIENTE_DB));
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
		}

		catch (IOException e) {
			System.out.println("Não foi possível ler o arquivo.");
			System.out.println("O erro gerado é: " + e.getMessage());
		}
		return false;

	}

	public Boolean atualizar(Cliente cliente) {
		Boolean retorno = false;
		Scanner leia = new Scanner(System.in);
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
			if (ler(cliente)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(cliente.getNome() + ";" + cliente.getCpf()) == false) {
						lista.add(linha);
					} else {
						String[] vetorCliente = linha.split(";");
						System.out.print("Informe o nome correto: ");
						String novoNomeCliente = leia.nextLine().toUpperCase(); // alterar o nome do cliente
						System.out.print("Informe o novo endereço: ");
						String novoEnderecoCliente = leia.nextLine().toUpperCase(); // alterar o endereço do cliente
						System.out.print("Informe o novo telefone: ");
						String novoTelefoneCliente = leia.nextLine(); // alterar o telefone do cliente
						linha = vetorCliente[0] + ";" + novoNomeCliente + ";" + vetorCliente[2] + ";" + vetorCliente[3]
								+ ";" + novoEnderecoCliente + ";" + novoTelefoneCliente;
						lista.add(linha);
						retorno = true;
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_CLIENTE_DB));
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

	private Integer codigoCliente() {
		Integer qtd = 0;
		if (!existeArquivo()) {
			return qtd;
		}
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_CLIENTE_DB))) {
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

}
