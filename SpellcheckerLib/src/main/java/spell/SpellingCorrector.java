package spell;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpellingCorrector implements ISpellCorrector {
    Words myTrie;

    public SpellingCorrector(){
        myTrie = new Words();
    }

    public static void main(String[] args){
        SpellingCorrector testCorrector = new SpellingCorrector();

        testCorrector.myTrie.add("kick");
        System.out.println(testCorrector.suggestSimilarWord("kkicke"));
    }




    public void useDictionary(String dictionaryFileName) throws IOException{
        File file = new File(dictionaryFileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()){
            myTrie.add(sc.next());
        }
    }





    public String suggestSimilarWord(String inputWord){
        if (myTrie.find(inputWord) != null){
            return inputWord.toLowerCase();
        }
        else{
            ArrayList<String> distance0 = new ArrayList<>();
            distance0.add(inputWord);
            ArrayList<String>distance1 = makeDistanceList(distance0);
            if (mostLikely(distance1) != null){
                System.out.println("I am returning a word that i think is the correct suggestion");
                return mostLikely(distance1);
            }
            ArrayList<String>distance2 = makeDistanceList(distance1);
            return mostLikely(distance2);

        }
    }


    public String mostLikely(ArrayList<String> list){
        HashMap<String, Integer> map = new HashMap<>();
        for (String element : list){
            if(myTrie.find(element) != null){
                WordNode node = (WordNode) myTrie.find(element);
                map.put(element,node.getValue());

            }
        }
        try {
            int maxVal = Collections.max(map.values());
            ArrayList<String> maxes = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == maxVal) {
                    maxes.add(entry.getKey());
                }
            }
            Collections.sort(maxes);
            return maxes.get(0);
        } catch(Exception ex) {
            return null;
        }
    }


    public ArrayList<String> makeDistanceList(ArrayList<String> inputList){
        ArrayList<String> outList = new ArrayList<>();
        for (String s : inputList){
            outList.addAll(deletion(s));
            outList.addAll(transposition(s));
            outList.addAll(alteration(s));
            outList.addAll(insertion(s));
        }

        //System.out.println(outList.toString());
        return outList;
    }

    public ArrayList<String> deletion(String inString){
        ArrayList<String> outList = new ArrayList<>();
        for(int i=0 ;i < inString.length(); i++){
            String addString = inString.substring(0,i) + inString.substring(i+1,inString.length());
            outList.add(addString);
        }
        return outList;
    }

    public ArrayList<String> transposition(String inString){
        ArrayList<String> outList = new ArrayList<>();
        for(int i=0 ;i < inString.length()-1; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(inString.substring(0,i));
            sb.append(inString.charAt(i+1));
            sb.append(inString.charAt(i));
            sb.append(inString.substring(i+2));

            outList.add(sb.toString());
        }
        return outList;
    }

    public ArrayList<String> alteration(String inString){
        ArrayList<String> outList = new ArrayList<>();
        for(int i=0 ;i < inString.length(); i++){
            for (char letter = 'a'; letter < 'z' +1 ; letter++){
                StringBuilder sb = new StringBuilder();
                sb.append(inString.substring(0,i));
                sb.append(letter);
                sb.append(inString.substring(i+1));
                outList.add(sb.toString());
            }
        }
        return outList;
    }

    public ArrayList<String> insertion(String inString){
        ArrayList<String> outList = new ArrayList<>();
        for(int i=0 ;i < inString.length(); i++){
            for (char letter = 'a'; letter < 'z' +1 ; letter++){
                StringBuilder sb = new StringBuilder();
                sb.append(inString.substring(0,i));
                sb.append(letter);
                sb.append(inString.substring(i));
                outList.add(sb.toString());
            }
        }
        return outList;
    }

}
