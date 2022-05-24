package Hangman.Controller;

/**
 * This is a Hangman Game Controller Interface.
 */
public interface IController {

  /**
   * Start a new Hangman game
   */
  void start();

  /**
   * Let user making a guess
   * @param letter the guessed letter
   */
  void makeGuess(String letter);

  /**
   * Update the current game status
   * @param letter the guessed letter
   */
  void updateGameInfo(String letter);

  /**
   * Update the image shown on the view
   */
  void updateImage();

  /**
   * When game is over, tell the user win or lose and the correct word.
   */
  void showGameResult();

  /**
   * Give user a hint: show a letter in the answer
   */
  void showHint();

  /**
   * determine whether the letter buttons can work.
   * Enable the buttons when the game is not over.
   *
   * @return whether the letter buttons can work
   */
  boolean enableButton();
}
