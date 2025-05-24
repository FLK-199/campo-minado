public class Campo {
    private final int tam;
    private final Matriz matriz;
    private final boolean revelado[][];
    private final boolean marcado[][];
    private int qntMarcado;
    private int qntRevelado;
    
    public Campo(int tamanho, int nivel){
        tam = tamanho;
        matriz = new Matriz(tamanho, nivel);
        revelado = new boolean[tamanho][tamanho];
        marcado = new boolean[tamanho][tamanho];
        qntMarcado = 0;
        qntRevelado = tamanho*tamanho - matriz.getQntBombas();
    }

    private void linhaSuperior(){
        System.out.print("     ");

        for(int i = 0; i < tam; i++){
            if(i+1 < 10)
                System.out.print((i+1) + "  ");
            else 
                System.out.print((i+1) + " ");
        }
        System.out.println();
        
        System.out.print("   \u250C");
        
        for(int i = 0; i < tam; i++){
            System.out.print("\u2500\u2500\u2500");
        }
        System.out.println("\u2510");
    }

    private void linhaInferior(){
        System.out.print("   \u2514");
        
        for(int i = 0; i < tam; i++){
            System.out.print("\u2500\u2500\u2500");
        }
        System.out.println("\u2518");
    }

    public void mostrarResposta(){
        int m[][] = matriz.getMatriz();

        linhaSuperior();

        for(int i = 0; i < tam; i++){
            if(i+1 < 10)
                System.out.print((i+1) + "  ");
            else 
                System.out.print((i+1) + " ");

            System.out.print("\u2502");

            for(int j = 0; j < tam; j++){
                if(m[i][j] != -1)
                    System.out.print(" " + m[i][j] + " ");
                else{
                    System.out.print("\033[1;34m" + " X " + "\033[0m");
                }  
            }

            System.out.println("\u2502");
        }

        linhaInferior();
    }
    
    public void mostrarMapa(){
        linhaSuperior();

        for(int i = 0; i < tam; i++){
            if(i+1 < 10)
                System.out.print((i+1) + "  ");
            else 
                System.out.print((i+1) + " ");

            System.out.print("\u2502");

            for(int j = 0; j < tam; j++)
                    System.out.print(" - ");

            System.out.println("\u2502");
        }

        linhaInferior();
    }

    public void mostrarJogada(int x, int y){
        int m[][] = matriz.getMatriz();

        linhaSuperior();

        for(int i = 0; i < tam; i++){
            if(i+1 < 10)
                System.out.print((i+1) + "  ");
            else 
                System.out.print((i+1) + " ");

            System.out.print("\u2502");

            for(int j = 0; j < tam; j++){
                if(marcado[i][j])
                    System.out.print("\033[1;31m" + " \u2691 " + "\033[0m");
                else if(revelado[i][j]){
                    if(m[i][j] != -1)
                        System.out.print(" " + m[i][j] + " ");
                    else 
                        System.out.print("\033[1;34m" + " X " + "\033[0m");
                }
                else 
                    System.out.print(" - ");
            }

            System.out.println("\u2502");
        }

        linhaInferior();
    }

    public void revelar(int x, int y){
        if (revelado[x][y] || marcado[x][y]) return;

        int m[][] = matriz.getMatriz();
        qntRevelado--;

        if(m[x][y] != 0){
            revelado[x][y] = true;
            return;
        }

        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        revelado[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int ni = x + dx[d];
            int nj = y + dy[d];
            if (ni >= 0 && ni < tam && nj >= 0 && nj < tam && m[ni][nj] != -1 && !revelado[ni][nj]) {
                revelar(ni, nj);
            }
        }
    }

    public void marcar(int x, int y){
        if (revelado[x][y]) return;

        marcado[x][y] = !marcado[x][y];
        int m[][] = matriz.getMatriz();

        if(marcado[x][y] && m[x][y] == -1) qntMarcado++;
        if(!marcado[x][y] && m[x][y] == -1) qntMarcado--; 
    }

    public boolean ganhou(){
        return qntMarcado == matriz.getQntBombas() && qntRevelado <= 0;
    }

    public boolean perdeu(int x, int y){
        int m[][] = matriz.getMatriz();
        return m[x][y] == -1;
    }
}