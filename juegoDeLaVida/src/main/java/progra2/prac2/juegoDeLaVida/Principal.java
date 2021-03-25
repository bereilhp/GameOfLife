/*
Copyright 2021 Pedro Bereilh Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to inwriting, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES 
OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and 
limitations under the License.
*/
package progra2.prac2.juegoDeLaVida;

import java.util.Scanner;
import java.util.concurrent.TimeUnit; 
import java.lang.InterruptedException;
/**
 * Clase que se encarga de ejecutar el main 
 *  */     
public class Principal{
/**
 * Método main para crear el tablero y avisar si sucede alguna excepción, también es donde se llaman a los métodos 
 * para poder transitar y ejecutar las reglas del juego. Podemos cambiar el numero de veces que van a transitar los dos 
 * tienen 15 vueltas lo que significa que van a transitar 15 veces primero el fichero matriz y luego el fichero creado
 * por Montecarlo. Tiene complejidad espacial O(n^2) y temporal O(n^2).
 * @param args args
 */
public static void main(String[] args){
	Tablero tablero = new Tablero();
	//tablero.generarEstadoActualPorMontecarlo();
	/*Scanner sc = new Scanner(System.in);
	System.out.println(tablero);
	for(int i = 0; i < 5; i++){
		System.out.print("Fila: ");
		int f = sc.nextInt();
		System.out.print("Columna: ");
		int c = sc.nextInt();
		int vecinas = tablero.numVecinasVivas(f, c);
		System.out.println("Vecinas vivas: " + vecinas);
	}*/

	
	try {

		System.out.println("SIMULACIÓN CON TABLERO LEÍDO");

		
		tablero.leerEstadoActual(); 
		System.out.println(tablero); 

		for(int i = 0; i <= 15; i++) {

			TimeUnit.SECONDS.sleep(1); 
			tablero.transitarAlEstadoSiguiente(); 
			System.out.println(tablero);
		} 

	System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO");

	tablero.generarEstadoActualPorMontecarlo(); 
	System.out.println(tablero);

	for(int i = 0; i <= 15; i++){

		TimeUnit.SECONDS.sleep(1); tablero.transitarAlEstadoSiguiente(); System.out.println(tablero);
    
    }

}catch(InterruptedException e)
	{
	System.out.println(e); }
	}
	}

