import java.util.Scanner;

public class Main {
    static public void main(String args[]){
        int niv, x, y;
        String opc;
        Jogar CM;
        Scanner s = new Scanner(System.in);

        System.out.print("Informe o nível de dificuldade (1_facil : 2_medio : 3_dificil) : ");
        niv = s.nextInt();
        CM = new Jogar(niv);

        CM.resposta();
        CM.mapa();

        while(true){
            x = s.nextInt();
            y = s.nextInt();
            opc = s.next();

            if(!(x > 0 && x <= niv*5 && y > 0 && y <= niv*5 && (opc.equals("J") || opc.equals("M"))) ){
                s.nextLine();
                continue;
            }

            CM.limpar();
            CM.jogada(x, y, opc);

            if(CM.perdeu(x, y) && !opc.equals("M")) 
                break;
            if(CM.ganhou())
                break;
        }

        if(CM.ganhou())
            System.out.println("\033[1;33m \nGANHOU\n \033[1;0m");
        else System.out.println("\033[1;31m \nPERDEU\n \033[1;0m");
        CM.resposta();

        s.close();
    }
}
