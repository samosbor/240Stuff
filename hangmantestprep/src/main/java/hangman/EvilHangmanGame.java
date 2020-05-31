package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EvilHangmanGame implements IEvilHangmanGame {
    Set<String> currentSet;
    boolean gameWon;
    int wLength;


    public EvilHangmanGame(){
        currentSet = new HashSet<>();
        gameWon = false;
    }


    public void startGame(File dictionary, int wordLength){
        currentSet.clear();
        wLength = wordLength;
        try {
            Scanner sc = new Scanner(dictionary);
            while(sc.hasNext("([a-z]|[A-Z])+")){
                String s = sc.next("([a-z]|[A-Z])+");
                s = s.toLowerCase();
                System.out.println(s);
                if(s.length() == wordLength) {
                    currentSet.add(s);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException{
        HashMap<ArrayList<Integer>, Set<String>> partitions= new HashMap<>();

        for(String element : currentSet){
            int key = 0;
            int count = 0;
            for (int i = 0; i < element.length(); i++){
                if (element.charAt(i) == guess){
                    key += (int)Math.pow(2,i);
                    count ++;
                }
            }
            ArrayList<Integer> keyList= new ArrayList<>();
            keyList.add(key);
            keyList.add(count);
            if(partitions.containsKey(keyList)){
                partitions.get(keyList).add(element);
            }
            else{
                Set<String> addSet = new HashSet<>();
                addSet.add(element);
                partitions.put(keyList,addSet);
            }
        }

        int maxSize = 0;
        for(Set<String> part : partitions.values()){
            if(part.size() > maxSize){
                maxSize = part.size();
            }
        }

        int maxKey = 0;
        for(ArrayList<Integer> part : partitions.keySet()){
            if(part.get(0) > maxKey){
                maxKey = part.get(0);
            }
        }

        for(int i = 0; i <= wLength; i++){
            for (int key = maxKey; key >= 0; key--){
                ArrayList<Integer> keyList = new ArrayList<>();
                keyList.add(key);
                keyList.add(i);

                if(partitions.get(keyList) != null) {
                    if (partitions.get(keyList).size() == maxSize) {
                        return partitions.get(keyList);
                    }
                }
            }
        }


        return null;
    }
}
