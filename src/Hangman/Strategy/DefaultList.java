package Hangman.Strategy;

import java.util.Scanner;

/**
 * This class represents a built-in word list,
 * which includes 1000+ words.
 */
public class DefaultList extends WordList {

  /**
   * Construct the default word list.
   */
  public DefaultList() {
    String filePath = "/res/wordlist.csv";
    Scanner file = null;
    file = new Scanner(getClass().getResourceAsStream(filePath));
    super.readWordList(file);
  }
}
