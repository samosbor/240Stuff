package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HangmanGame implements IEvilHangmanGame {
    int wordLength;
    Set<String> currentSet;
    char[] currentPartialWord;

    public HangmanGame(){
    }

    public static void main(String[] args){
        HangmanGame testGame = new HangmanGame();

        File testFile = new File("dictionary.txt");
        testGame.startGame(testFile, 4);



    }




    public void startGame(File dictionary, int inLength){
        wordLength = inLength;
        currentSet.clear();
        currentPartialWord = new char[wordLength];
        try {
            Scanner sc = new Scanner(dictionary);
            while (sc.hasNext()){
                String currString = sc.next();
                if (currString.length() == wordLength){
                    currentSet.add(currString);
                }
            }

        }catch (FileNotFoundException ex){
            System.out.println(ex);
        }
    }


    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{


        Set<String> outSet = selectSet(partitionToMap(currentSet, guess),guess);
        String first = outSet.iterator().next();
        for(int i = 0; i < wordLength; i ++){
            if (first.charAt(i) == guess){
                currentPartialWord[i] = guess;
            }
        }

        return outSet;
    }


    public HashMap<int[], Set<String>> partitionToMap(Set<String> set, char guess) {
        HashMap<int[], Set<String>> map = new HashMap<>();
        for(String word : set){
            int key = 0;
            int letterCount = 0;
            for (int i = 0; i < wordLength; i++){
                if (word.charAt(i) == guess){
                    key += 2^i;
                    letterCount++;
                }
            }
            int[] keyArray = new int[] {letterCount,key};
            if(map.get(keyArray) == null){
                Set<String> addSet = new HashSet<>();
                addSet.add(word);
                map.put(keyArray, addSet);
            }
            else{
                map.get(keyArray).add(word);
            }
        }
        return map;
    }

    public Set<String> selectSet(HashMap<int[], Set<String>> map, char guess){
        int maxSize = 0;
        int maxKey = 0;
        for(Map.Entry<int[], Set<String>> entry : map.entrySet()){
            if (entry.getValue().size() > maxSize){
                maxSize = entry.getValue().size();
            }
            if(entry.getKey()[1] > maxSize){
                maxKey = entry.getKey()[1];
            }
        }

        for(int letterCount = 0; letterCount <= wordLength; letterCount++){
            for (int key = maxKey; key > 0; key --){
                int[] keyArray = new int[] {letterCount,key};
                if (map.get(keyArray).size() == maxSize){
                    //We have established the correct set, now output to the user.
                    StringBuilder sb = new StringBuilder();
                    if(letterCount == 0){
                        sb.append("Sorry there are no ");
                        sb.append(guess);
                        sb.append("'s");
                    }
                    else if (letterCount == 1) {
                        sb.append("Yes, there is 1");
                        sb.append(guess);
                    }
                    else {
                        sb.append("Yes, there are ");
                        sb.append(letterCount);
                        sb.append(" ");
                        sb.append(guess);
                        sb.append("'s");
                    }
                    System.out.println(sb.toString());
                    return map.get(keyArray);
                }
            }
        }

        return null;
    }




}
