package main;

import static spark.Spark.get;
import static spark.Spark.port;

import spark.Spark;

/**
 * Clase que realiza el calculo de n y n-1 de la serie de fibonacci.
 * 
 * @author sonia.calderon 
 * 2018-01
 *
 */
public class Fibonacci {
	/** Almacena los dos resultados de la serie. */
	private Long[] resultadoFibo;
	/** Almacena el numero de consulta en int. */
	private int numeroConsulta;

	public Fibonacci() {
		this.resultadoFibo = new Long[2];
		this.numeroConsulta = -1;
		start();
	}

	/**
	 * Inicia servicio y ejecuta acciones.
	 */
	public void start() {
		final int puertoTest = 4568;
		port(puertoTest);
		get("/Serie/:NumeroConsulta", (request, response) -> {

			String mensajeRespuesta = valida(request.params(":NumeroConsulta"));
			calcularSerie(numeroConsulta);
			if ("".equals(mensajeRespuesta)) {
				return "N = " + this.resultadoFibo[0] + " /////// (N - 1) = " + this.resultadoFibo[1];
			} else {
				return mensajeRespuesta;
			}
		});
		// Se espera hasta que inicie el servicio.
		Spark.awaitInitialization();
	}

	/**
	 * Metodo que valida el parametro entregado.
	 * 
	 * @param n
	 *            Numero de consulta
	 * @return mensaje de error
	 */
	public String valida(String n) {
		try {
			this.numeroConsulta = Integer.parseInt(n);
		} catch (Exception nfe) {
			return "Debes ingresar un valor númerico.";
		}
		if (this.numeroConsulta < 0) {
			return "Debes ingresar un valor mayor o igual a 0";
		}
		return "";
	}

	/**
	 * Realiza calculo y asigna resultado.
	 * 
	 * @param numeroConsultaIn
	 *            numero de consulta
	 */
	private void calcularSerie(int numeroConsultaIn) {
		this.resultadoFibo[0] = fibonacci(numeroConsultaIn);
		this.resultadoFibo[1] = fibonacci(numeroConsultaIn - 1);
	}

	/**
	 * Detiene servicio.
	 */
	public void stop() {
		Spark.stop();
	}

	/**
	 * Realiza calculo del numero de la serie de fibonacci recursivo.
	 * 
	 * @param n
	 *            Numero de consulta
	 * @return Numero de la serie de fibonacci solicitado.
	 */
	private long fibonacci(int n) {
		if (n > 1) {
			return fibonacci(n - 1) + fibonacci(n - 2);
		} else if (n == 1 || n == 0) {
			return n;
		}
		return -1;
	}
}
