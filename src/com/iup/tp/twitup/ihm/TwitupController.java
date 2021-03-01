package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TwitupController implements Serializable
{
  private static final long serialVersionUID = 4092742481805901354L;

  /**
   * Vue principale de l'application.
   */
  protected TwitupMainView twitupMainView;

  /**
   * Ferme l'application.
   */
  public void exitTwitup()
  {
    this.twitupMainView.dispose();
  }

  /**
   * Affiche le OptionPane "A propos".
   */
  public void showAbout()
  {
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg")); // FIXME : Image brut

    JPanel content = new JPanel(new GridBagLayout());
    content.add(new JLabel("UBO M2-TILL", SwingConstants.CENTER), new GridBagConstraints(0, 0, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); // FIXME : Texte brut
    content.add(new JLabel("Département Informatique", SwingConstants.CENTER), new GridBagConstraints(0, 1, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); // FIXME : Texte brut

    JOptionPane.showMessageDialog(this.twitupMainView, content, "A propos", JOptionPane.PLAIN_MESSAGE, icon);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public TwitupMainView getTwitupView()
  {
    return twitupMainView;
  }

  public void setTwitupView(TwitupMainView twitupView)
  {
    this.twitupMainView = twitupView;
  }
}
