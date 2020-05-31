package spell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Words implements ITrie{
    WordNode root;
    int wordCount;
    int nodeCount;
    Set<String> allWords;
    ArrayList<String> equalsList;

    public Words(){
        root = new WordNode();
        wordCount = 0;
        nodeCount = 1;
        allWords = new HashSet<>();
        equalsList = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Words testWord = new Words();
        Words compareWord = new Words();

        testWord.add("kiCk");
        testWord.add("kick");
        testWord.add("kickers");
        testWord.add("ki");
        testWord.add("apple");
        testWord.add("ape");
        testWord.add("brick");


        compareWord.add("kiCk");
        compareWord.add("kick");
        compareWord.add("kickers");
        compareWord.add("ki");
        compareWord.add("apple");
        compareWord.add("ape");
        compareWord.add("briCk");
        compareWord.add("briCks");



        System.out.println("Test word:\n" + testWord.toString());
        System.out.println(Integer.toString(testWord.hashCode()));
        System.out.println("Compare word:\n" + compareWord.toString());
        System.out.println(Integer.toString(compareWord.hashCode()));


        Object notTrie = new Object();
        if(testWord.equals(notTrie)){
          System.out.println("\nThe two tries are equal ");
        }
        else{
          System.out.println("\nNope, The two tries are NOT equal ");
        }


        //System.out.println("Total words: " + Integer.toString(testWord.wordCount));
        //System.out.println("Total nodes: " + Integer.toString(testWord.nodeCount));

    }





    public void add(String word) {
        word = word.toLowerCase();
        WordNode traverseNode = root;
        for (int i = 0; i < word.length(); i++){
            char currentCharacter = word.charAt(i);
            HashMap<Character,WordNode> traverseNodeChildren = traverseNode.getChildren();
            if (traverseNodeChildren.get(currentCharacter) != null){
                traverseNode = traverseNodeChildren.get(currentCharacter);
                //System.out.println("character: "+currentCharacter+" is already present. continuing to add.");
                if(i == (word.length()-1)) {
                    if(traverseNode.getValue() == 0){
                        wordCount ++;
                    }
                    traverseNode.incrementValue();
                    traverseNode.isEndOfWord = true;

                }
            }
            else{
                traverseNodeChildren.put(currentCharacter, new WordNode(currentCharacter));
                traverseNode = traverseNodeChildren.get(currentCharacter);
                nodeCount++;
                //System.out.println("adding: "+currentCharacter);
                if(i == (word.length()-1)) {
                    traverseNode.incrementValue();
                    traverseNode.isEndOfWord = true;
                    wordCount++;
                }
            }
        }
    }
    public ITrie.INode find(String word) {
        word = word.toLowerCase();
        WordNode traverseNode = root;
        for (int i = 0; i < word.length(); i++){
            char currentCharacter = word.charAt(i);
            HashMap<Character,WordNode> traverseNodeChildren = traverseNode.getChildren();
            if (traverseNodeChildren.get(currentCharacter) != null){
                traverseNode = traverseNodeChildren.get(currentCharacter);
                //System.out.println("character: " + currentCharacter + " is already present. continuing to find.");
            }
            else{
                //System.out.println("That word is not present");
                return null;
            }
        }
        if(traverseNode.isEndOfWord == true){
            //System.out.println("the word: "+word+" is in the tree.");
            return traverseNode;
        }
        else{
            //System.out.println("the word: "+word+" is not in the tree. however, that node is there. its just not a complete word");
            return null;
        }

    }
    public int getWordCount() {
        return wordCount;
    }
    public int getNodeCount() {
        return nodeCount;
    }


    @Override
    public String toString() {
        ArrayList<WordNode> visited = new ArrayList<>();
        WordNode traverseNode = new WordNode();
        traverseNode = root;
        try {
            DFS(visited, traverseNode);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ArrayList<String> toSort = new ArrayList<>();
        for (String s : allWords) {
            toSort.add(s);
        }
        Collections.sort(toSort);
        StringBuilder sb = new StringBuilder();
        for (String s : toSort){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void DFS(ArrayList<WordNode> visited, WordNode node) throws CloneNotSupportedException {
        visited.add(node);
        equalsList.add(node.c + Integer.toString(node.getValue()));
        HashMap<Character, WordNode> children = node.getChildren();

        if(node.isEndOfWord){
            WordNode childClone = (WordNode)node.clone();
            Stack<Character> stack = new Stack<Character>();
            while(childClone.parent != null){
                stack.push(childClone.c);
                childClone = childClone.parent;
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()){
                sb.append(stack.pop());
            }
            allWords.add(sb.toString());
        }

        for (WordNode child: children.values()){
            child.parent = node;
            if (!visited.contains(child)) {
                DFS (visited, child);
            }
        }

    }



    @Override
    public int hashCode() {
        return (wordCount * nodeCount * 31);
    }
    @Override
    public boolean equals(Object o) {
        try{
            Words oCheck = (Words) o;
            if (oCheck.getWordCount() != wordCount || oCheck.getNodeCount() != nodeCount){
                return false;
            }

            ArrayList<WordNode> visited = new ArrayList<>();
            WordNode traverseNode = new WordNode();
            traverseNode = oCheck.root;
            oCheck.equalsList.clear();
            try {
                oCheck.DFS(visited, traverseNode);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            visited.clear();
            traverseNode = root;
            equalsList.clear();
            try {
                DFS(visited, traverseNode);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


            if (oCheck.equalsList.equals(equalsList)){
                return true;
            }
            return true;
        }catch (Exception ex){
            return false;
        }
    }

}
