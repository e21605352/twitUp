package com.iup.tp.twitup.ihm.twit.create;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.ImagePanel;
import com.iup.tp.twitup.ihm.components.JButtonStyled;
import com.iup.tp.twitup.ihm.utils.DocumentSizeFilter;

public class CreateTwitComponent extends JPanel
{
  private static final long serialVersionUID = 3444757906169384827L;

  protected static final int MAX_CHARACTERS = 250;
  protected static final String REMAINING_CHARACTERS_LABEL = "charactères restant";

  protected JPanel contentPane;
  protected JTextArea textInput;
  protected DefaultStyledDocument document;
  protected JLabel remainingCharactersLabel;

  protected final Set<ICreateTwitComponentObserver> observers;

  public CreateTwitComponent(User user)
  {
    this.observers = new HashSet<>();
    this.initComponent(user);
  }

  protected void initComponent(User user)
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.WHITE);
    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.contentPane.setSize(new Dimension(contentPane.getWidth(), screenSize.height * 5 / 100));
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));

    this.initTextInput();
    this.initRemainingCharactersLabel();

    this.placeComponents(user);
  }

  protected void initTextInput()
  {
    this.document = new DefaultStyledDocument();
    this.document.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
    this.document.addDocumentListener(new DocumentListener()
    {
      @Override
      public void changedUpdate(DocumentEvent e)
      {
        updateRemainingCharacters();
      }

      @Override
      public void insertUpdate(DocumentEvent e)
      {
        updateRemainingCharacters();
      }

      @Override
      public void removeUpdate(DocumentEvent e)
      {
        updateRemainingCharacters();
      }
    });

    this.textInput = new JTextArea();
    this.textInput.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    this.textInput.setLineWrap(true);
    this.textInput.setDocument(this.document);
  }

  protected void initRemainingCharactersLabel()
  {
    this.remainingCharactersLabel = new JLabel();
    this.updateRemainingCharacters();
  }

  protected void updateRemainingCharacters()
  {
    int remainingCharacters = MAX_CHARACTERS - this.document.getLength();
    this.remainingCharactersLabel.setText(remainingCharacters + " " + REMAINING_CHARACTERS_LABEL);
  }

  protected void placeComponents(User user)
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    try
    {
      JPanel imagePane = new ImagePanel(ImageIO.read(new File(user.getAvatarPath()).toURI().toURL()),
          new Dimension(screenSize.width * 3 / 100, screenSize.width * 3 / 100));
      imagePane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      this.contentPane.add(imagePane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    this.contentPane.add(this.textInput, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));

    this.contentPane.add(this.generateTwitButton(), new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.SOUTH,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.add(this.remainingCharactersLabel, new GridBagConstraints(1, 1, 2, 1, 0, 0,
        GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
  }

  protected JPanel generateTwitButton()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JPanel buttonPanel = new JPanel(new GridBagLayout());
    buttonPanel.setOpaque(false);
    buttonPanel.setPreferredSize(new Dimension(screenSize.width * 5 / 100, screenSize.height * 3 / 100));
    buttonPanel.setMinimumSize(new Dimension(screenSize.width * 5 / 100, screenSize.height * 3 / 100));

    JButton sendTwitButton = new JButtonStyled("Twit");
    sendTwitButton.addActionListener(e -> this.clickSendTwit());
    buttonPanel.add(sendTwitButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    return buttonPanel;
  }

  protected void clickSendTwit()
  {
    for (ICreateTwitComponentObserver observer : this.observers)
    {
      observer.notifySendTwit(this.textInput.getText());
    }
  }

  public void clearText()
  {
    this.textInput.setText("");
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ICreateTwitComponentObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ICreateTwitComponentObserver observer)
  {
    this.observers.remove(observer);
  }
}
