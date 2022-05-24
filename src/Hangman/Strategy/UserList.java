package Hangman.Strategy;

import java.io.InputStream;

/**
 * This class enable user to import a word list to the game.
 * The wold list should be stored in a csv file, with the header in its first row.
 */
public class UserList extends WordList {

  /**
   * Construct a wordlist form given file path.
   *
   * @param in the inputStream for obtain user's the file path
   * @throws IllegalArgumentException if the file path is invalid
   */
  public UserList(InputStream in) throws IllegalArgumentException {
    super(in);
  }
}
