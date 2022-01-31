package controle;

import java.awt.Color;
import java.lang.Runnable;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import modelo.Peca;
import modelo.Tabuleiro;
import visao.JTabuleiro;

public class ControlaTempo implements Runnable {

	private JTabuleiro jTabuleiro;
	
	private int tempoGasto = 0;
	
	private JProgressBar barraProgresso;
	
	public ControlaTempo(JProgressBar pbTempo) {
		super();
		this.barraProgresso = pbTempo;	
	}
	
	public void SetTabuleiro(JTabuleiro jTabuleiro) {
		this.jTabuleiro = jTabuleiro;
	}
	
	public void zeraCronometro() {
		this.tempoGasto = 0;
	}
	
	@Override
	public void run() {
		while (true) {
			
			try {
				Thread.sleep(1);
				tempoGasto += 1;
				barraProgresso.setValue(tempoGasto);
				if (tempoGasto > 0 && tempoGasto < 4000) {
					barraProgresso.setForeground(Color.CYAN);
				}
				else if (tempoGasto >= 4000 & tempoGasto < 7000) {
					barraProgresso.setForeground(Color.YELLOW);
				}
			
				else if (tempoGasto >= 7000) {
					barraProgresso.setForeground(Color.RED);
				}
				if (tempoGasto >= Tabuleiro.TEMPO_JOGADA) {
					JOptionPane.showMessageDialog(null, "O jogado" + jTabuleiro.getTabuleiro().getVez() + "perdeu a vez");

					if (jTabuleiro.getTabuleiro().getPecaSelecionada() != null) {
						jTabuleiro.getTabuleiro().getPecaSelecionada().setSelecionada(false);
						jTabuleiro.getTabuleiro().setPecaSelecionada(null);
					}
					jTabuleiro.getTabuleiro().inverteVez();
					jTabuleiro.desenhaTabuleiro();			
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
