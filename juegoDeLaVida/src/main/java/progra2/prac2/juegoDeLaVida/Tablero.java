/*
Copyright 2021 Pedro Bereilh Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to inwriting, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and 
imitations under the License.
*/
package progra2.prac2.juegoDeLaVida;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/** 
 * Clase que se encarga de leer el fichero matriz.txt, generar matriz por Montecarlo con 50 % de posibilidad de 1 y 0, calcular 
 * el numero de vecinas que contiene un elemento sin contar a ese elemento, transitar al estado siguiente si se cumplen 
 * las reglas o condiciones del juego de la vida explicadas en el enunciado y por último generar el tablero con una 
 * cabecera e imprimirlo. 
*/
public class Tablero{

	private static int dimension = 30;
	private int [][] estadoActual;
	private int [][] estadoSiguiente = new int [dimension][dimension];

/** 
 * Método leerEstadoActual, le pasamos un fichero llamado matriz.txt que tiene que estar ubicado en el mismo lugar del pom 
 * para que Maven lo encuentre y no tengamos que poner un path enorme como esta comentado si dejamos el fichero en src. 
 * Se encarga de leer este fichero y hacer posible que se genere las transiciones. Tiene Complejidad espacial que esta 
 * relacionada con la memoria de O(n^2) ya que tiene que leer linea por linea el fichero y temporal que es el numero de 
 * instrucciones de  O(n^2) ya que depende de la dimensión que le pasemos si es una matriz de 100 x 100 esto cambia.
 */
	public void leerEstadoActual(){

		//File f = new File("/Users/pedrobereilh/Desktop/juegodelavidaprac2/juegoDeLaVida/src/main/java/progra2/prac2/juegoDeLaVida/matriz.txt");
		File f = new File("matriz.txt");
		estadoActual = new int [dimension][dimension];

		try{

			Scanner sc = new Scanner(f);
			int lugar = 0;
			//System.out.println("Escaneando fichero");
			while(sc.hasNextLine()){

				String line = sc.nextLine();
				//System.out.println("Line: " + line);
				for(int i = 0; i < dimension; i++){
					estadoActual[lugar][i] = line.charAt(i) - '0';
				}
				lugar++;
			}

		/**
		 *  */			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		
	}

	/**
	 * Generar Estado actual por Montecarlo es un método muy simple que añade 1 o 0 con probabilidad de 50% a una matriz
	 * para tener otra forma de generar la matriz y que no solo sea pasandole un fichero matriz.txt. Tiene complejidad espacial
	 * O(n^2) ya que tiene que hacer dos bucles para poder hacer todas las filas y columnas y ir uno por uno en una posción exacta
	 * y agregar o un 0 o un 1 y para temporal lo mismo O(n^2) ya que las instrucciones dependen de la dimensión que le pasemos 
	 * que es para columnas y filas entonces tienen que realizar estas instrucciones al cuadrado ya que tiene que recorrer
	 * la matriz entera filas y columnas. Bucle for anidado.
	 */

	public void generarEstadoActualPorMontecarlo(){
		
		estadoActual = new int [dimension] [dimension];

		for (int i = 0;i < estadoActual.length;i++) {

			for (int j = 0; j < estadoActual[i].length; j++) {
				
				//System.out.print(estadoActual[i][j])

				if (Math.random() < 0.5) {

					estadoActual[i][j] = 0;
					
				}else{
					estadoActual[i][j] = 1;
				}

			}
			
		}
	}
/**
 * Este método calcula el numero de vecinas vivas que tiene un elemento en una posición exacta teniendo en cuenta 
 * su fila y columna y revisa su alrededor para saber si es un 1 o 0. No se cuenta a ella misma. Complejidad espacial 
 * O(1) ya que siempre son el mismo numero de vecinas dependiendo si es el borde o no pero casi siempre son las mismas
 * y en temporal es lo mismo O(1) ya que el numero de instrucciones que ejecuta siempre son las mismas dependiendo 
 * del número de sus vecinas que es un valor constante. 
 * @param f filas 
 * @param c columnas 
 * @return numero de vecinas 
 */
	public int numVecinasVivas(int f, int c){
		int vecinas = 0;

		for(int i = f - 1; i <= f + 1; i++){
			for(int j = c - 1; j <= c + 1; j++){
				if(i < dimension && i >= 0 && j < dimension && j >= 0){
				//System.out.print("Chequenado Celda: " + i +  " " + j + "...");
				if ((i !=f || j !=c) && estadoActual[i][j]==1){
					vecinas++;
					//System.out.println("Viva ");
				}
			}
		}
	}
		return vecinas; 
	}
/**
 * Este método se encarga de poner las reglas del juego que lo ejecutan tanto el fichero matriz como la matriz generada
 * por Montecarlo que usa el método anterior de vecinas vivas para poder transitar y crear el juego ya sea por el número 
 * de vecinas vivas que tiene y saber si pasa a viva o a muerta. Tiene complejidad espacial O(n^2) ya que son bucles anidados 
 * y tiene que revisar tanto las filas como columnas y poder crear la transición de viva o de muerta que se va haciendo 
 * dependiendo del número de iteraciones que le dimos en el main que son 15 pero ese valor puede cambiar. Luego la temporal
 * es la misma O(n^2) ya que el número de instrucciones depende de la dimensión que le pasemos y tiene que ejecutar para 
 * cada elemento y revisar sus filas y sus columnas una y otra vez para crear las transiciones. 
 */
	public void transitarAlEstadoSiguiente(){

		estadoSiguiente = new int [dimension] [dimension];

		for (int i = 0;i < estadoActual.length;i++) {

			for (int j = 0; j < estadoActual[i].length; j++) {

				int vivas = numVecinasVivas(i,j);

				if (estadoActual[i][j] == 1){
				
					if(vivas == 2 || vivas == 3){
						estadoSiguiente[i][j] = 1;

					}else{ 
						estadoSiguiente[i][j] = 0;
					}
				}

				if(estadoActual[i][j] == 0){

					if(vivas == 3){
						
						estadoSiguiente[i][j] = 1;

					} else{
						estadoSiguiente[i][j] = 0;
					}
				}

			}

		}
		estadoActual = estadoSiguiente;
	
	}
/**
 * Este método se encarga de generar el tablero con una cabecera y mostrar la matriz que como vemos es usando los mismos 
 * bucles for de siempre que recorren toda la matriz filas y columnas. Tiene complejidad espacial O(n^2) ya que son bucles
 * for anidados y depende del tamaño y lo mismo para temporal O(n^2) ya que las instrucciones que se ejecutan depende del 
 * tamaño. 
 */
	@Override
    public String toString(){
    	String str = "\n********** TABLERO *********** \n";

		for (int i = 0;i < estadoActual.length;i++) {

			for (int j = 0; j < estadoActual[i].length; j++) {

				str = str + estadoActual[i][j] + "";

			}

		str = str + "\n";
			
		}
	
		return str; 
	}
}