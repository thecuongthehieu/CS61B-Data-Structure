import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("A"));

        assertFalse(palindrome.isPalindrome("raceCar"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("aaaaaaaaab"));
    }

    @Test
    public void testIsOffByOnePalindrome() {
        assertTrue(palindrome.isPalindrome("", new OffByOne()));
        assertTrue(palindrome.isPalindrome("a", new OffByOne()));
        assertTrue(palindrome.isPalindrome("flake", new OffByOne()));
        assertTrue(palindrome.isPalindrome("FlAkE", new OffByOne()));

        assertFalse(palindrome.isPalindrome("aa", new OffByOne()));
        assertFalse(palindrome.isPalindrome("aA", new OffByOne()));
    }

    @Test
    public void testIsOffByNPalindrome() {
        assertTrue(palindrome.isPalindrome("", new OffByN(5)));
        assertTrue(palindrome.isPalindrome("a", new OffByN(5)));
        assertTrue(palindrome.isPalindrome("af", new OffByN(5)));

        assertFalse(palindrome.isPalindrome("Fa", new OffByN(5)));
        assertFalse(palindrome.isPalindrome("aa", new OffByN(5)));
        assertFalse(palindrome.isPalindrome("aA", new OffByN(5)));
        assertFalse(palindrome.isPalindrome("fh", new OffByN(5)));
    }
}
