package Hangman.Strategy;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is abstract WordList class, which implement the IStrategy Interface.
 */
public abstract class WordList implements IStrategy {

  ArrayList<String> wordList;

  /**
   * Construct a wordlist form given file path.
   * @param in the inputStream for obtain user's the file path
   * @throws IllegalArgumentException if the file path is invalid
   */
  protected WordList(InputStream in) throws IllegalArgumentException {
    System.out.println("Please enter the path of your word list: ");
    Scanner filepath = new Scanner(in);
    Scanner file = null;
    try {
      file = new Scanner(new FileInputStream(filepath.nextLine()));
    } catch (Exception e) {
      throw new IllegalArgumentException("not a valid file path");
    }
    readWordList(file);
  }

  /**
   * A construct for built-in wordlist
   */
  protected WordList() {

  }

  @Override
  public void readWordList(Scanner file){
    wordList = new ArrayList<String>();
    file.nextLine(); //skip the column headers
    while (file.hasNext()) {
      wordList.add(file.next());
    }
    file.close();
  }

  @Override
  public ArrayList<String> getWordList() {
    return this.wordList;
  }
}
