package spell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    Words dictionary;

    public SpellCorrector(){
        dictionary = new Words();
    }

    public void useDictionary(String dictionaryFileName) throws IOException{
        File file = new File(dictionaryFileName);
        Scanner sc = new Scanner(file);
        while(sc.hasNext("([a-z]|[A-Z])+")){
            dictionary.add(sc.next("([a-z]|[A-Z])+").toLowerCase());
        }
        System.out.println(dictionary.toString());
    }

    public String suggestSimilarWord(String inputWord){
        inputWord = inputWord.toLowerCase();
        ArrayList<String> distance1 = new ArrayList<>();
        distance1.addAll(deletion(inputWord));
        distance1.addAll(transposition(inputWord));
        distance1.addAll(alteration(inputWord));
        distance1.addAll(insertion(inputWord));
        System.out.println(distance1.toString());
        ArrayList<String> distance2 = new ArrayList<>();
        for(String element : distance1){
            distance2.addAll(deletion(element));
            distance2.addAll(transposition(element));
            distance2.addAll(alteration(element));
            distance2.addAll(insertion(element));
        }

        int maxVal = 0;
        for(String element : distance1){
            if(dictionary.find(element) != null){
                if (dictionary.find(element).getValue() > maxVal){
                    maxVal = dictionary.find(element).getValue();
                }
            }
        }
        if(maxVal > 0){
            Collections.sort(distance1);
            for (String element : distance1){
                if(dictionary.find(element) != null){
                    if(dictionary.find(element).getValue() == maxVal){
                        return element;
                    }
                }
            }
        }

        for(String element : distance2){
            if(dictionary.find(element) != null){
                if (dictionary.find(element).getValue() > maxVal){
                    maxVal = dictionary.find(element).getValue();
                }
            }
        }
        if(maxVal > 0){
            Collections.sort(distance2);
            for (String element : distance2){
                if(dictionary.find(element) != null){
                    if(dictionary.find(element).getValue() == maxVal){
                        return element;
                    }
                }
            }
        }


        return null;
    }

    public ArrayList<String> deletion(String inputWord){
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < inputWord.length(); i++){
            StringBuilder sb = new StringBuilder();
            sb.append(inputWord.substring(0,i));
            sb.append(inputWord.substring(i+1,inputWord.length()));
            out.add(sb.toString());
        }
        //System.out.println(out.toString());
        return out;
    }

    public ArrayList<String> transposition(String inputWord){
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < inputWord.length()-1; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(inputWord.substring(0,i));
            sb.append(inputWord.charAt(i+1));
            sb.append(inputWord.charAt(i));
            sb.append(inputWord.substring(i+2,inputWord.length()));
            out.add(sb.toString());
        }
        //System.out.println(out.toString());
        return out;
    }

    public ArrayList<String> alteration(String inputWord){
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < inputWord.length(); i++){
            for(char letter = 'a'; letter < 'z'+1; letter++) {
                StringBuilder sb = new StringBuilder();
                sb.append(inputWord.substring(0, i));
                sb.append(letter);
                sb.append(inputWord.substring(i + 1, inputWord.length()));
                out.add(sb.toString());
            }
        }
        //System.out.println(out.toString());
        return out;
    }

    public ArrayList<String> insertion(String inputWord){
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < inputWord.length()+1; i++){
            for(char letter = 'a'; letter < 'z'+1; letter++) {
                StringBuilder sb = new StringBuilder();
                sb.append(inputWord.substring(0, i));
                sb.append(letter);
                sb.append(inputWord.substring(i, inputWord.length()));
                out.add(sb.toString());
            }
        }
        //System.out.println(out.toString());
        return out;
    }

}
