package modelo;

public class Torre extends Peca {

    

    public Torre(EnumCor cor, int linha, int coluna) {
        super(cor, linha, coluna, "C:/Users/danie/git/repository/xadrez/src/figs/TORRE"+cor+".png");
    }

    public Torre(EnumCor cor, int linha, int coluna, String imagem) {
        super(cor, linha, coluna, imagem);
    }

    @Override
    public boolean validaMovimento(int linhaDestino, int colunaDestino) {
        return true;
    }
    
}
