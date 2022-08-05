package candidatura;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
	public static void main(String[] args) {
		 System.out.println("PROCESSO SELETIVO");
		 
		 String [] candidatos = { "FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO"};
		 
		 for(String candidato: candidatos) {
			 entrandoEmContato(candidato);
		 }
		 
//		 analisarCandidato( 1900.0 );
//		 analisarCandidato( 2200.0 );
//		 analisarCandidato( 2000.0 );
//		 selecaoCandidatos();
//		 imprimeSelecionados();
		 
	}
	//metodo entrando em contato com o candidato
	public static void entrandoEmContato(String candidato) {
		int tentativasRealizadas = 1;
		boolean continuarTentando = true;
		boolean atendeu = false;
		
		do {
			atendeu = atender();
			continuarTentando = !atendeu;
			if(continuarTentando)
				tentativasRealizadas++;
			else
				System.out.println("CONTATO REALIZADO COM SUCESSO");
		}while(continuarTentando && tentativasRealizadas < 3);
		
		if(atendeu)
			System.out.println("CONSEGUIMOS CONTATO COM " + candidato + "NA" + tentativasRealizadas);
		else
			System.out.println("NÃO CONSEGUIMOS CONTATO COM " + candidato + ", NÚMERO MAXIMO DE TENTATIVAS " + tentativasRealizadas + " REALIZADAS");
		
	}
	
	//método auxiliar 
	public static boolean atender() {
		return new Random().nextInt(3)==1;
	}
	public static void imprimeSelecionados() {
		String [] candidatos = { "FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO"};
		
		System.out.println(" Imprimindo a lista de candidatos informando o índice do elemento");
		
		for(int indice=0; indice < candidatos.length; indice++) {
			System.out.println("O candidato de número " + (indice + 1) + " é o " +  candidatos[indice]);
		}
		
		System.out.println(" Forma abreviada de iteração for each");
		
		for(String candidato: candidatos) {
			System.out.println("O candidato selecionado foi " + candidato);
		}
		
	}
	public static void selecaoCandidatos() {
		
		// Array com a lista de candidatos.
		String [] candidatos = { "FELIPE", "MARCIA","JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"};
		
		int candidatosSelecionados = 0;
		int candidatosAtual = 0;
		double salarioBase = 2000.0;
		
		while(candidatosSelecionados < 5 && candidatosAtual < candidatos.length) {
			String candidato = candidatos[candidatosAtual];
			double salarioPretendido = valorPretendido();
			
			System.out.println("O Candidato " + candidato 
					+ " solicitou este valor de salário " + salarioPretendido);
			if(salarioBase >= salarioPretendido) {
				System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
				candidatosSelecionados++;
			}
			candidatosAtual++;
		}
		
	}	
	
	public static void analisarCandidato(double salarioPretendido ) {
		
		double salarioBase = 2000.0;
		
		if(salarioBase > salarioPretendido) {
			System.out.println("LIGAR PARA CANDIDATO");
		}else if(salarioBase == salarioPretendido){
			System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
		}
		else {
			System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
		}
	}
	public static double valorPretendido() {
		return ThreadLocalRandom.current().nextDouble(1800,2200);
	}
	
}
	
