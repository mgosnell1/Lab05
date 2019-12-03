import java.util.Scanner;
import java.util.Arrays;

public class ConnectFour {
// im adding a comment here ok
Static String greeting = "hello world";
    public static void printBoard (char[][]array) {
        //this method takes the board array as an input, and prints out the array at each index without the brackets or commas
        for (int i= 0; i < array.length; i++){
            String output;
            output = Arrays.toString(array[i]).replace(",","")
                    .replace("[","").replace("]","");
            System.out.println(output);
        }
    }


    public static void initializeBoard (char[][] array) {
//this chunck of code takes the board array as an input, and fills every value with the value '-'
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[0].length; j++) {

                    array[i][j] = '-';
            }
        }

    }

    public static int insertChip(char[][]array, int col, char chipType){
        int rowReturn = 0;
/*this method checks the bottom of the column that the user inputs to see if the space is empty, and fills it
with the player's designated character. It then returns the row that the character is placed in
 */
        for (int a = (array.length - 1); a >= 0; a--) {

            if (array[a][col] == '-'){

                array[a][col] = chipType;
                rowReturn = a;
                break;
            }
            else {

            }

        }
        rowReturn = array.length - (rowReturn + 1) ;
        return rowReturn;
    }
    public static boolean checkTie(char[][] array){
      /*this method checks the top row to see if there are any dashes remaining. if there are
      '-' remaining in the top row, then the loop breaks and the method returns false because there
      cannot be a tie if there is still a '-'
       */
        String tieChecker = "";
        boolean tieReturn = true;
        for(int i = 0; i < array[0].length; i++) {
            tieChecker = tieChecker + array[0][i];
          }
        for (int i = 0; i <= (tieChecker.length() - 1); i++){
            if (tieChecker.charAt(i) == '-') {
            tieReturn = false;
            break;
            }
        }
       return tieReturn;
    }


    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        boolean horizontalWinner = false;
        boolean verticalWin = false;
        int counter1 = 0;
        int counter2 = 0;


        for (int i = 0; i < array[0].length; i++)    {
/*this for loop checks for a horizontal win by going through the board horizontally starting at index 0 horizontally and
starts vertically at the height which the chip was placed
   */
            char charAtIndex = array[array.length - (row + 1)][i];

            if ( charAtIndex == chipType) {
                counter1++;
                if (counter1 > 3) {
                    horizontalWin = true;
                    break;
                }
            }
            else {
                counter1 = 0;
            }
        }

                for (int i = 0; i < array.length; i++)    {
        /*this for loop checks for a vertical win by going through the board vertically starting at index 0 vertically and
        starts horizontally at the column that the user chose
           */
                    char charAtIndex = array[i][col];

                    if (charAtIndex == chipType) {
                        counter2++;

                        if (counter2 > 3) {
                            verticalWin = true;
                            break;
                        }
                    }
                    else {
                        counter2 = 0;
                    }
                }

// if the user wins vertically or horizontally, the method returns true
        if (verticalWin || horizontalWin){
            return true;
        }
        else {
            return false;
        }
    }


    public static void main(String[] args) {
        char characterInput;
        String whichPlayer;
        int columnInput;
        int iterations = 1;
        boolean run = true;
        Scanner scnr = new Scanner(System.in);
        //the above code initializes all variables needed in the main method
        System.out.println("What would you like the height of the board to be? ");
        int height = scnr.nextInt();
        System.out.println("What would you like the length of the board to be?");
        int width = scnr.nextInt();
        char [][] board = new char[height][width];
        //the above code makes a board with
        initializeBoard(board);
        printBoard(board);
        //the above code initalizes and prints the board using the previously declared methods
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        do {

            if (iterations % 2 == 0){
                charInput = 'o';
                whichPlayer = "Player 2";
            }
            else {
                charInput = 'x';
                whichPlayer = " Player 1";
            }
// the above conditional statement is used to determine which player is placing a chip and their corresponding chip
            System.out.println(whichPlayer + ": Which column would you like to choose?");
            columnInput = scnr.nextInt();

            int row = insertChip(board,columnInput,charInput);
            printBoard(board);
// the above code takes the user's column input, and calls the method which places the chip in the corresponding column


           if (checkIfWinner(board,columnInput,row,charInput)){
               System.out.println(whichPlayer + " won the game!");
                run = false;
           }
           else  if (checkTie(board)){
               System.out.println("Draw. Nobody wins.");
               run = false;
           }
           else
// the above code calls the method to check for a win or a tie
                iterations++;
        }
        while (run);

    }
}

