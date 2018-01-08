package main.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import main.Fibonacci;

public class FibonacciTest {

	@Test
	public void respuestaCorrectaTest() throws UnirestException {
		Fibonacci fibo = new Fibonacci();
		final HttpResponse<String> jsonResponse = Unirest.get("http://localhost:4568/Serie/1").asString();
		final String body = jsonResponse.getBody();
		assertEquals("N = 1 /////// (N - 1) = 0", body.toString());
		fibo.stop();
	}

	@Test
	public void parametroNegativoTest() throws UnirestException {
		Fibonacci fibo = new Fibonacci();
		final HttpResponse<String> jsonResponse = Unirest.get("http://localhost:4568/Serie/-1").asString();
		final String body = jsonResponse.getBody();
		assertEquals("Debes ingresar un valor mayor o igual a 0", body.toString());
		fibo.stop();
	}

	@Test
	public void parametroNoNumericoTest() throws UnirestException {
		Fibonacci fibo = new Fibonacci();
		final HttpResponse<String> jsonResponse = Unirest.get("http://localhost:4568/Serie/4abc").asString();
		final String body = jsonResponse.getBody();
		assertEquals("Debes ingresar un valor númerico.", body.toString());
		fibo.stop();
	}
}
