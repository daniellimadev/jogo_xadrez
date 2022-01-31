package modelo;

public class Bispo extends Peca {

	public Bispo(EnumCor cor, int linha, int coluna) {
        super(cor, linha, coluna, "C:/Users/danie/git/repository/xadrez/src/figs/BISPO"+cor+".png");
    }
	
	public Bispo(EnumCor cor, int linha, int coluna, String imagem) {
        super(cor, linha, coluna, imagem);
    }

    @Override
    public boolean validaMovimento(int linhaDestino, int colunaDestino) {
        return true;
    }

    
    
}
