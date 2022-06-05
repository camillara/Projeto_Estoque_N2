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

import model.Fornecedor;

public class FornecedorService {

	private final String DIR_FORNECEDOR_DB = "src/data_base/fornecedor.txt";
	private File arquivo;
	private BufferedWriter bw;
	
	public FornecedorService() {
		arquivo = new File(DIR_FORNECEDOR_DB);
	}
	

	public Boolean escrever(Fornecedor fornecedor) {

		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			if (existeArquivo()) {
				fornecedor.setId(codigoFornecedor() + 1);
				String dados = fornecedor.getId() + ";" + fornecedor.getTipoPessoa() + ";" + fornecedor.getCnpj() + ";"
						+ fornecedor.getRazaoSocial() + ";" + fornecedor.getFantasia() + "\n";
				FileWriter escreverArquivo = new FileWriter(arquivo, true);
				escreverArquivo.write(dados);
				escreverArquivo.close();
				return true;
			} else {
				criaArquivo();
				escrever(fornecedor); // recursão
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

	public Boolean ler(Fornecedor fornecedor) {
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			if (existeArquivo()) {
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					String[] linhaSplit = linha.split(";");
					if (fornecedor.getCnpj().equals(linhaSplit[2])) {
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

	public ArrayList<Fornecedor> ler() {
		List<Fornecedor> listaFornecedor = new ArrayList<>();
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			String linha = memoriaLeitura.readLine();
			while (linha != null) {
				Fornecedor fornecedores = new Fornecedor();
				String[] vetorFornecedor = linha.split(";");
				fornecedores.setId(Integer.parseInt(vetorFornecedor[0]));
				fornecedores.setTipoPessoa(Integer.parseInt(vetorFornecedor[1]));
				fornecedores.setCnpj(vetorFornecedor[2]);
				fornecedores.setRazaoSocial(vetorFornecedor[3]);
				fornecedores.setFantasia(vetorFornecedor[4]);
				listaFornecedor.add(fornecedores);
				linha = memoriaLeitura.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return (ArrayList<Fornecedor>) listaFornecedor;
	}

	public Boolean deletar(Fornecedor fornecedor) {
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			if (ler(fornecedor)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(fornecedor.getCnpj() + ";" + fornecedor.getRazaoSocial()) == false) {
						lista.add(linha);
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_FORNECEDOR_DB));
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

	public Boolean atualizar(Fornecedor fornecedor) {
		Boolean retorno = false;
		Scanner leia = new Scanner(System.in);
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			if (ler(fornecedor)) {
				String linha = memoriaLeitura.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(fornecedor.getCnpj() + ";" + fornecedor.getRazaoSocial()) == false) {
						lista.add(linha);
					} else {
						String[] vetorFornecedor = linha.split(";");
						System.out.print("Informe a nova razão social: ");
						String novaRazaoSocial = leia.nextLine().toUpperCase(); // alterar razão social
						System.out.print("Informe novo nome fantasia: ");
						String novaFantasia = leia.nextLine().toUpperCase(); // alterar nome fantasia do fornecedor
						linha = vetorFornecedor[0] + ";" + vetorFornecedor[1] + ";" + vetorFornecedor[2] + ";" + novaRazaoSocial + ";"
								+ novaFantasia;
						lista.add(linha);
						retorno = true;
					}
					linha = memoriaLeitura.readLine();
				}
				bw = new BufferedWriter(new FileWriter(DIR_FORNECEDOR_DB));
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

	private Integer codigoFornecedor() {
		Integer qtd = 0;
		if (!existeArquivo()) {
			return qtd;
		}
		try (BufferedReader memoriaLeitura = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
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
