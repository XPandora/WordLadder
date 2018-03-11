package WordLadder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import WordLadder.WordLadder.*;

/**
 * Unit test for simple App.
 */
public class WordLadderTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WordLadderTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {

        return new TestSuite( WordLadderTest.class );
    }


    public void testReplace()
    {
        WordLadder wordLadder = new WordLadder();
        assertTrue(wordLadder.replaceChar("cat",0,'a').equals("aat"));
        assertTrue(!wordLadder.replaceChar("hash",0,'a').equals("aat"));
        assertTrue(wordLadder.replaceChar("hash",0,'c').equals("cash"));
    }

    public void testInsert()
    {
        WordLadder wordLadder = new WordLadder();
        assertTrue(wordLadder.insertChar("car",0,'a').equals("acar"));
        assertTrue(wordLadder.insertChar("car",1,'a').equals("caar"));
        assertTrue(wordLadder.insertChar("car",2,'c').equals("cacr"));
        assertTrue(wordLadder.insertChar("car",3,'a').equals("cara"));
    }

    public void testRemove()
    {
        WordLadder wordLadder = new WordLadder();
        assertTrue(wordLadder.removeChar("car",0).equals("ar"));
        assertTrue(wordLadder.removeChar("car",1).equals("cr"));
        assertTrue(wordLadder.removeChar("car",2).equals("ca"));
    }

    public void testWordCheck()
    {
        WordLadder wordLadder = new WordLadder();
        wordLadder.setWord("a","abc");
        assertTrue(wordLadder.wordCheck());
        wordLadder.setWord("!","a");
        assertFalse(wordLadder.wordCheck());
    }

    public void testSameLength()
    {
        WordLadder wordLadder = new WordLadder();
        wordLadder.setWord("a","abc");
        assertFalse(wordLadder.isSameLength());
        wordLadder.setWord("cca","eeq");
        assertTrue(wordLadder.isSameLength());
}
}
