import java.util.Random;

public class Matriz {
    private final int mat[][];
    private final int tam;
    private final int nivel;
    private final Random r = new Random();
    private int qntbombas;

    public Matriz(int tamanho, int nivel){
        tam = tamanho;
        this.nivel = nivel;
        qntbombas = 0;
        mat = gerarMatriz();
   }

    public int[][] getMatriz(){
        return mat;
    }

    public int getQntBombas(){
        return qntbombas;
    }

    private int[][] gerarMatriz(){
        int m[][] = new int[tam][tam];
        int lim = 0;

        if(nivel == 1) lim = 3;
        if(nivel == 2) lim = 12;
        if(nivel == 3) lim = 20;

        while(qntbombas < lim){
            int i = r.nextInt(tam);
            int j = r.nextInt(tam);

            if(m[i][j] == -1) continue;

            m[i][j] = -1;
            qntbombas++;

            int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
            int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};

            for (int d = 0; d < 8; d++) {
                int ni = i + dx[d];
                int nj = j + dy[d];
                if (ni >= 0 && ni < tam && nj >= 0 && nj < tam && m[ni][nj] != -1) {
                    m[ni][nj]++;
                }
            }
        }

        return m;
    }
    
    public void imprimirMatriz(){
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++)
                System.out.printf("%3d", mat[i][j]);
            System.out.println();
        }
    }
}
