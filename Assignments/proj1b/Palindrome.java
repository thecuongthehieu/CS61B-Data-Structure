/**
 * @author Nguyen Cuong
 */
import sun.awt.image.ImageWatched;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); ++i) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    private boolean isPalindrome(Deque<Character> wordDeque) {
        /* Helper Method */
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        } else {
            Character firstChar = wordDeque.removeFirst();
            Character lastChar = wordDeque.removeLast();
            if (!firstChar.equals(lastChar)) {
                return false;
            } else {
                return isPalindrome(wordDeque);
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);

        return isPalindrome(wordDeque);
    }

    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        /* Helper Method With CharacterComparator */
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        } else {
            Character first = wordDeque.removeFirst();
            Character last = wordDeque.removeLast();

            if (!cc.equalChars(first, last)) {
                return false;
            } else {
                return isPalindrome(wordDeque, cc);
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        /* This Overloads isPalindrome Method above*/
        Deque<Character> wordDeque = wordToDeque(word);

        return isPalindrome(wordDeque, cc);
    }
}