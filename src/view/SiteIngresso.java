package view;

import java.util.concurrent.Semaphore;

import controller.ThreadIngresso;

public class SiteIngresso {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int idLogin = 0 ; idLogin< 100 ; idLogin ++) {
			Thread tCliente = new ThreadIngresso(idLogin, semaforo);
			tCliente.start();
			}
	}

}
