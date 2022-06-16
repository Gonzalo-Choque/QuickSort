/*	Archivo: Crear archivo aleatorio
*	Autores: 
*	- Gonzalo Joel Choque Dongo
*	- Carlos Fabian Arteaga Peña
*	- Shirley Chambi Chayña
*	- Nelson Huaman Apaza 
*/

package Lab06;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CrearArchivoTxt {
	public static void main(String[] args) throws IOException{//crea un archivo con un numero random de elementos y desordenado aleatoriamente
		
		File numeroReal = new File("archivosDesordenados/prueba"+"2"+".txt");//ir cambiando el numero o nombre para generar más archivos
		numeroReal.createNewFile(); 
		BufferedWriter bw = new BufferedWriter(new FileWriter(numeroReal));
		int cant = (int) (Math.random()*100)+1;
		
		int [] array = numerosAleatorios(0,100,cant);	
		for (int i = 0; i < array.length; i++) {	
			bw.write(array[i]+"\n");
		}
		bw.close();
		Desktop.getDesktop().open(numeroReal);
	}
	public static int [] numerosAleatorios(int desde, int hasta, int tam){//llena el array aleatoriamente
        int[] numeros = new int[tam];                                                                             
        Random rnd = new Random();
        for (int i = 0; i < numeros.length; i++) {
             numeros[i] = rnd.nextInt(hasta - desde + 1) + desde;                                                 
        }
        return numeros;
	}
}
