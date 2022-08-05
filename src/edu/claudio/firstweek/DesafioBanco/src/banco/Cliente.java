package banco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import lombok.Getter;
//import lombok.Setter;
//
//
//@Getter
//@Setter
public class Cliente {
	
	private String nome;
	private String cpf;
	private String endereco;
	private String dataDeNascimento;
	private LocalDate dNascimentoLocalDate;
	
	// Posterior contas com datas, tipo quantos dias do aniversário, quantos anos,..., etc.
	//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//		dNascimentoLocalDate =  LocalDate.parse(getDataDeNascimento(), formatter);
    // System.out.println("Data de Nascimento: " + formatter.format(dNascimentoLocalDate));

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
		
	}
	
}

