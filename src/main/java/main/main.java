package main;

/**
 * Inicia y levanta servicio.
 * 
 * @author sonia.calderon 2018-01
 *
 */
public class main {
	/**
	 * Metodo de ejecucion main del servicio.
	 * 
	 * @param args
	 *            Argumentos.
	 */
	public static void main(String[] args) {
		try {
			new Fibonacci();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
