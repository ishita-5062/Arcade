import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Game{
    static String[] board= new String[9];
    static boolean turn=true;

    static String check_winner(){
        String line="";

        for (int i=0; i<8; i++){
            switch (i){
                case 0:
                    line=board[0]+board[1]+board[2];
                    break;

                case 1:
                    line=board[3]+board[4]+board[5];
                    break;

                case 2:
                    line=board[6]+board[7]+board[8];
                    break;

                case 3:
                    line=board[0]+board[3]+board[6];
                    break;

                case 4:
                    line=board[1]+board[4]+board[7];
                    break;

                case 5:
                    line=board[2]+board[5]+board[8];
                    break;

                case 6:
                    line=board[0]+board[4]+board[8];
                    break;

                case 7:
                    line=board[2]+board[4]+board[6];
                    break;
            }

            if (line.equals("XXX") ) {
                return "X is the winner!";
            } else if (line.equals("OOO")) {
                return "O is the winner!";
            }
        }

        boolean flag=false;
        for (int i = 0; i < 9; i++) {
            if (Arrays.asList(board).contains(String.valueOf(i+1))) {
                flag = true;
                break;
            }
        }

        if(flag==false){
            return "Draw :(";
        }

        return "Continue";}

    static void Print_game(){

        System.out.println();
        System.out.println("+---+---+---+");
        System.out.println("| "+board[0]+" | "+board[1]+" | "+board[2]+" |");
        System.out.println("+---+---+---+");
        System.out.println("| "+board[3]+" | "+board[4]+" | "+board[5]+" |");
        System.out.println("+---+---+---+");
        System.out.println("| "+board[6]+" | "+board[7]+" | "+board[8]+" |");
        System.out.println("+---+---+---+");
        System.out.println();
    }
}

class Board {
    static int row, column;
    public static Character[][] Board = new Character[11][11];
    Random rand = new Random();

    Board() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                Board[i][j] = '.';
            }
        }

    }

    void FoodGen() {
        int foodx = rand.nextInt(9) + 1;
        int foody = rand.nextInt(9) + 1;
        if (Board[foodx][foody] == '.') {
            Board[foodx][foody] = 'O';
        } else {
            FoodGen();
        }
    }

    static void PrintBoard(int[] x_coord, int[] y_coord, int snekLength) {
        System.out.println("+---++---++---++---++---++---++---++---++---+");

        Board[x_coord[0]][y_coord[0]] = '℈';

        for (int i = 1; i < snekLength; i++) {
            Board[x_coord[i]][y_coord[i]] = '⌘';
        }

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (Board[i][j] == '.') {
                    System.out.print("|   |");
                } else {
                    System.out.print("| " + Board[i][j] + " |");
                }
            }
            System.out.println();
            System.out.print("+---++---++---++---++---++---++---++---++---+");
            System.out.println();
        }
    }
}


class Snek {
    static int maxSize = 70;
    static int xhead, yhead;
    static int[] x_coord = new int[70];
    static int[] y_coord = new int[70];
    static int snekLength = 3;

    Snek() {
        x_coord[0] = 5;
        x_coord[1] = 5;
        x_coord[2] = 5;

        y_coord[0] = 4;
        y_coord[1] = 5;
        y_coord[2] = 6;

        for (int i = 3; i < 70; i++) {
            x_coord[i] = -1;
            y_coord[i] = -1;
        }
    }

    static void move() {
        Board.Board[x_coord[snekLength - 1]][y_coord[snekLength - 1]] = '.';

        for (int i = snekLength-1; i > 0 ; i--) {
            x_coord[i] = x_coord[i - 1];
            y_coord[i] = y_coord[i - 1];
        }

        x_coord[0] = xhead;
        y_coord[0] = yhead;

    }

    static void findNextHead(char choice) {
        if (choice == 'a' || choice == 'A') {
            yhead = y_coord[0] - 1;
            xhead = x_coord[0];
        } else if (choice == 's' || choice == 'S') {
            xhead = x_coord[0] + 1;
            yhead = y_coord[0];
        } else if (choice == 'd' || choice == 'D') {
            yhead = y_coord[0] + 1;
            xhead = x_coord[0];
        } else if (choice == 'W' || choice == 'w') {
            xhead = x_coord[0] - 1;
            yhead = y_coord[0];
        } else {
            System.out.println("Invalid Choice! Please try again");
            //figure out in main
        }
    }

    static boolean crash() {
        if (xhead == 0 || xhead == 10 || yhead == 0 || yhead == 10) {
            return true;
        }
        for (int i = 1; i < snekLength; i++) {
            if (xhead == x_coord[i] && yhead == y_coord[i]) {
                return true;
            }
        }
        return false;
    }

    static void EatnGrow() {
        snekLength++;
        for (int i = snekLength-1; i > 0 ; i--) {
            x_coord[i] = x_coord[i - 1];
            y_coord[i] = y_coord[i - 1];
        }
        x_coord[0] = xhead;
        y_coord[0] = yhead;

    }
}

