package controller;

import java.util.concurrent.Semaphore;

public class SaquesDeposController extends Thread {
	
	Semaphore saque;
	Semaphore deposito;
	int iD = (int)threadId();
	int dep = 0;
	int saq = 0;
	int trans;
	
	public SaquesDeposController(Semaphore saque, Semaphore deposito, int trans) {
		this.saque = saque;
		this.deposito = deposito;
		this.trans = trans;
	}
	
	@Override
	public void run() {
		transacoes();
		if ((saq + dep) == trans) {
			System.out.println("Ao final de " + trans + " transações, temos " + saq + " saques e " + dep + " depósitos");
		}
	}

	private void transacoes() {
		int transacao = (int) ((Math.random() * 2) + 1);
		if (transacao == 1) {
			try {
				saque.acquire();
				System.out.println("Usuário #" + iD + " está realizando um saque...");
				saq++;
				sleep(2500);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				System.out.println("Usuário #" + iD + " terminou o saque.");
				saque.release();
			}
		} else {
			try {
				deposito.acquire();
				System.out.println("Usuário #" + iD + " está realizando um depósito...");
				dep++;
				sleep(4000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} finally {
				System.out.println("Usuário #" + iD + " terminou o depósito.");
				deposito.release();
			}
		}
	}
	
	
	
}
