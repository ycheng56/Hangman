package Hangman.View;

import Hangman.Controller.IController;

/**
 * This is a GUI View Interface for Hangman Game.
 */
public interface IView {

  /**
   * Set the Controller as View's listener
   *
   * @param features the Hangman Controller
   */
  void addFeatures(IController features);

  /**
   * Display current game status
   * @param word the word user guess so far
   */
  void showGameStatus(String word);

  /**
   * Display the game information
   * @param info the game information
   */
  void showGameInfo(String info);

  /**
   * Display the game image
   * @param path the file path
   */
  void showImage(String path);

  /**
   * Enable all the buttons in the view
   */
  void enableAllButton();

  /**
   * Disable the get hint button.
   */
  void disableButton();
}
