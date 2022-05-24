package Hangman.Strategy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Hangman Strategy interface for choosing the wordlist that
 * will be imported to the model.
 */
public interface IStrategy {

  /**
   * Get the data from a file and store the word list in an ArrayList.
   * @param file the file which stores the word list
   */
  void readWordList(Scanner file);

  /**
   * Get the word list
   * @return the word list
   */
  ArrayList<String> getWordList();

}
