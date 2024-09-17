package view;

import java.util.concurrent.Semaphore;

import controller.SaquesDeposController;

public class SaquesDeposPrincipal {

	public static void main(String[] args) {
		
		int transacoes = 20;
		Semaphore saques = new Semaphore(1);
		Semaphore depositos = new Semaphore(1);
			
		for (int i = 0; i < transacoes; i++) {
			SaquesDeposController saDeCon = new SaquesDeposController(saques, depositos, transacoes);;
			saDeCon.start();
		}
	}

}
