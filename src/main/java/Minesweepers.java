import java.util.Scanner;

public class Minesweepers {
    public String[][] field ;
    public String[][] visibleField;

    public boolean isWin = false;
    public boolean isDone = false;

    public static final String mine = " * ";
    public static final String empty = "  ";
    public static final String guess = " - ";

    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public Minesweepers(){
        int row = 0;
        int column = 0;

        Scanner scan = new Scanner(System.in);


        System.out.println("Beginner - (9x9 cells and 10 mines)");
        System.out.println("Intermediate - (16x16 cells and 40 mines)");
        System.out.println("Advance - (24x24 cells and 99 mines)");
        System.out.println("---------------------");
        System.out.println("Choose level of difficulty:");

        String level = scan.nextLine();

        switch (level){
            case "Beginner":
               field = new String[11][11];
               visibleField = new String[11][11];
               setLevel(level);
                break;
            case "Intermediate":
                field = new String[18][18];
                visibleField = new String[18][18];
                setLevel(level);
                break;
            case "Advance":
                field = new String[26][26];
                visibleField = new String[26][26];
                setLevel(level);
            break;
            default:
                System.out.println("Please enter a valid options");
                break;
        }

        for(int x = 0; x < field.length; x++){
            for(int y = 0; y < field[0].length; y++){
                if((x == 0 || x == field.length - 1)||(y == 0 || y == field[0].length - 1)){
                    field[x][y] = empty;
                    visibleField[x][y] = empty;
                }
                else{
                    field[x][y] = guess;
                    visibleField[x][y] = guess;
                }
            }
        }
    }



    public static void displayField(String[][] field) {
        System.out.print("   ");
        for (int row = 1; row < field.length-1; row++) {
            System.out.print("  " + row );
        }
        System.out.println();

        for (int row = 1; row < field.length - 1; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (col < 1) {
                    System.out.print(row);
                    System.out.print("" + field[row][col]);
                } else {

                    System.out.print("" + field[row][col]);
                }
            }
            System.out.println();
        }
    }

    public void update(){
        displayField(visibleField);
        System.out.println("");
    }

    public void generateMines(int n){
        for(int m = 0; m < n; m++){

            while(true){
                int x, y = 0;
                x = (int)(10*Math.random());
                y = (int)(10*Math.random());

                if(x >= 1 && x <= 10){
                    if(y >= 1 && y <= 10){
                        if(!field[x][y].equals(mine)){
                            field[x][y] = mine;
                            break;
                        }
                    }
                }
            }
        }
    }
    public void clear(int x, int y){
        for(int i = (x - 1); i <= (x + 1); i++){
            for(int j = (y - 1); j <= (y + 1); j++){
                if(field[i][j].equals(guess) == true){
                    visibleField[i][j] = empty;
                    field[i][j] = empty;
                }
            }
        }
    }

    public String getTile(int x, int y){
        return field[x][y];
    }

    public void detect(){
        for(int x = 1; x < visibleField.length - 2; x++){
            for(int y = 1; y < visibleField.length - 2; y++){
                if(field[x][y].equals(empty) == true){
                    int nums = 0;
                    for(int i = (x - 1); i <= (x + 1); i++){
                        for(int j = (y - 1); j <= (y + 1); j++){
                            if(field[i][j].equals(mine) == true)
                                nums++;
                        }
                    }
                    visibleField[x][y] = " " + nums + " ";
                }
            }
        }
    }
    public void turn(int x, int y){
        if(field[x][y].equals(guess) == true){
            isDone = false;
            visibleField[x][y] = empty;
            field[x][y] = empty;
        }else if(field[x][y].equals(mine) == true){
            isDone = true;
            isWin = false;
            System.out.println("You've lost!");
        }else if(visibleField[x][y].equals(empty) == true && field[x][y].equals(empty)){
            isDone = false;
            System.out.println("This tile's been cleared!");
        }
    }
    public void isVictory(){
        int counter = 0;
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j].equals(guess) == true)
                    counter++;
            }
        }
        if(counter != 0)
            isWin = false;
        else{
            isWin = true;
            isDone = true;
        }
    }


    public Boolean getDone(){
        return isDone;
    }


    public Boolean getWin(){
        return isWin;
    }

    public void onEnd(){
        displayField(field);
    }
}
