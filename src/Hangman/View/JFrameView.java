package Hangman.View;

import Hangman.Controller.IController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is a GUI View for Hangman Game.
 */
public class JFrameView extends JFrame implements IView {

  private JButton[] buttonList;
  private JPanel GamePanel;
  private JPanel ImagePanel;
  private JPanel InfoPanel;
  private JPanel DisplayPanel;
  private JPanel ButtonPanel;
  private JPanel FunctionPanel;
  private JLabel imageLabel;
  private JLabel gameInfoLabel;
  private JLabel displayLabel;
  private JButton restartButton;
  private JButton hintButton;

  /**
   * Construct a GUI View.
   */
  public JFrameView() {
    super("Ultimate Hangman");
    setSize(700, 700);
    setLocation(300, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUIComponents();
    this.add(GamePanel);
    setVisible(true);
  }

  /**
   * There are five Panels in the Game Panel:
   *    1. Image Panel: show the game image
   *    2. Info Panel: show the game information
   *    3. Display Panel: show the current game status
   *    4. Button Panel: show 26 letter buttons
   *    5. Function Panel: restart and hint buttons
   */
  private void createUIComponents() {
    // Set GamePanel
    GamePanel = new JPanel();
    GamePanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // Image Panel
    ImagePanel = new JPanel();
    imageLabel = new JLabel("");
    showImage("/res/icon8.png");
    ImagePanel.add(imageLabel);
    c.gridy = 0;
    GamePanel.add(ImagePanel, c);

    // Info Panel
    InfoPanel = new JPanel();
    gameInfoLabel = new JLabel();
    gameInfoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
    InfoPanel.add(gameInfoLabel);
    c.gridy = 1;
    GamePanel.add(InfoPanel, c);

    // set Display Panel
    DisplayPanel = new JPanel();
    displayLabel = new JLabel("Load...");
    Font font = new Font("Arial", Font.PLAIN, 30);
    Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
    attributes.put(TextAttribute.TRACKING, 0.5);
    Font fontWide = font.deriveFont(attributes);
    displayLabel.setFont(fontWide);
    DisplayPanel.add(displayLabel);
    c.gridy = 2;
    GamePanel.add(DisplayPanel, c);

    // set Button Panel
    ButtonPanel = new JPanel(new GridLayout(2, 13));
    buttonList = new JButton[26];
    for (int i = 0; i < buttonList.length; i++) {
      String Letter = "" + (char) (i + 65);
      JButton button = new JButton(Letter);
      button.setFont(new Font("Arial", Font.PLAIN, 15));
      button.setPreferredSize(new Dimension(50, 50));
      button.setSize(new Dimension(30, 30));
      buttonList[i] = button;
      ButtonPanel.add(buttonList[i]);
    }
    c.gridy = 3;
    GamePanel.add(ButtonPanel, c);

    //set Function Panel
    FunctionPanel = new JPanel();
    restartButton = new JButton("Restart");
    FunctionPanel.add(restartButton);
    hintButton = new JButton("Hint");
    FunctionPanel.add(hintButton);
    c.gridy = 4;
    GamePanel.add(FunctionPanel, c);
  }

  @Override
  public void addFeatures(IController features) {
    // add listener to the 26 letter buttons
    for (int i = 0; i < buttonList.length; i++) {
      JButton sender = buttonList[i];
      sender.addActionListener(evt -> {
        if (features.enableButton()) {
          features.makeGuess(sender.getText());
          sender.setEnabled(false);
        }
      });
    }
    // add listener to restart button
    restartButton.addActionListener(evt -> features.start());

    // add listener to hint button
    hintButton.addActionListener(evt -> {
      String[] options = {"Yes! Please.", "No! Not now."};
      // create a Option Dialog
      int result = JOptionPane.showOptionDialog(
          this,
          "Do you want to pay $0.99 to get a hint?",
          "Get Hint",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,     //no custom icon
          options,  //button titles
          options[1] //default button
      );
      if (result == JOptionPane.YES_OPTION) {
        features.showHint();
      }
    });
  }

  @Override
  public void showGameInfo(String info) {
    gameInfoLabel.setText(info);
  }

  @Override
  public void showGameStatus(String word) {
    displayLabel.setText(word);
  }

  @Override
  public void showImage(String path) {
    BufferedImage img = null;
    URL fileURL = null;
    try {
      fileURL = getClass().getResource(path);
      img = ImageIO.read(fileURL);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(scaledImg);
    imageLabel.setIcon(imageIcon);
  }

  @Override
  public void enableAllButton() {
    for (JButton button : buttonList) {
      button.setEnabled(true);
    }
    hintButton.setEnabled(true);
  }

  @Override
  public void disableButton() {
    hintButton.setEnabled(false);
  }
}
