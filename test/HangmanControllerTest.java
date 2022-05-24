import static org.junit.Assert.*;

import Hangman.Controller.HangmanController;
import Hangman.Controller.IController;
import Hangman.Model.HangmanModelImpl;
import Hangman.Model.IModel;
import Hangman.Strategy.TestList;
import Hangman.Strategy.WordList;
import Hangman.View.IView;
import Hangman.View.JFrameView;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a Junit test for hangman controller
 * The test will take couples of seconds to finish.
 */
public class HangmanControllerTest {

  private IController controller;
  private IModel model;
  private IView view;

  @Before
  public void setUp() {
    // in the test list, there is only one word: ALIGN
    WordList wordList = new TestList();
    model = new HangmanModelImpl(wordList);
    view = new JFrameView();
    controller = new HangmanController(model, view);
    controller.start();
  }

  /**
   * A test for win game
   */
  @Test
  public void winGame() {
    assertEquals("ALIGN",model.getAnswer());
    assertEquals("_____",model.getGameStatus());
    assertEquals(8,model.getChance());
    controller.makeGuess("A");
    controller.makeGuess("L");
    controller.makeGuess("I");
    controller.makeGuess("G");
    controller.makeGuess("N");
    assertTrue(model.isGameOver());
    assertEquals("ALIGN",model.getGameStatus());
  }

  /**
   * A test for lose game
   */
  @Test
  public void loseGame() {
    assertEquals("ALIGN",model.getAnswer());
    assertEquals("_____",model.getGameStatus());
    assertEquals(8,model.getChance());
    controller.makeGuess("B");
    controller.makeGuess("C");
    controller.makeGuess("D");
    controller.makeGuess("E");
    controller.makeGuess("F");
    controller.makeGuess("H");
    controller.makeGuess("J");
    controller.makeGuess("K");
    assertTrue(model.isGameOver());
    assertEquals(0,model.getChance());
    assertEquals("_____",model.getGameStatus());
  }
}