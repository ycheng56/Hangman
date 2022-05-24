import static org.junit.Assert.*;

import Hangman.Model.HangmanModelImpl;
import Hangman.Model.IModel;
import Hangman.Strategy.TestList;
import Hangman.Strategy.WordList;
import org.junit.Before;
import org.junit.Test;

/**
 * A Junit test class for Hangman Model
 */
public class HangmanModelTest {

  private IModel hangman;

  @Before
  public void setUp(){
    // in the test list, there is only one word: ALIGN
    WordList wordList = new TestList();
    hangman = new HangmanModelImpl(wordList);
  }

  /**
   * Illegal Strategy is given
   * IllegalArgumentException is expcted
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidStrategy() {
    WordList wordList = null;
    hangman = new HangmanModelImpl(wordList);
  }

  @Test
  public void testResetModel() {
    // make guesses
    hangman.guess('A');
    hangman.guess('L');
    hangman.guess('Z');

    // reset the model
    hangman.resetModel();
    assertEquals("ALIGN",hangman.getAnswer());
    assertEquals("_____",hangman.getGameStatus());
    assertEquals(8,hangman.getChance());
  }

  @Test
  public void testGetChance() {
    assertEquals(8,hangman.getChance());

    //make a correct guess
    hangman.guess('A');
    assertEquals(8,hangman.getChance());

    //make an incorrect guess
    hangman.guess('Z');
    assertEquals(7,hangman.getChance());

    //make 7 more incorrect guess
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    assertEquals(0,hangman.getChance());

    //make one more incorrect guess
    hangman.guess('Z');
    assertEquals(0,hangman.getChance());
  }

  @Test
  public void testGetAnswer() {
    assertEquals("ALIGN",hangman.getAnswer());
  }

  @Test
  public void isCorrectGuess() {
    // both lowercase and uppercase work
    assertTrue(hangman.isCorrectGuess('A'));
    assertTrue(hangman.isCorrectGuess('L'));
    assertTrue(hangman.isCorrectGuess('i'));
    assertTrue(hangman.isCorrectGuess('g'));
    assertTrue(hangman.isCorrectGuess('n'));

    assertFalse(hangman.isCorrectGuess('B'));
    assertFalse(hangman.isCorrectGuess('b'));
  }

  @Test
  public void guess() {
    assertEquals("_____",hangman.getGameStatus());
    hangman.guess('A');
    assertEquals("A____",hangman.getGameStatus());

    hangman.guess('g');
    assertEquals("A__G_",hangman.getGameStatus());
  }

  @Test
  public void getGameStatus() {
    assertEquals("_____",hangman.getGameStatus());
    hangman.guess('B');
    assertEquals("_____",hangman.getGameStatus());
    hangman.guess('A');
    assertEquals("A____",hangman.getGameStatus());
    hangman.guess('L');
    assertEquals("AL___",hangman.getGameStatus());
    hangman.guess('N');
    assertEquals("AL__N",hangman.getGameStatus());
  }

  /**
   * Test a case that user win
   */
  @Test
  public void isGameOverWin() {
    assertFalse(hangman.isGameOver());
    hangman.guess('A');
    hangman.guess('L');
    hangman.guess('I');
    hangman.guess('G');
    hangman.guess('N');
    assertTrue(hangman.isGameOver());
  }

  /**
   * Test a case that user lose
   */
  @Test
  public void isGameOverLose() {
    assertFalse(hangman.isGameOver());
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    hangman.guess('Z');
    assertTrue(hangman.isGameOver());
  }

  @Test
  public void getLetter() {
    assertSame('A',hangman.getLetter());
    hangman.guess('A');
    hangman.guess('L');
    hangman.guess('I');
    assertSame('G',hangman.getLetter());
  }
}