package test;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> children;
    boolean endOfWord;
    Item item; // Reference to the actual item

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
    }
}
