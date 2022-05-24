package Hangman.Controller;

import Hangman.Model.*;
import Hangman.View.*;

/**
 * This is a Hangman Game Controller, which implements the controller interface.
 */
public class HangmanController implements IController {

  private final IModel model;
  private final IView view;

  /**
   * Construct a Hangman Game Controller, and initialize it with given model and view.
   *
   * @param model the Hangman game model
   * @param view  the GUI view
   */
  public HangmanController(IModel model, IView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void start() {
    model.resetModel(); // reset the model
    view.enableAllButton(); // enable all buttons in the view
    view.showGameInfo("Please make a guess");
    view.showGameStatus(model.getGameStatus()); // display the current game status
    updateImage(); // update the image in the view
  }

  @Override
  public void makeGuess(String letter) {
    // if game is over, disable this method
    if (model.isGameOver()) {
      return;
    }
    model.guess(letter.charAt(0));
    this.updateImage();
    this.updateGameInfo(letter);

    // after this guess, if game is over
    if (model.isGameOver()) {
      this.showGameResult(); // show the game result: win or lose
      this.view.disableButton(); // disable all buttons
    }
  }

  @Override
  public void updateGameInfo(String letter) {
    view.showGameStatus(model.getGameStatus());

    if (model.isCorrectGuess(letter.charAt(0))) {
      view.showGameInfo("Correct! Letter " + letter + " is in the word");
    } else {
      view.showGameInfo("Nope! Letter " + letter + " is not in the word");
    }
  }

  @Override
  public void updateImage() {
    int i = model.getChance();
    String filename = "/res/icon" + i + ".png";
    // show the image for different status.
    view.showImage(filename);
  }

  @Override
  public void showGameResult() {
    String s;
    if (model.getChance() <= 0) {
      s = "You Lose!";
    } else {
      s = "You win!";
      view.showImage("/res/iconWin.png"); // show a winner icon
    }
    view.showGameInfo(s + " The word is " + model.getAnswer());
  }

  @Override
  public void showHint() {
    if (!model.isGameOver()) {
      Character c = model.getLetter();
      view.showGameInfo("Try letter: " + c);
    }
  }

  @Override
  public boolean enableButton() {
    return (!model.isGameOver());
  }
}
