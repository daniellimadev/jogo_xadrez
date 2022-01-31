package modelo;

public class Peao extends Peca {

    private boolean primeiroMovimento = true;

    public Peao(EnumCor cor, int linha, int coluna) {
        super(cor, linha, coluna, "C:/Users/danie/git/repository/xadrez/src/figs/PEAO"+cor+".png");
    }
    
    public Peao(EnumCor cor, int linha, int coluna, String imagem) {
        super(cor, linha, coluna, imagem);
    }

    @Override
    public boolean validaMovimento(int linhaDestino, int colunaDestino) {
    	Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);
    	if (pecaDestino == null && colunaDestino != getColuna()) {
    		return false;
    	}
        return true;
    }
   
    
    public void setPrimeiroMovimento(boolean primeiroMovimento) {
    	this.primeiroMovimento = primeiroMovimento;
    }
    
}
