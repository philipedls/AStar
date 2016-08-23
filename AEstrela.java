package ufpb.laser.desenvolvimento;

public class AEstrela {

	public static void main(String[] args) {

		Vertice v1 = new Vertice("A", 10, 4);
		Vertice v2 = new Vertice("B", 0, 15);
		Vertice[] vertices = new Vertice[16];

		vertices[4] = v1;
		vertices[15] = v2;

		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] == null) {
				if (i % 2 == 0) {

					vertices[i] = new Vertice("*", 7, i);

				} else if (i % 3 == 0) {
					vertices[i] = new Vertice("*", 10, i);

				} else {
					vertices[i] = new Vertice("*", 5, i);
				}
			}
		}

		mapeamentodeADJ(vertices);

		for (int i = 0; i < vertices.length; i++) {

			for (int ind = 0; ind < vertices[i].listaDeAdjacentes.size(); ind++) {
				if (ind % 2 == 0) {
					vertices[i].listaDeAdjacentes.get(ind).custos.add(1);

				} else {
					vertices[i].listaDeAdjacentes.get(ind).custos.add(2);
				}
			}
		}

		Vertice v = v1;
		while (v.getDistaciaLimite() != 0) {
			v = v.melhorAdj();
			if(v.getIndice() != v2.getIndice()){
				vertices[v.getIndice()].setEtiqueta("X");
			}
			
		}
		
		exibirMatriz(vertices);

	}

	public static void gerarCustos(int num, Vertice v) {
		v.custos.add(num);
	}

	public static void validaADJ(Vertice vertice, Vertice vAdjacente) {
		if (vAdjacente.isFlag() == false) {
			vertice.listaDeAdjacentes.add(vAdjacente);
		}
	}

	public static void exibirMatriz(Vertice[] v) {

		int mutiplo = 4;

		for (int ind = 0; ind < v.length; ind++) {

			if (ind > 2 && (ind + 1) % mutiplo == 0) {
				if (v[ind] != null) {
					System.out.print(" | " + v[ind].getEtiqueta() + " |");
					System.out.println();

				} else {
					System.out.print(" |  |");
					System.out.println();
				}

			} else {
				if (v[ind] != null) {
					System.out.print(" | " + v[ind].getEtiqueta());

				} else {
					System.out.print(" | ");
				}
			}
		}
	}

	public static void mapeamentodeADJ(Vertice[] v) {
		for (int ind = 0; ind < v.length; ind++) {
			if (ind < 4 && ind != 0 && ind != 3) {
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind + 3]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind + 5]);

			} else if (ind > 4 && ind < 11 && ind != 7 && ind != 8) {
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind + 5]);
				validaADJ(v[ind], v[ind + 3]);
				validaADJ(v[ind], v[ind - 5]);
				validaADJ(v[ind], v[ind - 3]);

			} else if (ind == 13 || ind == 14) {
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind - 3]);
				validaADJ(v[ind], v[ind - 5]);

			} else if (ind == 4 || ind == 8) {
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind - 3]);
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind + 5]);

			} else if (ind == 7 || ind == 11) {
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind - 5]);
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind + 3]);

			} else if (ind == 0) {
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind + 5]);

			} else if (ind == 3) {
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind + 4]);
				validaADJ(v[ind], v[ind + 3]);

			} else if (ind == 12) {
				validaADJ(v[ind], v[ind + 1]);
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind - 3]);

			} else if (ind == 15) {
				validaADJ(v[ind], v[ind - 1]);
				validaADJ(v[ind], v[ind - 4]);
				validaADJ(v[ind], v[ind - 5]);
			}
		}
	}
}
