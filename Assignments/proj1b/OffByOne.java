/**
 * @author Nguyen Cuong.
 * Tast 4: Generalized Palindrome and OffByOne
 */

public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == 1 || diff == -1) {
            return true;
        } else {
            return false;
        }
    }
}