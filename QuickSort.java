/*	Ejercicio1: Tiempo de ejecucion de QuickSort para varios archivos
*	Autores: 
*	- Gonzalo Joel Choque Dongo
*	- Carlos Fabian Arteaga Peña
*	- Shirley Chambi Chayña
*	- Nelson Huaman Apaza
*/

package Lab06;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

public class QuickSort {
	
	public static BufferedWriter bw = null;
	public static File gnu = null;
	public static BufferedWriter bwIter = null;
	public static File gnuIter = null;
	
	
	public static void main(String[] args) throws IOException{
		
		//Leer Archivos
		String [] archivos = leerArchivos();

		gnu = new File ("Resultado.txt");
    		gnu.createNewFile();
		
		//Calcula tiempos
    		if(archivos.length>0) {
    			bucleTiempos(archivos);
    		
    			//imprime resultado
        		bw.close();
    			Desktop.getDesktop().open(gnu);
    		}
    		else{
    			System.out.println("No hay archivos por ordenar"); 		
    		} 
	 	
	}
	
	public static String[] leerArchivos() {//retorna un array con el nombre de todos los archivos
		// TODO Auto-generated method stub
		File directorio = new File("archivosDesordenados"); 
		File archivos[]=directorio.listFiles();
		String[] arch = new String[archivos.length];
		String iter;
		int size;
		for(int i=0; i<archivos.length;i++) {
			if(archivos[i].isFile()) {	
				iter=archivos[i].getName();
				size=iter.length();
				arch[i]=iter.substring(0, size-4);
			}
		}
		return arch;
	}
	public static void bucleTiempos(String [] archivos) throws IOException {//inicia el programa
		TiempoQuickSort(archivos);
	}

	private static void TiempoQuickSort(String [] archivos) throws IOException {//calcula el tiempo en ordenar cada uno de los archivos
		long Tinicio, Tfinal;
		long tiempo;
		bw = new BufferedWriter(new FileWriter(gnu));

		String arch;
		for(int i=0; i < archivos.length; i++) {
			//crear arreglos en peor caso
			arch = archivos[i];
			
			int nums[]=leerNumeros(arch);

			Tinicio = System.nanoTime();
			quickSort(nums);
			Tfinal = System.nanoTime();
			
			ArchivoOrdenado(nums,arch);
			tiempo = Tfinal-Tinicio;

			bw.write("El archivo "+ arch +".txt"+" con "+nums.length+" elementos\t- demoro: "+"\t"+tiempo+" nanosegundos"+"\n");			
		}
	}
	private static void ArchivoOrdenado(int[] nums, String arch) throws IOException {//crea un archivo con el nombre del original + ordenado
		// TODO Auto-generated method stub
		gnuIter = new File ("archivosOrdenados/"+arch+"Ordenado.txt");
    	gnuIter.createNewFile();
    	bwIter = new BufferedWriter(new FileWriter(gnuIter));

    	for(int i=0;i<nums.length;i++) {
    		bwIter.write(nums[i]+"\n");
    	}
    	bwIter.close();
    	Desktop.getDesktop().open(gnuIter);
	}

	private static int[] leerNumeros(String arch) throws IOException {//crea un array con todos los numeros del archivo.txt linea por linea
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("archivosDesordenados/"+arch+".txt"));
		int count=0;
		String linea=br.readLine();
		while(linea != null) {
			linea = br.readLine();
			count++;
		}
		br.close();
		
		int arreglo[] = new int[count];
		BufferedReader br1 = new BufferedReader(new FileReader("archivosDesordenados/"+arch+".txt"));
		String leer = br1.readLine();
		int pos=0;
		while(leer != null) {
			arreglo[pos] = Integer.parseInt(leer);
			leer=br1.readLine();
			pos++;
		}
		br1.close();
		return arreglo;
	}

	private static void quickSort(int [] nums) {//retorna un array con los datos ordenados
		// TODO Auto-generated method stub
		int size=nums.length;
		quickSort(nums, 0, size-1);
	}
	private static void quickSort(int vec[], int inicio, int fin){
		int pivote=vec[inicio];	// primer elemento como pivote
		int i=inicio, j=fin;	// i realiza la búsqueda de izquierda a derecha
		int aux;				// j realica la búsqueda de derecha a izquierda
		
		while(i<j) {
			while(vec[i]<= pivote && i<j) i++;	// busca elemento mayor que pivote
			while(vec[j]>pivote) j--;			// busca elemento menos que pivote
			if(i<j) {							// si no se han crusado los intercambia
				aux=vec[i];
				vec[i]=vec[j];
				vec[j]=aux;
			}
		}
		vec[inicio]=vec[j];		// se coloca el pivote en su lugar
		vec[j]=pivote;			// menos a la ziquierda y mayores a la derecha
		
		if(inicio<j-1) quickSort(vec,inicio,j-1);	// ordenamos subarray izquierdo
		if(fin>j+1) quickSort(vec,j+1,fin);			// ordenamos subarray derecho
	}
}
