/**
 * @author Nguyen Cuong.
 * Tast 4: Generalized Palindrome and OffByN
 */

public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        this.N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == this.N || - diff == this.N) {
            return true;
        } else {
            return false;
        }
    }
}