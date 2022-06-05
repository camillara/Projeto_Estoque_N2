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

import model.Fornecedor;

public class FornecedorService {

	private final String DIR_FORNECEDOR_DB = "src/data_base/fornecedor.txt";

	File arquivo = new File(DIR_FORNECEDOR_DB);


	public Boolean escrever(Fornecedor fornecedor) {

		try {
			if (existeArquivo()) {

				FileReader arquivoLeitura = new FileReader(DIR_FORNECEDOR_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
				fornecedor.setId(codigoFornecedor()+1);
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
		try {
			if (existeArquivo()) {
				FileReader arquivoLeitura = new FileReader(DIR_FORNECEDOR_DB);
				BufferedReader memoriaLeitura = new BufferedReader(arquivoLeitura);
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
		try (BufferedReader br = new BufferedReader(new FileReader(DIR_FORNECEDOR_DB))) {
			String linha = br.readLine();
			while (linha != null) {
				String[] vetorFornecedor = linha.split(";");
				Integer idFornecedor = Integer.parseInt(vetorFornecedor[0]);
				Integer tipoPessoaFornecedor = Integer.parseInt(vetorFornecedor[1]);
				String cnpjFornecedor = vetorFornecedor[2];
				String razaoSocialFornecedor = vetorFornecedor[3];
				String fantasiaFornecedor = vetorFornecedor[4];
				Fornecedor fornecedores = new Fornecedor(idFornecedor, tipoPessoaFornecedor, cnpjFornecedor,
						razaoSocialFornecedor, fantasiaFornecedor);
				listaFornecedor.add(fornecedores);
				linha = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return (ArrayList<Fornecedor>) listaFornecedor;

	}

	public Boolean deletar(Fornecedor fornecedor) {
		try {
			if(ler(fornecedor)) {
				FileReader lerNoARquivo = new FileReader(DIR_FORNECEDOR_DB);
				BufferedReader br = new BufferedReader(lerNoARquivo);
				String linha = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				while (linha != null) {
					if (linha.contains(fornecedor.getCnpj() + ";" + fornecedor.getRazaoSocial()) == false) {
						lista.add(linha);
				}
				linha = br.readLine();			}
				FileWriter apagar = new FileWriter(DIR_FORNECEDOR_DB, true);
				apagar.close();
				br.close();
				lerNoARquivo.close();
				FileWriter escrever = new FileWriter(DIR_FORNECEDOR_DB);
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

	public Boolean atualizar(Fornecedor fornecedor) {
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
	
	public Integer codigoFornecedor() {
		
		criaArquivo();
		Integer qtd=0;
		try {						
			FileReader lerNoARquivo = new FileReader(DIR_FORNECEDOR_DB);
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
