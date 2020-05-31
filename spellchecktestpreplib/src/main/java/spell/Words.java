package spell;

import java.util.ArrayList;
import java.util.Collections;

public class Words implements ITrie {
    WordNode root;
    int wordCount;
    int nodeCount;

    public Words(){
        root = new WordNode();
        wordCount = 0;
        nodeCount = 1;
    }

    public static void main(String[] args){
        Words testTrie = new Words();
        WordNode testTrie2 = new WordNode();

        testTrie.add("sam");
        testTrie.add("geyboi");
        testTrie.add("sam");
        testTrie.add("geyboi");
        testTrie.add("sam");
        testTrie.add("geyboi");
        testTrie.find("sam");
        testTrie.find("sam");
        testTrie.find("sam");
        testTrie.find("geyboi");
        System.out.println(testTrie.getNodeCount());
        System.out.println(testTrie.getWordCount());

        System.out.println(testTrie.equals(testTrie2));

        System.out.println(testTrie.toString());
        System.out.println(testTrie.toString());
    }



    public void add(String word) {
        word = word.toLowerCase();
        WordNode traverseNode = root;
        for (int i = 0; i < word.length(); i++){
            char curr = word.charAt(i);
            if (traverseNode.children[curr-'a'] == null){
                traverseNode.children[curr-'a'] = new WordNode(curr);
                traverseNode = traverseNode.children[curr-'a'];
                if(i == word.length()-1){
                    traverseNode.value++;
                    wordCount++;
                }
                nodeCount++;
            }
            else{
                traverseNode = traverseNode.children[curr-'a'];
                if(i == word.length()-1){
                    traverseNode.value++;
                    wordCount++;
                }
            }
        }


    }
    public ITrie.INode find(String word) {
        word = word.toLowerCase();
        WordNode traverseNode = root;
        for (int i = 0; i < word.length(); i++){
            char curr = word.charAt(i);
            if (traverseNode.children[curr-'a'] != null){
                traverseNode = traverseNode.children[curr-'a'];
                if(i == word.length()-1){
                    if(traverseNode.value > 0){
                        return traverseNode;
                    }
                    else{
                        return null;
                    }
                }
            }
            else {
                return null;
            }
        }
        return null;
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
        String curr = "";

        ArrayList<String> empty = new ArrayList<>();
        ArrayList<String> end = stringDFS(root, empty, curr);
        Collections.sort(end);
        return end.toString();
    }
    @Override
    public int hashCode() {
        return wordCount * nodeCount * 31;
    }
    @Override
    public boolean equals(Object o) {
        try {
            Words checkWord = (Words) o;
            if (checkWord.hashCode() != hashCode()){
                return false;
            }
            ArrayList<WordNode> visited = new ArrayList<>();
            return equalsDFS(checkWord.root, root, visited);
        } catch (ClassCastException ex){
            return false;
        }
    }

    public boolean equalsDFS(WordNode checkNode, WordNode myNode, ArrayList<WordNode> visited){
        if(checkNode.value == myNode.value && checkNode.character == myNode.character){
            visited.add(checkNode);
            visited.add(myNode);
            for(int i = 0; i < 26; i++) {
                if(!visited.contains(checkNode.children[i]) && checkNode.children[i] != null && myNode.children[i] != null){
                    equalsDFS(checkNode.children[i], myNode.children[i], visited);
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public ArrayList<String> stringDFS(WordNode node, ArrayList<String> allWords, String curr){
        curr += node.character;
        for(int i = 0; i < 26; i++) {
            if(node.children[i] != null){
                stringDFS(node.children[i],allWords, curr);
            }
        }
        if(node.getValue() > 0 && !allWords.contains(curr)){
            allWords.add(curr);
        }

        return allWords;
    }
}
