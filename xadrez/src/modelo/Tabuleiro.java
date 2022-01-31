package modelo;

import java.util.ArrayList;
import java.util.List;

import controle.ControlaTempo;
import visao.JXadrez;

public class Tabuleiro {

    private Peca[][] pecas;
    private Peca pecaSelecionada = null;
    private EnumCor vez = EnumCor.BRANCO;
    public static final int TEMPO_JOGADA = 10000;
    private ControlaTempo controlaTempo;
    private List<Peca> pecasForaDejogo;
    
    public Tabuleiro(ControlaTempo controlaTempo) {
    	this.controlaTempo = controlaTempo;
        this.pecas = new Peca[8][8];
        this.pecasForaDejogo = new ArrayList<>();
        Torre torreBranca1 = new Torre(EnumCor.BRANCO,0,0);
        Torre torreBranca2 = new Torre(EnumCor.BRANCO,0,7);
        this.adicionaPeca(torreBranca1);
        this.adicionaPeca(torreBranca2);
        
        Torre torrePreto1 = new Torre(EnumCor.PRETO,7,0);
        Torre torrePreto2 = new Torre(EnumCor.PRETO,7,7);
        this.adicionaPeca(torrePreto1);
        this.adicionaPeca(torrePreto2);
        
        Cavalo cavaloBranco1 = new Cavalo(EnumCor.BRANCO, 0,1);
        Cavalo cavaloBranco2 = new Cavalo(EnumCor.BRANCO, 0,6);
        this.adicionaPeca(cavaloBranco1);
        this.adicionaPeca(cavaloBranco2);
        
        Cavalo cavaloPreto1 = new Cavalo(EnumCor.PRETO, 7,1);
        Cavalo cavaloPreto2 = new Cavalo(EnumCor.PRETO, 7,6);
        this.adicionaPeca(cavaloPreto1);
        this.adicionaPeca(cavaloPreto2);
        
        Bispo bispoBranco1 = new Bispo(EnumCor.BRANCO, 0,2);
        Bispo bispoBranco2 = new Bispo(EnumCor.BRANCO, 0,5);
        this.adicionaPeca(bispoBranco1);
        this.adicionaPeca(bispoBranco2);
        
        Bispo bispoPreto1 = new Bispo(EnumCor.PRETO, 7,2);
        Bispo bispoPreto2 = new Bispo(EnumCor.PRETO, 7,5);
        this.adicionaPeca(bispoPreto1);
        this.adicionaPeca(bispoPreto2);
        
        Rainha rainhaBranco = new Rainha(EnumCor.BRANCO, 0,3);
        Rei reiBranco = new Rei(EnumCor.BRANCO, 0,4);
        this.adicionaPeca(rainhaBranco);
        this.adicionaPeca(reiBranco);
        
        Rainha rainhaPreto = new Rainha(EnumCor.PRETO, 7,3);
        Rei reiPreto = new Rei(EnumCor.PRETO, 7,4);
        this.adicionaPeca(rainhaPreto);
        this.adicionaPeca(reiPreto);
        
        for (int i = 0; i < 8; i++) {
        	Peao peaoBranco = new Peao(EnumCor.BRANCO, 1,i);
        	this.adicionaPeca(peaoBranco);
        	
        	Peao peaoPreto = new Peao(EnumCor.PRETO, 6,i);
        	this.adicionaPeca(peaoPreto);
        }
    } 
    
    public List<Peca> getPecasForaDejogo() {
    	return this.pecasForaDejogo;
    }
    
    public EnumCor getVez() {
    	return this.vez;
    }
    
    public Peca getPecaSelecionada() {
    	return this.pecaSelecionada;
    }
    
    public void setPecaSelecionada(Peca peca) {
    	this.pecaSelecionada = peca;
    }
    
    public Peca getPeca(int linha, int coluna) {
        return this.pecas[linha][coluna];
    }
    
    public void setPeca(Peca peca) {
    	this.pecas[peca.getLinha()][peca.getColuna()] = peca;
    	peca.setTabuleiro(this);
    }

    public void adicionaPeca(Peca peca) {
        this.pecas[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }

    public void selecionaPeca(Peca peca) {
        if (peca.isSelecionada()) { // Quando a peça já ¡ estar selecionada, ele desmarca
            peca.setSelecionada(false);
            this.pecaSelecionada = null;
        } else { // Quando a peça não estar selecionada, ele marca a peça
            peca.setSelecionada(true);
            this.pecaSelecionada = peca;
        }
    }

    public void movePeca(Peca peca, int novaLinha, int novaColuna) {
    	if (peca.validaMovimento(novaLinha, novaColuna)) {
    		this.pecas[peca.getLinha()][peca.getColuna()] = null;
    		peca.setLinha(novaLinha);
    		peca.setColuna(novaColuna);
    		if (peca instanceof Peao) {
    			Peao peao = (Peao) peca;
        		peao.setPrimeiroMovimento(false);
        		
    		}
    		this.setPeca(peca);
    		this.selecionaPeca(peca);
    		
    		this.inverteVez();
    		
    	}
    }

    public void inverteVez() {
        if (this.vez.equals(EnumCor.BRANCO)) {
            this.vez = EnumCor.PRETO;
        } else {
            this.vez = EnumCor.BRANCO;
        }
        JXadrez.setVez(this.vez);
        controlaTempo.zeraCronometro();
    }

	public void realizaJogada(int linha, int coluna) {
		
		Peca peca = this.getPeca(linha, coluna);
		if (this.pecaSelecionada == null) {
			if (peca != null && peca.getCor().equals(this.vez)) {
				this.selecionaPeca(peca);
			}
		} else {
			if (this.pecaSelecionada == peca) {
				this.selecionaPeca(peca);
			}else {
				if (peca == null) { // só mover uma célula vazia
					this.movePeca(this.pecaSelecionada, linha, coluna);
				}
				if (peca != null && !peca.getCor().equals(this.pecaSelecionada.getCor())) {// comer o adversário
					peca.setEliminada(true);
					this.pecasForaDejogo.add(peca);
					this.movePeca(this.pecaSelecionada, linha, coluna);
				}
			}
		}
  }
	
}
