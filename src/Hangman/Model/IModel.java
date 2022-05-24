package Hangman.Model;

/**
 * This interface represents the operations offered by the Hangman model.
 */

public interface IModel {

  /**
   * Generate a new random word from the list, and clear the guess record.
   * By calling this method, user can start a new game.
   */
  void resetModel();

  /**
   * User can make at most 8 incorrect guesses.
   * Once user make an incorrect guess, the chances is decreased by 1.
   * Get the number of rest chances.
   *
   * @return the number of rest chances
   */
  int getChance();

  /**
   * Get the answer word
   * @return the answer word
   */
  String getAnswer();

  /**
   * Determine whether the guess letter is in the answer
   *
   * @param guessWord the guessed letter
   * @return whether the guess letter is in the answer
   */
  Boolean isCorrectGuess(Character guessWord);

  /**
   * By calling this method, user can make a guess
   * If the guess is incorrect, the number of chance is decreased by 1
   *
   * @param guessWord the guessed letter
   */
  void guess(Character guessWord);

  /**
   * Get the current status of the word
   * The letters have already been guessed are shown as letter
   * The letters have not been guessed are shown as "_"
   *
   * @return the current status of the word
   */
  String getGameStatus();

  /**
   * Determine whether the game is over
   * When game is over, it can be either Win/Lose
   *
   * @return whether the game is over
   */
  Boolean isGameOver();

  /**
   * Get one letter in the answer word
   * @return a letter in the answer word
   */
  Character getLetter();
}
