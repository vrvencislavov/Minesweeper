import java.util.Scanner;

public class MinesweepersEngine {

    public static void test(){
        Minesweepers game = new Minesweepers();
        Scanner scan = new Scanner(System.in);

        String levelOfGame = game.getLevel();

        switch (levelOfGame){
            case "Beginner":
                game.generateMines(10);
                break;
            case "Intermediate":
                game.generateMines(40);
                break;
            case "Advanced":
                game.generateMines(99);
                break;
        }



//        game.generateMines(16);
        game.update();


        int x, y;
        System.out.println("Enter your move, (row, column)");
        x = scan.nextInt();
        y = scan.nextInt();

        if(game.getTile(x,y).equals(" * ") == true){
            game.generateMines(1);
            game.field[x][y] = " ? ";
        }

        game.clear(x,y);
        game.detect();
        game.update();

        while(true){
            if(game.getDone() == true && game.getWin() == true){
                System.out.println("You win!");
                game.onEnd();
                break;
            }else if(game.getDone() == true){
                game.onEnd();
                break;
            }else if(game.getDone() == false){
                x = -1;
                y = -1;
                System.out.print("Enter an x coordinate.");
                y = scan.nextInt();
                System.out.print("Enter a y coordinate.");
                x = scan.nextInt();
                game.turn(x,y);
                game.isVictory();
                game.detect();
                game.update();
            }

        }
    }
}
