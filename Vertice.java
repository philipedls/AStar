package ufpb.laser.desenvolvimento;

import java.util.LinkedList;
import java.util.List;

public class Vertice {
	
	private String etiqueta;
	private int distaciaLimite;
	List<Vertice> listaDeAdjacentes;
	List<Integer> custos;
	private int indice;
	private boolean flag;
	
	public Vertice(){
	this.listaDeAdjacentes = new LinkedList<Vertice>();
	this.custos = new LinkedList<Integer>();
	
	}
	
	public Vertice(String etiqueta, int distanciaLimite, int indice){
		this.etiqueta = etiqueta;
		this.distaciaLimite = distanciaLimite;
		this.indice = indice;
		this.listaDeAdjacentes = new LinkedList<Vertice>();
		this.custos = new LinkedList<Integer>();
		this.setFlag(false);
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public int getDistaciaLimite() {
		return distaciaLimite;
	}

	public void setDistaciaLimite(int distaciaLimite) {
		this.distaciaLimite = distaciaLimite;
	}
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	public Vertice melhorAdj(){
		
		int distEconomica = caminhoMaisCurto(this.listaDeAdjacentes, this.custos);
		listaDeAdjacentes.get(distEconomica).setFlag(true);
		return listaDeAdjacentes.get(distEconomica);
		
	}
	
	//Função que calcula a distancia de forma heuristica do A*( F = G + H)
	
	public int caminhoMaisCurto(List<Vertice> listaDeAdjacentes, List<Integer> custos){
		
		int distCaminho = 0;
		for(int i = 1; i < listaDeAdjacentes.size(); i++){
			if(listaDeAdjacentes.get(i).distaciaLimite + custos.get(i) > listaDeAdjacentes.get(i-1).distaciaLimite + custos.get(i-1)){
				distCaminho = i-1;
			}else{
				distCaminho = i;
			}
		}
		return distCaminho;
	}

	
}
