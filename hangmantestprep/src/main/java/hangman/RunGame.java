package hangman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class RunGame {

    public RunGame(){

    }

    public static void main(String[] args){
        String d = args[0];
        int w = Integer.parseInt(args[1]);
        int guesses = Integer.parseInt(args[2]);
        File file = new File(d);
        EvilHangmanGame game = new EvilHangmanGame();
        game.startGame(file, w);
        ArrayList<Character> usedLetters = new ArrayList<>();
        Character[] partialWord = new Character[w];
        for(int i = 0; i < w; i++){
            partialWord[i] = '-';
        }

        if(game.currentSet.size() == 0){
            System.out.println("There are no valid strings in that dictionary file");
        }


        while(guesses > 0 && !game.gameWon){
            System.out.println("You have "+ guesses + " guesses left");
            System.out.println("Used letter: " + usedLetters.toString());
            System.out.println("Word: " + partialWord);
            System.out.print("Enter Guess: ");


            try {


                Scanner sc = new Scanner(System.in);
                String guessString = sc.next("([a-z]|[A-Z])");
                char guess = guessString.toLowerCase().charAt(0);
                if(usedLetters.contains(guess)){
                    throw new IEvilHangmanGame.GuessAlreadyMadeException("That letter has already been guessed");
                }
                game.makeGuess(guess);


                Iterator<String> it = game.currentSet.iterator();
                String first = it.next();
                int count = 0;
                for(int i = 0; i < w; i++){
                    if (first.charAt(i) == guess){
                        partialWord[i] = guess;
                        count++;
                    }
                }


                if(count == 0){
                    System.out.println("Sorry there are no "+guessString+"'s");
                }
                else if(count == 1){
                    System.out.println("Yes, there is 1 "+ guessString);
                    guesses++;
                }
                else{
                    System.out.println("Yes there are "+Integer.toString(count)+" "+guess+"'s");
                    guesses++;
                }
                usedLetters.add(guess);
                guesses --;

            } catch (IEvilHangmanGame.GuessAlreadyMadeException e) {
                continue;
            } catch (Exception e){
                System.out.println("Usage: java RunGame dictionary wordLength guesses");
                continue;
            }



        }
        if(game.gameWon){

        }
        else{

        }



    }


}
