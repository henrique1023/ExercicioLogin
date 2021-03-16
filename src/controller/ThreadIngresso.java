package controller;

import java.util.concurrent.Semaphore;

public class ThreadIngresso extends Thread {
	private int idLogin;
	private static int ingresso = 100;
	private static int statusLogin = 0;
	private Semaphore semaforo;

	public ThreadIngresso(int idlogin, Semaphore semaforo) {
		this.idLogin = idlogin;
		this.semaforo = semaforo;

	}
	@Override
	public void run() {
		login();
		try {
			semaforo.acquire();
			validaCompra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	private void login() {
		int tempoLogin = (int) ((Math.random() * 51) + 1950);
		int tempoRodando = 0;
		int tempoLimite = 1000;
		int tempo = 200;
		while (tempoRodando < tempoLogin) {
			tempoRodando += 100;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Fazendo logindo do usuario " + idLogin);
			if (tempoRodando > tempoLimite) {
				System.out.println("Não conseguimos achar o login\n Tente novamente.");
				break;
			}
		}
		statusLogin = 1;
		processoCompra();
	}



	private void validaCompra() {
		if (statusLogin == 1) {
			int ingressoCompra = (int) ((Math.random() * 4) + 1);
			System.out.println("Solicitação de compra de " + ingressoCompra +" do usuario "+ idLogin);
			if (ingressoCompra <= ingresso) {
				ingresso-= ingressoCompra;
				System.out.println("Os " + ingressoCompra + " foram comprados e reservados com sucesso!!");
			} else {
				System.out.println("Não sera possivel concluir a compra, ingressos esgotados.");
			}
		}
	}

	private void processoCompra() {
		int tempoCompra = (int) ((Math.random() * 1001) + 2000);
		int tempoRodando = 0;
		int tempoLimite = 2000;
		int tempo = 200;
		while (tempoRodando < tempoCompra) {
			tempoRodando += 100;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Processando pedido.");
			if (tempoRodando > tempoLimite) {
				System.out.println("Não conseguimos Executar a compra.");
				break;
			}
		}

	}
}
