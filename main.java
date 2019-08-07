/*
Rose Keenan
Tic Tac Toe game!
 */

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("\nWELCOME TO TIC TAC TOE!\n");
        System.out.println("Please choose game option (1 or 2): ");
        System.out.println("1. Player vs. Player \n2. Player vs. Computer");
        Scanner input = new Scanner(System.in);
        String option = input.next();
        while (!option.equals("1") && !option.equals("2")) {
            System.out.println("Please enter a valid game option: ");
            option = input.next();
        }
        int optionInt = Integer.parseInt(option);
        if (optionInt == 1) {
            playerPlayer();
        }
        else {
            playerComputer();
        }
    }

    public static void playerPlayer() {
        System.out.println("\nPLAYER VS. PLAYER");
        System.out.println("\nWelcome players!");
        boolean isComputer = false;
        int counter = 0;
        String playerOne = playerOne();
        String playerTwo = playerTwo(playerOne);
        System.out.println("Player 1: " + playerOne);
        System.out.println("Player 2: " + playerTwo);
        String[][] board = newBoard();
        printBoard(board);
        boolean win = false;
        while (win == false) {
            System.out.println("\nPlayer 1, your turn!");
            board = userMove(board, playerOne);
            counter++;
            if (counter == 9) {
                System.out.println("It's a tie!");
                printBoard(board);
                win = true;
                break;
            }
            printBoard(board);
            win = isWinner(board, playerOne, playerTwo, isComputer);
            if (win == true) {
                break;
            }
            System.out.println("\nPlayer 2, your turn!");
            board = userMove(board, playerTwo);
            counter++;
            printBoard(board);
            win = isWinner(board, playerOne, playerTwo, isComputer);
        }
    }

    public static void playerComputer(){
        System.out.println("\nPLAYER VS. COMPUTER");
        System.out.println("\n'You think you can beat me?!' ");
        boolean isComputer = true;
        int counter = 0;
        String player = playerOne();
        String computer = playerTwo(player);
        System.out.println("Player 1: " + player);
        System.out.println("Computer: " + computer);
        String[][] board = newBoard();
        printBoard(board);
        boolean win = false;
        while (win == false) {
            System.out.println("\nPlayer 1, your turn!");
            board = userMove(board, player);
            counter++;
            if (counter == 9) {
                System.out.println("It's a tie!");
                printBoard(board);
                win = true;
                break;
            }
            printBoard(board);
            win = isWinner(board, player, computer, isComputer);
            if (win == true) {
                break;
            }
            System.out.println("\nPlayer 2, your turn!");
            board = computerMove(board, computer);
            counter++;
            printBoard(board);
            win = isWinner(board, player, computer, isComputer);
        }

    }

    //Chooses mark of player one.
    //Parameters: Nothing
    //Returns: String of player one mark
    public static String playerOne() {
        Scanner input = new Scanner(System.in);
        System.out.print("Player 1, please choose X or O: ");
        String playerOne = input.next().toUpperCase();
        while(!playerOne.equals("X") && !playerOne.equals("O")) {
            System.out.print("Please enter X or O: ");
            playerOne = input.next().toUpperCase();
        }
        return playerOne;
    }

    //Chooses mark of player two.
    //Parameters: player one mark - they cannot be the same
    //Return: String of player two mark
    public static String playerTwo(String playerOne) {
        String playerTwo;
        if(playerOne.equals("X")) {
            playerTwo = "O";
        }
        else {
            playerTwo = "X";
        }
        return playerTwo;
    }

    public static String[][] newBoard() {
        String[][] board = {{" ", "  1  ", "  2  ", "  3  "}, {"1", "|   |","|   |","|   |"}, {"2", "|   |","|   |","|   |"}, {"3", "|   |","|   |","|   |"}};
        return board;
    }

    public static void printBoard(String[][] board) {
        for(int i = 0; i < board.length; i++) {
            System.out.println("");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
        }
    }

    public static String[][] userMove(String[][] board, String move) {
        System.out.print("\nPlease enter a row: ");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        System.out.print("Please enter a column: ");
        int col = input.nextInt();
        while(row > 3 || col > 3 || board[row][col] != "|   |") {
            System.out.println("Please enter a valid move.");
            printBoard(board);
            System.out.print("\nPlease enter a row: ");
            row = input.nextInt();
            System.out.print("Please enter a column: ");
            col = input.nextInt();
        }
        board[row][col] = "| " + move + " |";
        return board;
    }

    public static String[][] computerMove(String board[][], String move) {
        int row = (int)(Math.random() * ((4 - 1)) + 1);
        System.out.println(row);
        int col = (int)(Math.random() * ((4 - 1)) + 1);
        System.out.println(col);
        while(board[row][col] != "|   |") {
            row = (int)(Math.random() * ((4 - 1)) + 1);
            col = (int)(Math.random() * ((4 - 1)) + 1);
        }
        board[row][col] = "| " + move + " |";
        return board;
    }

    public static boolean isWinner(String[][] board, String playerOne, String playerTwo, boolean isComputer) {
        int i = 1;
        while(i < 4) {
            if (board[i][1].equals(board[i][2]) && board[i][2].equals(board[i][3]) && !board[i][1].equals("|   |")) {
                if (board[i][1].equals("| " + playerOne + " |")) {
                    System.out.println("Player 1 wins!");
                    return true;
                } else {
                    System.out.println("Player 2 wins!");
                    return true;
                }
            }
            i++;
        }
        int j = 1;
        while( j < 4) {
            if (board[1][j].equals(board[2][j]) && board[2][j].equals(board[3][j]) && !board[1][j].equals("|   |")) {
                if (board[1][j].equals("| " + playerOne + " |")) {
                    System.out.println("\nPlayer 1 wins!");
                    return true;
                } else {
                    if(isComputer == false) {
                        System.out.println("\nPlayer 2 wins!");
                        return true;
                    }
                    else {
                        System.out.println("\n'Haha! I beat you!'");
                        return true;
                    }
                }
            }
            j++;
        }
        if((board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3])) || (board[1][3].equals(board[2][2]) && board[2][2].equals(board[3][1])) && (!board[2][2].equals("|   |"))){
            if(board[2][2].equals("| " + playerOne + " |")) {
                System.out.println("\nPlayer 1 wins!");
                return true;
            }
            else {
                if(isComputer == false) {
                    System.out.println("\nPlayer 2 wins!");
                    return true;
                }
                else {
                    System.out.println("\nComputer: 'Haha! I beat you!' ");
                    return true;
                }
            }
        }
        return false;
    }
}
