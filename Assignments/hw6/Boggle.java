import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;


/**
 * @author Nguyen Cuong
 * https://leetcode.com/problems/word-search-ii/ : The same problem on leetcode.
 */

public class Boggle {

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        HashSet<String> foundWords = new HashSet<String>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                String word = new String("");
                DFS(board, visited, trie, word, i, j, foundWords);
            }
        }

        List<String> ans = new ArrayList<String>();
        for (String str : foundWords) {
            ans.add(str);
        }

        return ans;
    }

    private boolean validInput(int i, int j, char[][] board) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        return true;
    }

    private void DFS(char[][] board, boolean[][] visited, Trie trie, String word, int i, int j, HashSet<String> foundWords) {
        if (!validInput(i, j, board)) {
            return;
        }

        if (!visited[i][j]) {
            String c = String.valueOf(board[i][j]);
            word += c;
            if (!trie.startsWith(word)) {
                return;
            }

            if (trie.search(word)) {
                foundWords.add(word);
            }

            visited[i][j] = true;

            DFS(board, visited, trie, word, i - 1, j, foundWords);
            DFS(board, visited, trie, word, i, j - 1, foundWords);
            DFS(board, visited, trie, word, i , j + 1, foundWords);
            DFS(board, visited, trie, word, i + 1, j, foundWords);

            visited[i][j] = false;
        }
    }



    private class Trie {

        private static final int NUM_CHARACTERS = 26;

        private class Node {
            private boolean isWord;
            private Node[] links;

            public Node() {
                isWord = false;
                links = new Node[NUM_CHARACTERS];
            }

            public void setIsWord() {
                isWord = true;
            }
        }

        private int index(char c) {
            return c - 'a';
        }



        /** Initialize your data structure here. */
        private Node root;
        public Trie() {
            root = new Node();
        }

        private void insert(String word, Node node) {
            char c = word.charAt(0);

            if (node.links[index(c)] == null) {
                node.links[index(c)] = new Node();
            }


            if (word.length() == 1) {
                node.links[index(c)].setIsWord();
                return;
            }

            String subStr = word.substring(1);
            insert(subStr, node.links[index(c)]);
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            insert(word, root);
        }

        private boolean search(String word, Node node) {
            if (node == null) {
                return false;
            }

            char c = word.charAt(0);
            if (word.length() == 1) {
                return node.links[index(c)] != null && node.links[index(c)].isWord;
            } else {
                String subStr = word.substring(1);
                return search(subStr, node.links[index(c)]);
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return search(word, root);
        }

        private boolean startsWith(String prefix, Node node) {
            if (node == null) {
                return false;
            }

            char c = prefix.charAt(0);
            if (prefix.length() == 1) {
                return node.links[index(c)] != null;
            }

            String subStr = prefix.substring(1);
            return startsWith(subStr, node.links[index(c)]);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(prefix, root);
        }
    }
}
