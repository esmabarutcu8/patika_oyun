import java.util.Scanner;
import java.util.*;

public class MineSweeper {
    int[][] map ;
    String[][] game;
    int satir,sutun;
    boolean isGameOver=false;
   ;
    MineSweeper(int satir,int sutun){
        this.satir = satir;
        this.sutun= sutun;
        this.map = new int[satir][sutun];
        this.game = new String[satir][sutun];
        createGame(sutun,satir );
    }

    private void createGame(int row, int col) {
        int numberOfMine= (col*row)/4;
        Random random = new Random();
        int sayi= 0;
        while (sayi<numberOfMine){
            int mineRow= random.nextInt(row);
            int mineCol= random.nextInt(col);
            if(this.map[mineRow][mineCol]!=1){
                this.map[mineRow][mineCol]=1;
                sayi++;
            }
        }
        for (String[] strings : game) {
            Arrays.fill(strings, "-");

        }
    }

    public void run() {
        printMap();
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver){
            printGame();
            System.out.print("Satır:");
            int inputSatir= scanner.nextInt();
            System.out.print("Sütun:");
            int inputSutun=scanner.nextInt();
            if(inputSutun>=sutun || inputSutun<0){
                System.out.println("Hatalı sütun");
                continue;
            }
            if(inputSatir>=satir || inputSatir<0){
                System.out.println("Hatalı satır");
                continue;
            }
            select(inputSatir,inputSutun);
            if(isFinished()){
                System.out.println("Tebrikler oyunu tamamlandınız");
                break;
            }
        }
        if(isGameOver){
            System.out.println("Mayına bastınız");
            printMap();
        }
    }

    private void printMap() {
        System.out.println("------------");
        for(int[] i : this.map){
            for(int j: i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    private void select(int i, int j){
        int count=0;
        if(map[i][j]==1){
            this.isGameOver=true;
        }
        else {
            for (int k = i-1; k <=i+1 ; k++) {
                for (int l = j-1; l <=j+1 ; l++) {
                    if(k==i && l==j){
                        continue;
                    }
                    try {
                        count+=map[k][l];
                    }catch (Exception ignored){

                    }
                }
            }
        }

        this.game[i][j]=String.valueOf(count);
    }

    private void printGame(){
        System.out.println("-------------");
        for(String[] i : this.game){
            for(String j: i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
    private boolean isFinished(){
        boolean isEmpty=true;
        for(int i= 0; i<game.length;i++){
            for(int j=0; j<game[i].length;j++){
                if (Objects.equals(game[i][j], "-") && map[i][j]==0 ) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }
}



