package Hangman.Model;

import Hangman.Strategy.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is Hangman Game Model, which implement the model interface.
 */
public class HangmanModelImpl implements IModel {

  private final ArrayList<String> wordList;
  private String answer;
  private String word;
  private int chance;

  /**
   * Construct a Hangman Model, and initialize it with given strategy.
   *
   * @param strategy a strategy with corresponding word list.
   * @throws IllegalArgumentException if the strategy parameter is not an instance of IStrategy
   */
  public HangmanModelImpl(IStrategy strategy) throws IllegalArgumentException {
    if (!(strategy instanceof IStrategy)) {
      throw new IllegalArgumentException("Invalid Strategy");
    }
    this.wordList = strategy.getWordList();
    resetModel();
  }

  @Override
  public void resetModel() {
    this.answer = generateWord();
    this.word = String.join("", Collections.nCopies(answer.length(), "_"));
    this.chance = 8;
  }

  /**
   * Generate a random word from the wordlist
   *
   * @return the word that is to be guessed.
   */
  private String generateWord() {
    Random random = new Random();
    int randIndex = random.nextInt(wordList.size());
    return wordList.get(randIndex).toUpperCase();
  }

  @Override
  public int getChance() {
    return this.chance;
  }

  @Override
  public String getAnswer() {
    return this.answer;
  }

  @Override
  public Boolean isCorrectGuess(Character letter) {
    letter = Character.toUpperCase(letter);
    return this.answer.indexOf(letter) != -1;
  }

  @Override
  public void guess(Character letter) {
    letter = Character.toUpperCase(letter);
    if (isCorrectGuess(letter)) { // correct guess
      StringBuilder sb = new StringBuilder(this.word);
      for (int i = 0; i < this.answer.length(); i++) {
        if (letter == this.answer.charAt(i)) {
          // update the letter
          sb.setCharAt(i, letter);
        }
      }
      this.word = sb.toString();
    } else { // incorrect guess
      if (chance > 0) {
        chance--;
      }
    }
  }

  @Override
  public String getGameStatus() {
    return this.word;
  }

  @Override
  public Boolean isGameOver() {
    return this.answer.equals(this.word) || this.chance == 0;
  }

  @Override
  public Character getLetter() {
    for (Character c : this.answer.toCharArray()) {
      if (this.word.indexOf(c) == -1) {
        return c;
      }
    }
    return null;
  }
}
