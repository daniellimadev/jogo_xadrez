package visao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;	
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controle.ControlaTempo;
import modelo.EnumCor;
import modelo.Tabuleiro;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class JXadrez extends JFrame {
	
	private static JLabel lbVez;
    
	private Tabuleiro tabuleiro;
	
	private final JButton btReiniciarJogo;
	private final ControlaTempo controlaTempo;
	private final JTabuleiro jTabuleiro;
	public static final JPanel painelCemiterio = new JPanel();
	public JProgressBar pbTempo;
	
    public JXadrez() {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
        pbTempo = new JProgressBar();
        pbTempo.setMinimum(0);
        pbTempo.setMaximum(10000);
        this.controlaTempo = new ControlaTempo(pbTempo);
        this.tabuleiro = new Tabuleiro(controlaTempo);
        this.jTabuleiro = new JTabuleiro(tabuleiro);
        controlaTempo.SetTabuleiro(jTabuleiro);
        this.add(jTabuleiro, BorderLayout.CENTER);
                
        JPanel pnTopo = new JPanel();
        lbVez = new JLabel("VEZ DE: BRANCO");
        pnTopo.add(lbVez);
        this.add(pnTopo, BorderLayout.NORTH); 
        
        final JPanel pnLateral = new JPanel();
        pnLateral.setLayout(new GridLayout(10,1));
        btReiniciarJogo = new JButton ("Reiniciar Jogo");
        
        btReiniciarJogo.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent ev) {
        		reiniciaJogo();
        	}
        	
        });
        
        //painelCemiterio.setLayout(new FlowLayout());
        this.add(painelCemiterio, BorderLayout.EAST);
        
        pnLateral.add(btReiniciarJogo);
        
        this.add(pnLateral, BorderLayout.WEST);
        this.add(pbTempo, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
        
        Thread threadTempo = new Thread(controlaTempo);
        threadTempo.start();
        
        this.pack();
        this.setVisible(true);
    }
    
    private void reiniciaJogo() {
    	controlaTempo.zeraCronometro();
    	this.tabuleiro = new Tabuleiro(controlaTempo);
    	this.jTabuleiro.setTabuleiro(this.tabuleiro);
    	this.jTabuleiro.desenhaTabuleiro();
    	setVez(tabuleiro.getVez());
    }

    public static void main(String args[]) {
        new JXadrez();
    }
    
    public static void	 setVez(EnumCor corVez) {
    	lbVez.setText("VEZ DE: " + corVez);
    }
    
}
