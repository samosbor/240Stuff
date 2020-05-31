package hangman;

import java.io.File;

public class RunGame {

    public RunGame(){}

    public static void main(String[] args){
        try {
            IEvilHangmanGame game = new HangmanGame();
            String dictionary = args[0];
            File file = new File(dictionary);
            int wordLength = Integer.parseInt(args[1]);
            int guesses = Integer.parseInt(args[2]);
            game.startGame(file, wordLength);

            while(guesses > 0){
                System.out.println("You have "+Integer.toString(guesses)+" guesses left");
                //System.out.println();


                //prompt for input
                //make guess with input

                guesses --;
            }
            //loop make guess and stuff

        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Not enough arguments");
            System.out.println("USAGE: java RunGame dictionary wordLength guesses");
            System.out.println(ex);
        }catch(NumberFormatException ex){
            System.out.println("Incorrect number format for wordLength");
            System.out.println("USAGE: java RunGame dictionary wordLength guesses");
            System.out.println(ex);
        }
    }
}
