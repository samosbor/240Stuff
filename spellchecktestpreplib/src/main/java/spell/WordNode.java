package spell;

public class WordNode implements ITrie.INode{
    int value;
    WordNode[] children;
    char character;

    public WordNode(){
        children = new WordNode[26];
        value = 0;
    }
    public WordNode(char c){
        character = c;
        children = new WordNode[26];
        value = 0;
    }

    public int getValue(){
        return value;
    }
}