public class Main {
    static void print_cards(Character[] arr){
        for (int i=0; i<8; i++){
            System.out.print("   "+i+"   ");
        }
        System.out.println();
        for(Character x: arr){
            System.out.print(" | "+x+" | ");
        }
        System.out.println();
        System.out.println();
    }

    static boolean check(Character[] arr, int index){
        if (arr[index]=='X'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the The Jimmy Jab Games");
        System.out.println("What will the challenge of your choice?");
        System.out.println("Enter 1 if you wish to play TicTacToe with a friend.");
        System.out.println("Enter 2 if you wish to test your memory.");
        System.out.println("Enter 3 if you wish to feed a hungry snake.");


        System.out.print("Enter your choice: ");
        int s = sc.nextInt();

            switch (s) {
                case 1:
                    int inp;
                    boolean invalid;
                    for (int i = 1; i < 10; i++) {
                        Game.board[i - 1] = Integer.toString(i);
                    }
                    Game.Print_game();

                    while (Game.check_winner() == "Continue") {

                        if (Game.turn) {
                            System.out.println("X's turn!");
                            System.out.print("Please enter where you want to place an X: ");
                            inp = sc.nextInt();
                            invalid = Game.board[inp - 1].equals("X") || Game.board[inp - 1].equals("O") || inp > 9 || inp < 1;
                            if (invalid) {
                                System.out.println("Invalid input");
                                System.out.println("Please enter again");
                            } else {
                                Game.board[inp - 1] = "X";
                                Game.turn = false;
                            }
                        } else {
                            System.out.println("O's turn!");
                            System.out.print("Please enter where you want to place an O: ");
                            inp = sc.nextInt();
                            invalid = Game.board[inp - 1].equals("X") || Game.board[inp - 1].equals("O");
                            if (invalid) {
                                System.out.println("Invalid input");
                                System.out.println("Please enter again");
                            } else {
                                Game.board[inp - 1] = "O";
                                Game.turn = true;
                            }
                        }
                        Game.Print_game();
                    }
                    System.out.println(Game.check_winner());
                    break;

                case 2:
                    ArrayList<Character> board = new ArrayList<>(8);
                    board.add('A');
                    board.add('A');
                    board.add('B');
                    board.add('B');
                    board.add('C');
                    board.add('C');
                    board.add('D');
                    board.add('D');

                    Character[] x_array = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
                    Collections.shuffle(board);
                    int firstindex;
                    int secondindex;
                    int no_of_pairs = 0;

                    print_cards(x_array);

                    while (no_of_pairs < 4) {
                        System.out.println("Enter the index of the card to be flipped first:");
                        firstindex = sc.nextInt();
                        while (!check(x_array, firstindex)) {
                            System.out.println("Incorrect Input! Please enter a valid index.");
                            firstindex = sc.nextInt();
                        }
                        x_array[firstindex] = board.get(firstindex);
                        print_cards(x_array);

                        System.out.println("Enter the index of the card to be flipped second:");
                        secondindex = sc.nextInt();
                        while (!check(x_array, secondindex)) {
                            System.out.println("Incorrect Input! Please enter a valid index.");
                            secondindex = sc.nextInt();
                        }
                        x_array[secondindex] = board.get(secondindex);
                        print_cards(x_array);

                        if (board.get(firstindex) == board.get(secondindex)) {
                            no_of_pairs++;
                            System.out.println("Yay! You found a pair ^_^");
                        } else {
                            x_array[firstindex] = 'X';
                            x_array[secondindex] = 'X';
                            System.out.println("Sorry! This was not a pair :|");
                            print_cards(x_array);
                        }
                    }
                    System.out.println("Congratulations! You won :D");
                    System.out.println("--------------x------------");
                    break;

                case 3:
                    Board B = new Board();
                    Snek S = new Snek();

                    B.FoodGen();
                    Board.PrintBoard(Snek.x_coord, Snek.y_coord, Snek.snekLength);

                    boolean win = true;
                    while (Snek.snekLength <= 70) {
                        System.out.print("Enter the direction you want to move in using ASWD notation: ");
                        char ch = sc.next().charAt(0);

                        Snek.findNextHead(ch);
                        if (Snek.crash()) {
                            win = false;
                            break;
                        } else {
                            if (Board.Board[Snek.xhead][Snek.yhead] == 'O') {
                                Snek.EatnGrow();
                                B.FoodGen();
                            } else {
                                Snek.move();
                            }
                        }
                        Board.PrintBoard(Snek.x_coord, Snek.y_coord, Snek.snekLength);
                    }

                    sc.close();
                    System.out.println();
                    if (win) {
                        System.out.println("Congratulations! VICTORYYY 🚀");
                    } else {
                        System.out.println("GAME OVER 🫥  ");
                    }
                    break;
                default:
                    System.out.println("Invalid Choice :(");
            }
        }
    }
