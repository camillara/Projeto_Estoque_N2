package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Fornecedor;


public class FornecedorService {
	
	private final String DIR_FORNECEDOR_DB = "src/data_base/fornecedor.txt";

	File arquivo = new File(DIR_FORNECEDOR_DB);
	
public Boolean escrever(Fornecedor fornecedor) {
		
	try {
		if (existeArquivo()) {

			FileReader arquivoLeitura = new FileReader(DIR_FORNECEDOR_DB);
			BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
			int contadorLinha = 1;
			String linha = null;
			while ((linha = memoriaLeitura.readLine()) != null) {
				contadorLinha++;
			}
			fornecedor.setId(contadorLinha);
			String dados = fornecedor.getId() + ";" + fornecedor.getCnpj() + ";" + fornecedor.getRazaoSocial()+ ";" +  fornecedor.getFantasia() + "\n";
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
		try {
			if(existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_FORNECEDOR_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				String linha = null;
				while ((linha = memoriaLeitura.readLine()) != null) {
					String[] linhaSplit = linha.split(";");
					if(fornecedor.getCnpj().equals(linhaSplit[1])) {
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
		ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();
		return listaFornecedor;
		
		
	}
	
	public Boolean deletar(Fornecedor fornecedor) {
		if(existeArquivo()) {
			return true;			
		}
		else {
			return false;			
		}		

	}
	
	public Boolean atualizar(Fornecedor fornecedor) {
		if(existeArquivo()) {
			return true;
		}
		else {
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
