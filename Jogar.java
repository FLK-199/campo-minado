

public class Jogar {
    private final Campo mapa;

    public Jogar(int nivel){
        mapa = new Campo(5*nivel, nivel);
    }

    public void resposta(){
        mapa.mostrarResposta();
    }

    public void mapa(){
        mapa.mostrarMapa();
    }

    public void jogada(int x, int y, String opc){
        if(opc.equals("M"))
            mapa.marcar(x-1,y-1);
        else mapa.revelar(x-1, y-1);

        mapa.mostrarJogada(x-1, y-1);
    }

    public void limpar(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public boolean perdeu(int x, int y){
        return mapa.perdeu(x-1, y-1);
    }
    public boolean ganhou(){
        return mapa.ganhou();
    }
}
