package com.iup.tp.twitup.ihm.twit.create;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

import com.iup.tp.twitup.ihm.utils.DocumentSizeFilter;
import com.iup.tp.twitup.ihm.utils.ImagePanel;

public class CreateTwitComponent extends JPanel
{
  private static final long serialVersionUID = 3444757906169384827L;

  protected static final int MAX_CHARACTERS = 250;
  protected static final String REMAINING_CHARACTERS_LABEL = "charactères restant";

  protected CreateTwitController createTwitController;

  protected JPanel contentPane;
  protected URL avatarURL;
  protected JTextArea textInput;
  protected DefaultStyledDocument document;
  protected JLabel remainingCharactersLabel;

  public CreateTwitComponent(CreateTwitController createTwitController)
  {
    this.createTwitController = createTwitController;
    this.initComponent();
  }

  protected void initComponent()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setSize(new Dimension(contentPane.getWidth(), screenSize.height * 5 / 100));
    try // FIXME : A enlever
    {
      this.avatarURL = getClass().getClassLoader().getResource("twitupLogo.png").toURI().toURL();
    }
    catch (MalformedURLException | URISyntaxException e)
    {
      e.printStackTrace();
    }

    this.initTextInput();
    this.remainingCharactersLabel = new JLabel();
    this.updateRemainingCharacters();

    this.setLayout(new GridBagLayout());
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents();
  }

  protected void initTextInput()
  {
    this.textInput = new JTextArea();
    this.textInput.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    this.textInput.setLineWrap(true);

    this.document = new DefaultStyledDocument();
    this.document.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS)); // FIXME
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
    this.textInput.setDocument(document);
  }

  protected void updateRemainingCharacters()
  {
    int remainingCharacters = MAX_CHARACTERS - this.document.getLength(); // FIXME
    this.remainingCharactersLabel.setText(remainingCharacters + " " + REMAINING_CHARACTERS_LABEL); // FIXME
  }

  protected void placeComponents()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    try // FIXME : Chargement image
    {
      JPanel imagePane = new ImagePanel(ImageIO.read(this.avatarURL),
          new Dimension(screenSize.width * 3 / 100, screenSize.width * 3 / 100));
      this.contentPane.add(imagePane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }

    this.contentPane.add(new JScrollPane(this.textInput), new GridBagConstraints(1, 0, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));

    this.contentPane.add(new JButton("Twit"), new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.SOUTH,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.add(this.remainingCharactersLabel, new GridBagConstraints(1, 1, 2, 1, 0, 0,
        GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
  }
}
