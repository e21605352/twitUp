package com.iup.tp.twitup.ihm.twit.read;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.ImagePanel;
import com.iup.tp.twitup.ihm.utils.DateFormatter;

public class ReadTwitComponent extends JPanel
{
  private static final long serialVersionUID = -542051157975532236L;

  protected Twit twit;

  protected JPanel contentPane;

  public ReadTwitComponent(Twit twit)
  {
    this.twit = twit;
    this.initComponent();
  }

  protected void initComponent()
  {

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.WHITE);

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    User twitCreator = this.twit.getTwiter();

    try // FIXME : Chargement image
    {
      JPanel imagePane = new ImagePanel(ImageIO.read(new File(twitCreator.getAvatarPath()).toURI().toURL()),
          new Dimension(screenSize.width * 3 / 100, screenSize.width * 3 / 100), false);

      imagePane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      this.contentPane.add(imagePane, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }

    JLabel userName = new JLabel(twitCreator.getName());
    userName.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    this.contentPane.add(userName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel userTag = new JLabel(Constants.USER_TAG_DELIMITER + twitCreator.getUserTag());
    userTag.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
    userTag.setForeground(Color.GRAY);
    this.contentPane.add(userTag, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel divider = new JLabel("-");
    divider.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
    divider.setForeground(Color.GRAY);
    this.contentPane.add(divider, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel twitEmissionDate = new JLabel(DateFormatter.formatLocalDate(new Date(this.twit.getEmissionDate())));
    twitEmissionDate.setFont(new Font("Sans-Serif", Font.ITALIC, 14));
    twitEmissionDate.setForeground(Color.GRAY);
    this.contentPane.add(twitEmissionDate, new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.add(this.generateTwitText(), new GridBagConstraints(1, 1, 4, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
  }

  protected JTextArea generateTwitText()
  {
    JTextArea twitText = new JTextArea();
    twitText.setText(this.twit.getText());
    twitText.setWrapStyleWord(true);
    twitText.setLineWrap(true);
    twitText.setOpaque(false);
    twitText.setEditable(false);
    twitText.setFocusable(false);
    twitText.setBackground(UIManager.getColor("Label.background"));
    twitText.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
    twitText.setBorder(UIManager.getBorder("Label.border"));

    return twitText;
  }
}
