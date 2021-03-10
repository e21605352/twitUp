package com.iup.tp.twitup.ihm.profile.profileInfos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.ImagePanel;

public class ProfileInfosComponent extends JPanel
{
  private static final long serialVersionUID = 5755639153249804668L;

  protected JPanel contentPane;

  public ProfileInfosComponent(User user)
  {
    this.initComponent(user);
  }

  protected void initComponent(User user)
  {

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.WHITE);

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents(user);
  }

  protected void placeComponents(User user)
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    try // FIXME : Chargement image
    {
      JPanel imagePane = new ImagePanel(ImageIO.read(new File(user.getAvatarPath()).toURI().toURL()),
          new Dimension(screenSize.width * 3 / 100, screenSize.width * 3 / 100), false);

      imagePane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      this.contentPane.add(imagePane, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }

    JLabel userName = new JLabel(user.getName());
    userName.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    this.contentPane.add(userName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel userTag = new JLabel(Constants.USER_TAG_DELIMITER + user.getUserTag());
    userTag.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
    userTag.setForeground(Color.GRAY);
    this.contentPane.add(userTag, new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel followsNumber = new JLabel(Integer.toString(user.getFollows().size()));
    followsNumber.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    this.contentPane.add(followsNumber, new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    JLabel followsNumberLabel = new JLabel("abonnements");
    followsNumberLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
    this.contentPane.add(followsNumberLabel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }
}
