package Hangman.Strategy;

import java.util.Scanner;

/**
 * This class represents a built-in word list,
 * which includes one word: ALIGN
 */
public class TestList extends WordList {

  /**
   * Construct the test word list.
   */
  public TestList() {
    String filePath = "/res/testlist.csv";
    Scanner file = null;
    file = new Scanner(getClass().getResourceAsStream(filePath));
    super.readWordList(file);
  }
}
