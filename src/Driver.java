import Hangman.Controller.*;
import Hangman.Model.*;
import Hangman.Strategy.*;
import Hangman.View.*;

/**
 * This is a Hangman Game Driver.
 *
 * There are three game mode:
 *  1. Default Mode: play with default list
 *  2. Test Mode: there is only one word. The answer is ALIGN
 *  3. User Custom Mode: user can import word list to the game.
 */
public class Driver {

  public static void main(String[] args) {
    // Default Mode
//    WordList wordList = new DefaultList();

    // Test Mode
//     WordList wordList = new TestList();

    // User Custom Mode
    WordList wordList = new UserList(System.in);

    IModel model = new HangmanModelImpl(wordList);
    IView view = new JFrameView();
    IController controller = new HangmanController(model, view);
    controller.start();
  }
}
