package spell;

import java.util.HashMap;

public class WordNode implements ITrie.INode, Cloneable {
    char c;
    int value;
    HashMap<Character,WordNode> children = new HashMap<Character, WordNode>();
    boolean isEndOfWord;
    WordNode parent;

    public WordNode (){}
    public WordNode(Character cin){
        c = cin;
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void incrementValue(){
        value++;
    }

    public HashMap getChildren(){
        return children;
    }
    public void addToChildren(Character c, WordNode node){
        children.put(c,node);
    }

    public Object clone() throws CloneNotSupportedException {
        WordNode clone = (WordNode)super.clone();
        return clone;
    }
}
