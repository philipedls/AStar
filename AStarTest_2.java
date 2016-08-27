package ufpb.laser.desenvolvimento;

import java.util.Random;
import java.util.Scanner;

public class AStarTest_2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int linhaVI;
		int colunaVI;
		int linhaVF;
		int colunaVF;
		String resposta;
		
		int[] linhas = new int[]{0,1,2,3,4,5};
		int tamanhoVetor = 36;
		int distanciaP = 5;
		Vertice[] vertices = new Vertice[tamanhoVetor];
		
		boolean sair = false;	
		do{
			
			exibirMatriz(vertices);
			
			System.out.println("Vétice de início"+"\n"+"Digite o linha:");
			linhaVI = Integer.parseInt(scan.nextLine());
			
			System.out.println("Digite o coluna:");
			colunaVI = Integer.parseInt(scan.nextLine());
			
			System.out.println("Vétice alvo"+"\n"+"Digite a linha:");
			linhaVF = Integer.parseInt(scan.nextLine());
			
			System.out.println("Digite a coluna:");
			colunaVF = Integer.parseInt(scan.nextLine());
			
			vertices[pesquisaIndice(linhaVI, colunaVI)] = new Vertice("A", distanciaP, pesquisaIndice(linhaVI, colunaVI));
			vertices[pesquisaIndice(linhaVF, colunaVF)] = new Vertice("B", 0, pesquisaIndice(linhaVF, colunaVF));
			
			for(int i=0; i < vertices.length; i++){
				if(i != pesquisaIndice(linhaVI, colunaVI) && i != pesquisaIndice(linhaVF, colunaVF)){ 
					vertices[i] = new Vertice("*", distanciaP, getDistanciaAleatoria());
				}
			}
			
			exibirMatriz(vertices);
			
			//realiza mapeamento dos vertices adjacentes de cada vertice
			for(int linha=0; linha < linhas.length; linha++){
				for(int coluna=0; coluna < vertices.length / linhas.length; coluna++){
					mapeamentoDeADJ(vertices, linha, coluna);
				}
			}
			
			gerenciadorDeCustos(vertices);
			
			
			System.out.println("Continuar: S/N"); 
			
			if(scan.nextLine().equalsIgnoreCase("S")){
	
				Vertice vertice = vertices[pesquisaIndice(linhaVI, colunaVI)];
				while(vertice.getDistaciaLimite() != 0) {
					vertice = vertice.melhorAdj();
					System.out.println(" -> " +vertice.getEtiqueta());
					
				}
				
				exibirMatriz(vertices);
			}
			
		}while(!sair);

	}
	
	public static void validaADJ(Vertice vertice, Vertice vAdjacente){
		if(vAdjacente.isFlag() == false){
			vertice.listaDeAdjacentes.add(vAdjacente);
		}
	}
	
	public static void exibirMatriz(Vertice[] v){
		
		int mutiplo = 6;
		
		for(int ind=0; ind < v.length; ind++){
			
			if(ind > 4 && (ind +1 )% mutiplo == 0){
				if(v[ind] != null ){
					System.out.print(" | " + v[ind].getEtiqueta()+" |");
					System.out.println();
				
				}else{
					System.out.print(" |  |");
					System.out.println();
				}
			
			}else{
				if(v[ind] != null){
					System.out.print(" | " + v[ind].getEtiqueta());
				
				}else{
					System.out.print(" | ");
				}
			}
		}
	}

	public static int pesquisaIndice(int linha, int coluna) {

		int[] idLinha_1 = new int[] { 0, 1, 2, 3, 4, 5 };
		int[] idLinha_2 = new int[] { 6, 7, 8, 9, 10, 11 };
		int[] idLinha_3 = new int[] { 12, 13, 14, 15, 16, 17 };
		int[] idLinha_4 = new int[] { 18, 19, 20, 21, 22, 23 };
		int[] idLinha_5 = new int[] { 24, 25, 26, 27, 28, 29 };
		int[] idLinha_6 = new int[] { 30, 31, 32, 33, 34, 35 };
		int ind;

		switch (linha) {
		case 1:
			return idLinha_1[coluna-1];
			
		case 2:
			return idLinha_2[coluna-1];
			
		case 3:
			return idLinha_3[coluna-1];
			
		case 4:
			return idLinha_4[coluna-1];
			
		case 5:
			return idLinha_5[coluna-1];
			
		case 6:
			return idLinha_6[coluna-1];
			
		default:
			return ind=0;
		}
		
	}
	
	public static void mapeamentoDeADJ(Vertice[] v, int linha, int coluna){
		for(int i=0; i < v.length; i++){
			if(coluna >= 2 && coluna <= 5 && linha == 1){
				if(i > 0 && i < 6 && v[i].listaDeAdjacentes.size() < 5){
					validaADJ(v[i], v[i + 1]);
					validaADJ(v[i], v[i - 1]);
					validaADJ(v[i], v[i + 6]);
					validaADJ(v[i], v[i + 5]);
					validaADJ(v[i], v[i + 7]);
				}
			
			}else if(coluna >= 2 && coluna <= 5 && linha >=2 && linha <= 5){
				if(i >= 7 && i <= 28 && i!=12 && i!=18 && i!=24 && v[i].listaDeAdjacentes.size() < 8){
					validaADJ(v[i], v[i + 1]);
					validaADJ(v[i], v[i - 1]);
					validaADJ(v[i], v[i + 6]);
					validaADJ(v[i], v[i - 6]);
					validaADJ(v[i], v[i + 5]);
					validaADJ(v[i], v[i + 7]);
					validaADJ(v[i], v[i - 5]);
					validaADJ(v[i], v[i - 7]);
					
				}
			
			}else if(coluna >= 2 && coluna <= 5 && linha == 6){
				if(i >= 31 && i <= 34 && v[i].listaDeAdjacentes.size() < 5){
					validaADJ(v[i], v[i + 1]);
					validaADJ(v[i], v[i - 1]);
					validaADJ(v[i], v[i - 6]);
					validaADJ(v[i], v[i - 5]);
					validaADJ(v[i], v[i - 7]);
					
				}
			
			}else if(coluna == 1 && linha >= 2 && linha <= 5){
				if(i == 6 || i == 12 || i == 18 || i == 24){
					if(v[i].listaDeAdjacentes.size() < 5){
						validaADJ(v[i], v[i + 1]);
						validaADJ(v[i], v[i + 6]);
						validaADJ(v[i], v[i - 6]);
						validaADJ(v[i], v[i - 5]);
						validaADJ(v[i], v[i + 7]);
						
					}
				}
			}else if(coluna == 6 && linha >= 2 && linha <= 5){
				if(i == 11 || i == 17 || i == 23 || i == 29){
					if (v[i].listaDeAdjacentes.size() < 5) {
						validaADJ(v[i], v[i + 6]);
						validaADJ(v[i], v[i - 6]);
						validaADJ(v[i], v[i - 1]);
						validaADJ(v[i], v[i + 5]);
						validaADJ(v[i], v[i - 7]);
						
					}
				}
			
			}else{
				switch(i){
				case 0:
					if (v[i].listaDeAdjacentes.size() < 3) {
						validaADJ(v[i], v[1]);
						validaADJ(v[i], v[6]);
						validaADJ(v[i], v[7]);
					
					}
					break;
					
				case 5:
					if (v[i].listaDeAdjacentes.size() < 3) {
						validaADJ(v[i], v[4]);
						validaADJ(v[i], v[10]);
						validaADJ(v[i], v[11]);
						
					}
					break;
					
				case 30:
					if (v[i].listaDeAdjacentes.size() < 3) {
						validaADJ(v[i], v[24]);
						validaADJ(v[i], v[25]);
						validaADJ(v[i], v[31]);
					
					}
					break;
					
				case 35:
					if (v[i].listaDeAdjacentes.size() < 3) {
						validaADJ(v[i], v[34]);
						validaADJ(v[i], v[28]);
						validaADJ(v[i], v[29]);
						
					}
					break;
				}
				
			}
		}
	}
	
	public static void geradorDeCustos(Vertice v){
		Random gerador = new Random();
		int limite = 2;
		for(int i=0; i < v.listaDeAdjacentes.size(); i++){
			v.custos.add(gerador.nextInt(limite));
			
		}
	}
	
	public static void gerenciadorDeCustos(Vertice[] v){
		for(int index=0; index < v.length; index++){
			geradorDeCustos(v[index]);
		}
	}
	
	public static int getDistanciaAleatoria(){
		Random gerador = new Random();
		int limite = 6;
		return gerador.nextInt(limite);
	}
}
