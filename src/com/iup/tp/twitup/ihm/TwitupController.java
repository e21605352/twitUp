package com.iup.tp.twitup.ihm;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class TwitupController
{

  protected TwitupMainView twitupView;

  public void closeWindow()
  {
    this.exitTwitup();
  }

  public void showAbout()
  {
    // FIXME : Texte brut
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg"));
    JLabel content = new JLabel("<html><center>UBO M2-TILL<br/>Département Informatique</center></html>",
        SwingConstants.CENTER);

    JOptionPane.showMessageDialog(this.twitupView, content, "A propos", JOptionPane.PLAIN_MESSAGE, icon);
  }

  public void exitTwitup()
  {
    this.twitupView.dispose();
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public TwitupMainView getTwitupView()
  {
    return twitupView;
  }

  public void setTwitupView(TwitupMainView twitupView)
  {
    this.twitupView = twitupView;
  }
}
