package test;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String key, Item item) {
        TrieNode current = root;
        for (char l : key.toCharArray()) {
            TrieNode node = current.children.get(l);
            if (node == null) {
                node = new TrieNode();
                current.children.put(l, node);
            }
            current = node;
        }
        current.endOfWord = true;
        current.item = item;
    }


    public Item search(String key) {
        TrieNode current = root;
        for (char l : key.toCharArray()) {
            TrieNode node = current.children.get(l);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current.endOfWord ? current.item : null;
    }
}
